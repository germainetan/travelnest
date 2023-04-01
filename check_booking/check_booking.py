from flask import Flask, request, jsonify
from flask_cors import CORS

import os, sys
from os import environ

# import requests
from invokes import invoke_http

# import json requirements

app = Flask(__name__)
CORS(app)


property_URL = environ.get("property_URL") or "http://localhost:8000/property"
booking_URL = environ.get("booking_URL") or "http://localhost:8000/booking"
renter_URL = environ.get("renter_URL") or "http://localhost:8000/renter"

@app.route("/check_booking", methods=['POST'])

def filter_property():

    if request.is_json:
        try:
            check_booking = request.get_json()
            print("\nReceived a request to check booking in JSON:", check_booking)

            # do the actual work
            # 1. Send order info {cart items}
            result = processFilterProperty(check_booking)
            return jsonify(result), result["code"]

        except Exception as e:
            # Unexpected error in code
            exc_type, exc_obj, exc_tb = sys.exc_info()
            fname = os.path.split(exc_tb.tb_frame.f_code.co_filename)[1]
            ex_str = str(e) + " at " + str(exc_type) + ": " + fname + ": line " + str(exc_tb.tb_lineno)
            print(ex_str)

            return jsonify({
                "code": 500,
                "message": "check_booking.py internal error: " + ex_str
            }), 500

    # if reached here, not a JSON request.
    return jsonify({
        "code": 400,
        "message": "Invalid JSON input: " + str(request.get_data())
    }), 400

def processFilterProperty(check_booking): 
        
    personID = check_booking["personID"]
    booking_status = check_booking["booking_status"].lower()
    status = check_booking["status"].lower()

    if status == "owner":
        # defining the paramters for the microservices  
        book_param = f"/search_owner?ownerID={personID}&booking_status={booking_status}"
    else:
        book_param = f"/search_renter?renterID={personID}&booking_status={booking_status}"

    # invoke booking microservice
    print('\n-----Invoking booking microservice-----')

    # returns a list of dict
    booking_result = invoke_http(booking_URL + book_param , method='GET')
    print('booking_result:', booking_result)

    if booking_result["code"] in range(200, 300):

    # iterate thru list to invoke & get the variables
        check_book = []

        for booking in booking_result["data"]:

            result = {}

            # get the required variables needed to invoke other ms AND store in result
            result["bookingID"] = booking["bookingID"]
            result["start_datetime"] = booking["start_datetime"]
            result["end_datetime"] = booking["end_datetime"]

            renterID = booking["renterID"]
            propertyID = booking["propertyID"]

            # invoke property microservice
            print('\n-----Invoking property microservice-----')

            # returns a list of dictionary
            property_result = invoke_http(property_URL + f"/{propertyID}" , method='GET')
            print('property_result:', property_result)

            if status == "owner":
                # invoke renter microservice
                print('\n-----Invoking property microservice-----')

                # returns a list of dictionary
                renter_result = invoke_http(renter_URL + f"/{renterID}" , method='GET')
                print('renter_result:', renter_result)

                result["renter"] = renter_result["data"]

            property_result = property_result["data"]

            property_response = {
                "title" : property_result["title"],
                "propertyID" : property_result["propertyID"],
                "country" : property_result["country"],
                "city" : property_result["city"],
                "address" : property_result["address"],
            }

            result["property"] = property_response

            check_book.append(result)

        return {
                "code": 200,
                "data" : {
                    "check_book_results" : check_book
                },
                "message" : "Check booking_result Success"
            }

    return {
            "code": 404,
            "message": f"There are no {booking_status} bookings as of this moment."
        }


# Execute this program if it is run as a main script (not by 'import')
if __name__ == "__main__":
    print("This is flask " + os.path.basename(__file__) + " for filter properties")
    app.run(host="0.0.0.0", port=8085, debug=True)
