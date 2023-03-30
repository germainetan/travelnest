from invokes import invoke_http
from os import environ
from flask import request, jsonify
import json

# if environment variable is set, program will use its value 
# otherwise, it will prompt for an input
book_URL = booking_URL = "http://localhost:8080/booking/" 

# invoke book microservice to get all book

# with open("test.json", "r") as f:
#     intents = json.load(f)

# # print(intents["property_filters"]["start_datetime"])

# start_datetime = intents["property_filters"]["start_datetime"]

bookingID = 8

results = invoke_http(book_URL + f"{bookingID}", method='GET')

print( type(results) )
print()
print( results )

