import json
import os
import amqp_setup
from twilio.rest import Client

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
    sid = 'AC0db8e941786b67830951b69976e1264e'
    authToken = '9caa9f89094c3a08755321b82d061de3'
    client = Client(sid, authToken)

    ownerPhone = 'whatsapp:+65' + data['ownerPhone']
    renterPhone = 'whatsapp:+65' + data['renterPhone']

    # data = {'bookingStatus':'pending', 'title':'Hideout Horizon - Eco Bamboo Home', 'price': 386, 'ownerFullname':'Low Xuanli', 'ownerPhone':'98242683', 'renterFullname':'Germaine Tan', 'renterPhone':'98242683'}
    if data['bookingStatus'] == 'pending':
        ownerMsg = f"[no reply] From Travelnest: Hi {data['ownerFullname']}, there is a booking placed at {data['title']} pending for your approval. Please login to view the booking."
        renterMsg = f"[no reply] From Travelnest: Hi {data['renterFullname']}, thank you for booking {data['title']}! Your payment of S${data['price']} is currently kept on hold and you will receive the status of your booking within 3 working days."
        client.messages.create(to=ownerPhone, from_='whatsapp:+14155238886', body=ownerMsg) 
        client.messages.create(to=renterPhone, from_='whatsapp:+14155238886', body=renterMsg) 
    
    # data = {'bookingStatus':'cancelled', 'title':'Hideout Horizon - Eco Bamboo Home', 'price':386, 'ownerFullname':'Low Xuanli', 'ownerPhone':'98242683', 'renterFullname':'Germaine Tan', 'renterPhone':'87276883'}
    elif data['bookingStatus'] == 'confirmed':
        renterMsg = f"[no reply] From Travelnest: Hi {data['renterFullname']}, your booking at {data['title']} is confirmed and the payment of S${data['price']} has been deducted successfully. You may login to view your booking(s)."
        client.messages.create(to=renterPhone, from_='whatsapp:+14155238886', body=renterMsg) 
    
    elif data['bookingStatus'] == 'rejected':
        renterMsg = f"[no reply] From Travelnest: Hi {data['renterFullname']}, sorry to inform you that your booking at {data['title']} is not successful and the payment of S${data['price']} has been refunded."
        client.messages.create(to=renterPhone, from_='whatsapp:+14155238886', body=renterMsg) 

    else:
        ownerMsg = f"[no reply] From Travelnest: Hi {data['ownerFullname']}, the booking placed at {data['title']} has been cancelled."
        renterMsg = f"[no reply] From Travelnest: Hi {data['renterFullname']}, your booking at {data['title']} has been cancelled and the payment of S${data['price']} has been refunded."
        client.messages.create(to=ownerPhone, from_='whatsapp:+14155238886', body=ownerMsg) 
        client.messages.create(to=renterPhone, from_='whatsapp:+14155238886', body=renterMsg) 

if __name__ == "__main__":  # execute this program only if it is run as a script (not by 'import')
    receiveBookingLog()
