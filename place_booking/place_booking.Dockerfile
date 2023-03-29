FROM python:3-slim
WORKDIR /usr/src/app
COPY http.reqs.txt amqp.reqs.txt ./
RUN python -m pip install --no-cache-dir -r http.reqs.txt -r amqp.reqs.txt
COPY ./place_booking.py ./invokes.py ./
EXPOSE 8087
CMD [ "python", "./place_booking.py" ]