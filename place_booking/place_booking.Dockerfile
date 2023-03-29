FROM python:3-slim
WORKDIR /usr/src/app
COPY ../requirements/http.reqs.txt ../
RUN python -m pip install --no-cache-dir -r http.reqs.txt -r amqp.reqs.txt
COPY ./place_order.py ../requirements/invokes.py ../
CMD [ "python", "./place_order.py" ]