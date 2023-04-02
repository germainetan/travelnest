# TravelNest

Our group focuses on the idea of connecting travellers with property owners who are renting out their properties across the world. The 2 main users of our application are renters and property owners. 


## Project Setup 

### Create a user account in phpMyAdmin

1) Start up WAMPServer/ MAMPServer 

2) Open phpMyAdmin and click User accounts 

<insert picture></insert>

3) Click Add user account and specify the following:

- User name: (Use text field:) travelnest
- Host name: (Any host) %
- Password: travelnest

4) Select Data and CREATE option under Structure

5) Leave the rest as default and Click Go
(NOTE: please ensure that a new user: travelnest has been added with the correct privileges)


### Dependencies

# Install Docker Desktop
= Sign up for a Docker ID @ https://hub.docker.com and then download Docker Desktop from https://www.docker.com/get-started.

# All required installations/dependencies are located in the following 2 files, 
NO ACTION IS REQUIRED FROM YOU AS THESE WILL BE INSTALLED AUTOMATICALLY

- amqp.reqs.txt
- http.reqs.txt

HOWEVER, if you happen to encounter problems with the installations when running up the application with docker compose, you may install them manually:

Installing Flask, Requests, Flask_Cors, pika, twilio
Note: for mac users, you may need to type "python3" instead of "python"
- python -m pip install flask
- python -m pip install requests
- python -m pip install flask_cors
- python -m pip install Flask-SQLAlchemy
- python -m pip install mysql-connector-python
- python -m pip install pika
- python -m pip install twilio


### Running the app with docker compose 

Open your terminal in the root project folder -> in this case: travelnest, and execute the following line:

``docker-compose up``

-- you may have to wait a few minutes for the app to start up, if you see error like pika.BlockingConnection, please DO NOT force stop the container, this error is appearing simply bcos rabbitmq needs time to start up. 


### Setting up KONG API Gateway 
do this only AFTER 14/15 containers are up and running

Kong API Gateway setup: https://docs.google.com/document/d/1dIiVSVh8mseYdx2Av2E4ZYKjxrY1F-qx-PhxzukWi7s/edit?usp=sharing


### IF you wish to test out twilio API: 

1) you need to send the following WhatsApp message to +14155238886:

``join improve-entirely``

2) create a renterID in the renterservice db with your number

3) Place an order with our web application and you are all set!


### After you are done, you can choose to stop docker compose and remove the containers & images:

Stop Docker Compose and remove containers:
``docker-compose down``

Stop Docker Compose and remove containers, images:
``docker-compose down --rmi 'all'``