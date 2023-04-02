from flask import Flask, request, jsonify
from flask_cors import CORS

import os, sys
from os import environ

# import requests
from invokes import invoke_http

# import json requirements

app = Flask(__name__)
CORS(app)


property_URL = environ.get("property_URL") or "http://localhost:8083/property"
booking_URL = environ.get("booking_URL") or "http://localhost:8080/booking"

@app.route("/filter_property", methods=['POST'])

def filter_property():

    if request.is_json:
        try:
            book_filter = request.get_json()
            print("\nReceived a filter checking in JSON:", book_filter)

            # do the actual work
            # 1. Send order info {cart items}
            result = processFilterProperty(book_filter)
            return jsonify(result), result["code"]

        except Exception as e:
            # Unexpected error in code
            exc_type, exc_obj, exc_tb = sys.exc_info()
            fname = os.path.split(exc_tb.tb_frame.f_code.co_filename)[1]
            ex_str = str(e) + " at " + str(exc_type) + ": " + fname + ": line " + str(exc_tb.tb_lineno)
            print(ex_str)

            return jsonify({
                "code": 500,
                "message": "filter_property.py internal error: " + ex_str
            }), 500

    # if reached here, not a JSON request.
    return jsonify({
        "code": 400,
        "message": "Invalid JSON input: " + str(request.get_data())
    }), 400

def processFilterProperty(book_filter): 
        
    country = book_filter["country"]
    guests = book_filter["guests"]
    start_datetime = "T".join(book_filter["start_datetime"].split(" "))
    end_datetime = "T".join(book_filter["end_datetime"].split(" "))

    # defining the paramters for the microservices
    prop_param = f"/search?country={country}&guests={guests}"
    book_param = f"/search?start_datetime={start_datetime}&end_datetime={end_datetime}"

    # invoke property microservice
    print('\n-----Invoking property microservice-----')

    # returns a list of dictionary
    property_result = invoke_http(property_URL + prop_param , method='GET')
    print('property_result:', property_result)

    
    # invoke booking microservice
    print('\n-----Invoking booking microservice-----')

    # returns a list of propertyID
    booking_result = invoke_http(booking_URL + book_param , method='GET')
    print('booking_result:', booking_result)

    if property_result["code"] or booking_result["code"] not in range(200,300):
        return {
            "code": 404,
            "message": "There are no available properties that match your requirements. Please reselect your filters"
        }
    
    property_result = property_result["data"]

    prop_list = []

    for prop in property_result:
        prop_list.append(prop["propertyID"])

    for i in range(len(prop_list)):
        
        if str(prop_list[i]) in booking_result["data"]:
            property_result.pop(i) 


    if len(property_result):
        return {
                "code": 200,
                "data" : {
                    "property_result" : property_result
                },
                "message" : "Filter Property Success"
            }

    return {
            "code": 404,
            "message": "There are no available properties that match your requirements. Please reselect your filters"
        }
    


# Execute this program if it is run as a main script (not by 'import')
if __name__ == "__main__":
    print("This is flask " + os.path.basename(__file__) + " for filter properties")
    app.run(host="0.0.0.0", port=8086, debug=True)
