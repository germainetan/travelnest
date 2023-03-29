from flask import Flask, request, jsonify
from flask_cors import CORS

import os, sys
from os import environ

# import requests
from ..req.invokes import invoke_http

# import json requirements

app = Flask(__name__)
CORS(app)


property_URL = environ.get("property_URL") or "http://localhost:8083/property"
booking_URL = environ.get("booking_URL") or "http://localhost:8080/booking"



@app.route("/filter_property", methods=['GET'])

def filter_property():
    # Simple check of input format and data of the request are JSON

    # values here are assumed to be not null
    country = request.args.get("country")
    guests = request.args.get("guests")
    start_datetime = request.args.get("start_datetime")

    print("\nReceived a view property request in JSON:")

    if country and guests and start_datetime:

        # defining the paramters for the microservices
        prop_param = f"/search?country={country}&guests={guests}"
        book_param = f"/search?start_datetime={start_datetime}"

        # invoke property microservice
        print('\n-----Invoking property microservice-----')

        # returns a list of dictionary
        property_result = invoke_http(property_URL + prop_param , method='GET')
        print('property_result:', property_result)

        
        # invoke booking microservice
        print('\n-----Invoking booking microservice-----')

        # returns a list 
        booking_result = invoke_http(booking_URL + book_param , method='GET')
        print('booking_result:', booking_result)

        prop_list = []

        for prop in property_result:
            prop_list.append(prop["propertyID"])


        for i in range(len(prop_list)):
            
            if str(prop_list[i]) in booking_result:
                property_result.pop(i) 

        if len(property_result):
            return jsonify(
                {
                    "code": 200,
                    "data" : {
                        "property_result" : property_result
                    }
                }
            )
        return jsonify(
            {
                "code": 404,
                "message": "There are no available properties that match your requirements. Please reselect your filters"
            }
        ), 404
    
    return jsonify(
        {
            "code": 404,
            "message": "Error: Insufficient Arguments"
        }
    ), 404


# Execute this program if it is run as a main script (not by 'import')
if __name__ == "__main__":
    print("This is flask " + os.path.basename(__file__) + " for filter properties")
    app.run(host="0.0.0.0", port=8086, debug=True)
