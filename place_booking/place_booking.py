from flask import Flask, request, jsonify, json
from flask_cors import CORS

import os, sys
from os import environ

import requests
from invokes import invoke_http

import amqp_setup
import pika

app = Flask(__name__)
CORS(app)

property_URL = environ.get("property_URL") or "http://localhost:8083/property"
booking_URL = environ.get("booking_URL") or "http://localhost:8080/booking"
renter_URL = environ.get("renter_URL") or "http://localhost:8082/renter"
payment_URL = environ.get("payment_URL") or "http://localhost:8084/payment"
owner_URL = environ.get("owner_URL") or "http://localhost:8081/owner"


@app.route("/place_booking", methods=['POST'])

def place_booking():
    # processBooking()
    # return "Worked"
    if request.is_json:
        try:
            check_booking = request.get_json()
            print("\nReceived place booking in JSON:", check_booking)

            # do the actual work
            # 1. Send order info {cart items}
            result = processBooking(check_booking)
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

def processBooking(check_booking):

    orderid = check_booking["orderid"]
    renterid = check_booking["renterid"]
    propertyid = check_booking["propertyid"]
    start_datetime = check_booking["start_datetime"]
    end_datetime = check_booking["end_datetime"]

    # Invoke the Payment microservice
    # Retrieve paymentID from orderID
    print('\n-----Invoking Payment microservice-----')

    payment_result = invoke_http(payment_URL + f"/getpaymentid/{orderid}", method='GET')
    print(payment_result)
    paymentid = payment_result["paymentID"]
    print('Paymentid:', paymentid)

    # Invoke the Payment microservice again
    # Authorize order from orderID
    print('\n-----Invoking Payment microservice-----')
    # payment_authorization_result = invoke_http(payment_URL + "api/orders/" + orderid + "/authorize", method='POST')
    payment_authorization_result = invoke_http(payment_URL + f"/api/orders/{orderid}/authorize", method='POST')
    print('payment_authorization_result:', payment_authorization_result)

    # Invoke the Property microservice
    # Retrieve ownerID from propertyID
    print('\n\n-----Invoking Property microservice-----')
    # property_result = invoke_http(property_URL + propertyid, method='GET')
    property_result = invoke_http(property_URL + f"/{propertyid}", method='GET')
    print('property_result:', property_result)
    ownerid = property_result["ownerID"]

    # Invoke the Owner microservice
    # Retrieve Owner's phone from OwnerID
    print('\n\n-----Invoking Owner microservice-----')
    # owner_result = invoke_http(owner_URL + ownerid, method='GET')
    owner_result = invoke_http(owner_URL + f"/{ownerid}", method='GET')
    print('owner_result:', owner_result)

    # Invoke the Renter microservice
    # Retrieve Renter's phone from RenterID
    print('\n\n-----Invoking Renter microservice-----')
    # renter_result = invoke_http(renter_URL + renterid, method='GET')
    renter_result = invoke_http(renter_URL + f"/{renterid}", method='GET')
    print('renter_result:', renter_result)

    # Craft a JSON payload to create a new booking record
    payload = {"renterID": renterid,
               "ownerID": ownerid,
               "propertyID": propertyid,
               "paymentID": paymentid,
               "start_datetime": start_datetime,
               "end_datetime": end_datetime,
               "booking_status": "pending"
               }

    # Invoke the Booking microservice
    # Create new Booking Record
    print('\n\n-----Invoking Booking microservice-----')
    # booking_result = invoke_http(booking_URL, method='POST', json=payload)
    booking_result = invoke_http(booking_URL, method='POST', json=payload)
    print('booking_result:', booking_result)

    # Invoke the whatsapp microservice

    message = {
        'bookingStatus': booking_result['booking_status'],
        'bookingid': booking_result['bookingID'],
        'renterFullname': renter_result["fullName"],
        'renterPhone': renter_result["phone"],
        'ownerPhone' : owner_result["phone"],
        'ownerFullname' : owner_result["fullName"]
    }

    body = json.dumps(message).encode('utf-8')
    
    amqp_setup.channel.basic_publish(exchange=amqp_setup.exchangename, routing_key="whatsapp-num", body=body, properties=pika.BasicProperties(delivery_mode=2)) 
    print("\nStatus published to RabbitMQ Exchange.\n")


    # Return Invoke Status, Owner's email, Renter's email
    return {
        "code": 200,
        "data": {
            "bookingID" : booking_result['bookingID'],
            "propertyTitle" : property_result['title'],
            "ownerEmail" : owner_result['email'],
            "ownerPhone" : owner_result["phone"],
            "start_datetime" : start_datetime,
            "end_datetime" : end_datetime,
            "booking_status" : "pending"

        },
        "message" : "booking creation successful!"
    }

# Execute this program if it is run as a main script (not by 'import')
if __name__ == "__main__":
    print("This is flask " + os.path.basename(__file__) +
          " for placing an order...")
    app.run(host="0.0.0.0", port=8087, debug=True)

