FROM python:3-slim
WORKDIR /usr/src/app
COPY http.reqs.txt ./
RUN python -m pip install --no-cache-dir -r http.reqs.txt
COPY ./check_booking.py ./invokes.py ./
CMD [ "python", "./check_booking.py" ]


# FROM python:3-slim
# WORKDIR /usr/src/app
# COPY ../req/http.reqs.txt ./
# RUN python -m pip install --no-cache-dir -r http.reqs.txt
# COPY ./filter_property/filter_property.py ../req/invokes.py ./
# CMD [ "python", "./filter_property.py" ]