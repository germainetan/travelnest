from flask import Flask, request, jsonify, json
from flask_cors import CORS

import os, sys

import requests
from invokes import invoke_http

app = Flask(__name__)
CORS(app)

payment_URL = "http://localhost:8084/payment/"
booking_URL = "http://localhost:8080/booking"
property_URL = "http://localhost:8083/property/"
owner_URL = "http://localhost:8081/owner/"
renter_URL = "http://localhost:8082/renter/"


@app.route("/place_booking", methods=['POST'])
def place_booking():
    # processBooking()
    # return "Worked"
    if request.is_json:
        try:
            order = request.get_json()
            print("\nReceived an order in JSON:", order)

            # do the actual work
            # 1. Send order info {cart items}
            result = processBooking(order)
            return jsonify(result), result["code"]

        except Exception as e:
            # Unexpected error in code
            exc_type, exc_obj, exc_tb = sys.exc_info()
            fname = os.path.split(exc_tb.tb_frame.f_code.co_filename)[1]
            ex_str = str(e) + " at " + str(exc_type) + ": " + fname + ": line " + str(exc_tb.tb_lineno)
            print(ex_str)

            return jsonify({
                "code": 500,
                "message": "processpayment.py internal error: " + ex_str
            }), 500

    # if reached here, not a JSON request.
    return jsonify({
        "code": 400,
        "message": "Invalid JSON input: " + str(request.get_data())
    }), 400

def processBooking(order):

    orderid = order["orderid"]
    renterid = str(order["renterid"])
    propertyid = str(order["propertyid"])
    startdate = order["startdate"]
    enddate = order["enddate"]

    # Invoke the Payment microservice
    # Retrieve paymentID from orderID
    print('\n-----Invoking Payment microservice-----')
    # payment_result = invoke_http(payment_URL + "getpaymentid/" + orderid, method='GET')
    payment_result = invoke_http("http://payment-container:8084/payment/getpaymentid/" + orderid, method='GET')
    print(payment_result)
    paymentid = payment_result["paymentID"]
    print('Paymentid:', paymentid)

    # Invoke the Payment microservice again
    # Authorize order from orderID
    print('\n-----Invoking Payment microservice-----')
    # payment_authorization_result = invoke_http(payment_URL + "api/orders/" + orderid + "/authorize", method='POST')
    payment_authorization_result = invoke_http("http://payment-container:8084/payment/api/orders/" + orderid + "/authorize", method='POST')
    print('payment_authorization_result:', payment_authorization_result)

    # Invoke the Property microservice
    # Retrieve ownerID from propertyID
    print('\n\n-----Invoking Property microservice-----')
    # property_result = invoke_http(property_URL + propertyid, method='GET')
    property_result = invoke_http("http://property-container:8083/property/" + propertyid, method='GET')
    print('property_result:', property_result["ownerID"])
    ownerid = str(property_result["ownerID"])

    # Invoke the Owner microservice
    # Retrieve Owner's phone from OwnerID
    print('\n\n-----Invoking Owner microservice-----')
    # owner_result = invoke_http(owner_URL + ownerid, method='GET')
    owner_result = invoke_http("http://owner-container:8081/owner/" + ownerid, method='GET')
    print('owner_result:', owner_result["phone"])

    # Invoke the Renter microservice
    # Retrieve Renter's phone from RenterID
    print('\n\n-----Invoking Renter microservice-----')
    # renter_result = invoke_http(renter_URL + renterid, method='GET')
    renter_result = invoke_http("http://renter-container:8082/renter/" + renterid, method='GET')
    print('renter_result:', renter_result["phone"])

    # Craft a JSON payload to create a new booking record
    payload = {"renterID": int(renterid),
               "ownerID": int(ownerid),
               "propertyID": int(propertyid),
               "paymentID": paymentid,
               "start_datetime": "2023-03-29 15:00:00",
               "end_datetime": "2023-03-29 15:00:00",
               "booking_status": "pending"}

    # Invoke the Booking microservice
    # Create new Booking Record
    print('\n\n-----Invoking Booking microservice-----')
    # booking_result = invoke_http(booking_URL, method='POST', json=payload)
    booking_result = invoke_http("http://booking-container:8080/booking", method='POST', json=payload)
    print('booking_result:', booking_result)

    # Invoke the Email microservice

    # Return Invoke Status, Owner's email, Renter's email
    return {
        "code": 200,
        "data": "Invoked",
        "renterphone": renter_result["phone"],
        "ownerphone": owner_result["phone"]
    }


# Execute this program if it is run as a main script (not by 'import')
if __name__ == "__main__":
    print("This is flask " + os.path.basename(__file__) +
          " for placing an order...")
    app.run(host="0.0.0.0", port=5000, debug=True)
    # Notes for the parameters:
    # - debug=True will reload the program automatically if a change is detected;
    #   -- it in fact starts two instances of the same flask program,
    #       and uses one of the instances to monitor the program changes;
    # - host="0.0.0.0" allows the flask program to accept requests sent from any IP/host (in addition to localhost),
    #   -- i.e., it gives permissions to hosts with any IP to access the flask program,
    #   -- as long as the hosts can already reach the machine running the flask program along the network;
    #   -- it doesn't mean to use http://0.0.0.0 to access the flask program.
