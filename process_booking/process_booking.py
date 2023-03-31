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


@app.route("/process_booking", methods=['POST'])
def process_booking():
    if request.is_json:
        try:
            check_booking = request.get_json()
            print("\nReceived a request to check booking in JSON:", check_booking)

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
                "message": "process_booking.py internal error: " + ex_str
            }), 500

    # if reached here, not a JSON request.
    return jsonify({
        "code": 400,
        "message": "Invalid JSON input: " + str(request.get_data())
    }), 400

def processBooking(check_booking):

    bookingid = check_booking["bookingid"]
    booking_status = check_booking["booking_status"]
    amqp_setup.check_setup()

    # Invoke the Booking microservice
    # Retrieve paymentID, renterID
    print('\n-----Invoking Booking microservice-----')
    
    booking_result = invoke_http(booking_URL + f"/{bookingid}" , method='GET')

    renterid = booking_result["renterID"]
    paymentid = booking_result["paymentID"]
    ownerid = booking_result["ownerID"]
    print('Renterid:', renterid)
    print('Paymentid:', paymentid)
    print('Booking_result:', booking_result)

    # Invoke the Payment microservice
    # Get AuthorizationID from paymentID
    print('\n-----Invoking Payment microservice-----')
    payment_result = invoke_http(payment_URL + f"/{paymentid}", method='GET')

    if booking_status == "confirmed":
        authorizationid = payment_result["authorizationID"]
        print('authorizationid:', authorizationid)
        payment_param = f"/api/orders/{authorizationid}/authorization/capture"

        return_message = "Booking Accepted. Payment Captured."


    if booking_status == "cancelled":
        captureid = payment_result["captureID"]
        print('captureid:', captureid)
        payment_param = f"/api/orders/{captureid}/refund"

        return_message = "Refund Successful"


    if booking_status == "rejected":
        authorizationid = payment_result["authorizationID"]
        print('authorizationid:', authorizationid)
        payment_param = f"/api/orders/{authorizationid}/authorization/void"

        return_message = "Booking Rejection Successful"


    # Invoke the Payment microservice again
    # Capture funds
    print('\n-----Invoking Payment microservice-----')
    payment_result = invoke_http(payment_URL + payment_param, method='POST')
    print(f'payment_{booking_status}_result:', payment_result)


    # Invoke the Booking microservice
    # Update booking status
    print('\n-----Invoking Booking microservice-----')
    booking_result = invoke_http(booking_URL + f"/{bookingid}?booking_status={booking_status}", method='PUT')


    # Invoke the Renter microservice
    # Retrieve Renter's phone from RenterID
    print('\n\n-----Invoking Renter microservice-----')
    renter_result = invoke_http(renter_URL + f"/{renterid}", method='GET')
    print('renter_phone:', renter_result["phone"])

    # Send to AMQP
    # message = {'bookingStatus':'pending', 'bookingid': '1', 'ownerFullname':'Low Xuanli', 'ownerPhone':'98242683', 'renterFullname':'Germaine Tan', 'renterPhone':'98242683'}

    message = {
        'bookingStatus': booking_status,
        'bookingid': bookingid,
        'renterFullname': renter_result["fullName"],
        'renterPhone': renter_result["phone"],
        'ownerPhone' : "",
        'ownerFullname' : ""
    }

    if booking_status == "cancelled":

        print('\n\n-----Invoking Owner microservice-----')
        owner_result = invoke_http(owner_URL + f"/{ownerid}", method='GET')
        print('owner_result:', owner_result)

        message["ownerFullname"] = owner_result["fullName"]
        message["ownerPhone"] = owner_result["phone"]


    body = json.dumps(message).encode('utf-8')
    
    amqp_setup.channel.basic_publish(exchange=amqp_setup.exchangename, routing_key="whatsapp-num", body=body, properties=pika.BasicProperties(delivery_mode=2)) 
    print("\nStatus published to RabbitMQ Exchange.\n")


    # # Return Invoke Status, Owner's email, Renter's email
    return {
        "code": 200,
        "data": booking_result,
        "message": return_message
    }



# Execute this program if it is run as a main script (not by 'import')
if __name__ == "__main__":
    print("This is flask " + os.path.basename(__file__) +
          " for placing an order...")
    app.run(host="0.0.0.0", port=8088, debug=True)

