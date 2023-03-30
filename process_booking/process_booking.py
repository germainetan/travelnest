from flask import Flask, request, jsonify, json
from flask_cors import CORS

import os, sys

import requests
from invokes import invoke_http

import amqp_setup
import pika

app = Flask(__name__)
CORS(app)

payment_URL = "http://payment:8084/payment/"
booking_URL = "http://booking:8080/booking"
property_URL = "http://property:8083/property/"
owner_URL = "http://owner:8081/owner/"
renter_URL = "http://renter:8082/renter/"


@app.route("/accept_booking", methods=['POST'])
def accept_booking():
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

    bookingid = order["bookingid"]
    amqp_setup.check_setup()

    # Invoke the Booking microservice
    # Retrieve paymentID, renterID
    print('\n-----Invoking Booking microservice-----')
    update_booking = invoke_http(booking_URL + "/" + str(bookingid) + "?booking_status=confirmed", method='PUT')
    booking_result = invoke_http(booking_URL + "/" + str(bookingid), method='GET')
    renterid = booking_result["renterID"]
    paymentid = booking_result["paymentID"]
    print('Renterid:', renterid)
    print('Paymentid:', paymentid)
    print('Booking_status:', booking_result['booking_status'])

    # Invoke the Payment microservice
    # Get AuthorizationID from paymentID
    print('\n-----Invoking Payment microservice-----')
    payment_authorization_result = invoke_http(payment_URL + str(paymentid), method='GET')
    authorizationid = payment_authorization_result["authorizationID"]
    print('authorizationid:', authorizationid)

    # Invoke the Payment microservice again
    # Capture funds
    print('\n-----Invoking Payment microservice-----')
    payment_capture_result = invoke_http(payment_URL + "api/orders/" + authorizationid + "/authorization/capture", method='POST')
    print('payment_capture_result:', payment_capture_result)

    # Invoke the Renter microservice
    # Retrieve Renter's phone from RenterID
    print('\n\n-----Invoking Renter microservice-----')
    renter_result = invoke_http(renter_URL + str(renterid), method='GET')
    print('renter_phone:', renter_result["phone"])

    # Send to AMQP
    # data = {'bookingStatus':'pending', 'bookingid':'1', 'ownerFullname':'Low Xuanli', 'ownerPhone':'98242683', 'renterFullname':'Germaine Tan', 'renterPhone':'98242683'}
    message = {'bookingStatus': booking_result['booking_status'], 'bookingid': bookingid, 'renterFullname': renter_result["fullName"], 'renterPhone': renter_result["phone"]}
    amqp_setup.channel.basic_publish(exchange=amqp_setup.exchangename, routing_key="whatsapp-num", 
            body=message, properties=pika.BasicProperties(delivery_mode = 2)) 
    print("\nStatus published to RabbitMQ Exchange.\n")

    # Return Invoke Status, Owner's email, Renter's email
    return {
        "code": 200,
        "data": "Booking Accepted. Payment Captured.",
    }

@app.route("/cancel_booking", methods=['POST'])
def cancel_booking():
    # processBooking()
    # return "Worked"
    if request.is_json:
        try:
            order = request.get_json()
            print("\nReceived an order in JSON:", order)

            # do the actual work
            # 1. Send order info {cart items}
            result = processcancelBooking(order)
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

def processcancelBooking(order):

    bookingid = order["bookingid"]

    # Invoke the Booking microservice
    # Retrieve paymentID, renterID, ownerID
    print('\n-----Invoking Booking microservice-----')
    update_booking = invoke_http(booking_URL + "/" + str(bookingid) + "?booking_status=cancelled", method='PUT')
    booking_result = invoke_http(booking_URL + "/" + str(bookingid), method='GET')
    renterid = booking_result["renterID"]
    paymentid = booking_result["paymentID"]
    ownerid = booking_result['ownerID']
    print('Renterid:', renterid)
    print('Ownerid:', ownerid)
    print('Paymentid:', paymentid)
    print('Booking_status:', booking_result['booking_status'])

    # Invoke the Payment microservice
    # Get captureID from paymentID
    print('\n-----Invoking Payment microservice-----')
    captureid_result = invoke_http(payment_URL + str(paymentid), method='GET')
    captureid = captureid_result["captureID"]
    print('captureid:', captureid)

    # Invoke the Payment microservice again
    # Refund funds
    print('\n-----Invoking Payment microservice-----')
    payment_refund_result = invoke_http(payment_URL + "api/orders/" + captureid + "/refund", method='POST')
    print('payment_refund_result:', payment_refund_result)

    # Invoke the Booking microservice again
    # Update Booking status
    print('\n-----Invoking Booking microservice-----')
    update_booking = invoke_http(booking_URL + "/" + str(bookingid) + "?booking_status=cancelled", method='PUT')
    booking_result = invoke_http(booking_URL + "/" + str(bookingid), method='GET')
    print('Booking_status:', booking_result['booking_status'])

    # Invoke the Renter microservice
    # Retrieve Renter's phone from RenterID
    print('\n\n-----Invoking Renter microservice-----')
    renter_result = invoke_http(renter_URL + str(renterid), method='GET')
    print('renter_phone:', renter_result["phone"])

    # Invoke the Owner microservice
    # Retrieve Owner's phone from RenterID
    print('\n\n-----Invoking Renter microservice-----')
    owner_result = invoke_http(owner_URL + str(ownerid), method='GET')
    print('owner_phone:', owner_result["phone"])

    # Send to AMQP
    # data = {'bookingStatus':'pending', 'bookingid':'1', 'ownerFullname':'Low Xuanli', 'ownerPhone':'98242683', 'renterFullname':'Germaine Tan', 'renterPhone':'98242683'}
    message = {'bookingStatus': booking_result['booking_status'], 'bookingid': bookingid, 'ownerFullname': owner_result["fullname"], 'ownerPhone': owner_result['phone'], 'renterFullname': renter_result["fullName"], 'renterPhone': renter_result["phone"]}
    amqp_setup.channel.basic_publish(exchange=amqp_setup.exchangename, routing_key="whatsapp-num", 
            body=message, properties=pika.BasicProperties(delivery_mode = 2)) 
    print("\nStatus published to RabbitMQ Exchange.\n")

    # Return Invoke Status, Owner's email, Renter's email
    return {
        "code": 200,
        "data": "Refund Successful",
    }

# Execute this program if it is run as a main script (not by 'import')
if __name__ == "__main__":
    print("This is flask " + os.path.basename(__file__) +
          " for placing an order...")
    app.run(host="0.0.0.0", port=5100, debug=True)
    # Notes for the parameters:
    # - debug=True will reload the program automatically if a change is detected;
    #   -- it in fact starts two instances of the same flask program,
    #       and uses one of the instances to monitor the program changes;
    # - host="0.0.0.0" allows the flask program to accept requests sent from any IP/host (in addition to localhost),
    #   -- i.e., it gives permissions to hosts with any IP to access the flask program,
    #   -- as long as the hosts can already reach the machine running the flask program along the network;
    #   -- it doesn't mean to use http://0.0.0.0 to access the flask program.
