# pip install googlemaps
# pip install Pillow

import requests
import googlemaps
from PIL import Image

API_KEY = "AIzaSyC9IHubhNzjWcBNk5Igv2-xn4WcrCPg9Ig"
gmaps = googlemaps.Client(key = API_KEY)
response = requests.get('http://localhost:8000/property')

if response.status_code == 200:
    data = response.json()

    for property in data['data']:
        place_id = property['placeID']
        fields = ['name','formatted_phone_number', 'photo']
        places_details  = gmaps.place(place_id= place_id , fields= fields)

        photo = places_details['result']['photos'][0]['photo_reference']
        photo_width = 400                      
        photo_height = 400

        raw_image_data = gmaps.places_photo(photo_reference = photo, max_width = photo_width, max_height = photo_height)
        filename = "./images/property/P00" + str(property['propertyID']) + '.jpg'
        f = open(filename, 'wb')

        for chunk in raw_image_data:
            if chunk:
                f.write(chunk)
        f.close()

else:
    print('Error:', response.status_code)