import json
import os
import amqp_setup
from twilio.rest import Client
from os import environ

monitorBindingKey='whatsapp-num'

def receiveBookingLog():
    amqp_setup.check_setup()
        
    queue_name = 'whatsapp-send'
    
    amqp_setup.channel.basic_consume(queue=queue_name, on_message_callback=callback, auto_ack=True)
    amqp_setup.channel.start_consuming()  

def callback(channel, method, properties, body): 
    sendWhatsapp(json.loads(body))

# Microservices: Place Booking, Process Booking
def sendWhatsapp(data):
    sid = environ.get('SID')
    authToken = environ.get('AUTHTOKEN')
    client = Client(sid, authToken)

    whatsappPhone = "+14155238886"

    ownerPhone = 'whatsapp:+65' + str(data['ownerPhone'])
    renterPhone = 'whatsapp:+65' + str(data['renterPhone'])

    # data = {'bookingStatus':'pending', 'bookingid':'1', 'ownerFullname':'Low Xuanli', 'ownerPhone':'12345678', 'renterFullname':'Germaine Tan', 'renterPhone':'98765432'}
    if data['bookingStatus'] == 'pending':
        ownerMsg = f"[no reply] From Travelnest: Hi {data['ownerFullname']}, there is a booking BookingID: {data['bookingid']} awaiting your approval. Please login to view the booking."
        renterMsg = f"[no reply] From Travelnest: Hi {data['renterFullname']}, thank you for booking with us! Your payment is currently kept on hold and you will receive the status of your booking BookingID: {data['bookingid']} within 3 working days."
        client.messages.create(to=ownerPhone, from_=f'whatsapp:{whatsappPhone}', body=ownerMsg) 
        client.messages.create(to=renterPhone, from_=f'whatsapp:{whatsappPhone}', body=renterMsg) 
    
    # data = {'bookingStatus':'confirmed', 'bookingid':'1', 'ownerFullname':'', 'ownerPhone':'',  'renterFullname':'Low Xuanli', 'renterPhone':'12345678'}
    elif data['bookingStatus'] == 'confirmed':
        renterMsg = f"[no reply] From Travelnest: Hi {data['renterFullname']}, your booking BookingID: {data['bookingid']} is confirmed and the payment has been deducted successfully. You may login to view your booking(s)."
        client.messages.create(to=renterPhone, from_=f'whatsapp:{whatsappPhone}', body=renterMsg) 
    
    elif data['bookingStatus'] == 'rejected':
        renterMsg = f"[no reply] From Travelnest: Hi {data['renterFullname']}, We are sorry to inform you that your booking BookingID: {data['bookingid']} is not successful and the payment has been refunded."
        client.messages.create(to=renterPhone, from_=f'whatsapp:{whatsappPhone}', body=renterMsg) 

    else:
        ownerMsg = f"[no reply] From Travelnest: Hi {data['ownerFullname']}, the booking BookingID: {data['bookingid']} has been cancelled."
        renterMsg = f"[no reply] From Travelnest: Hi {data['renterFullname']}, your booking BookingID: {data['bookingid']} has been cancelled and the payment has been refunded."
        client.messages.create(to=ownerPhone, from_=f'whatsapp:{whatsappPhone}', body=ownerMsg) 
        client.messages.create(to=renterPhone, from_=f'whatsapp:{whatsappPhone}', body=renterMsg) 

if __name__ == "__main__":  # execute this program only if it is run as a script (not by 'import')
    receiveBookingLog()
