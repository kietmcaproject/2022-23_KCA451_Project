FROM python:3.10.6 AS BASE

WORKDIR /app

ADD . /app/

RUN pip install --no-cache-dir --upgrade pip

RUN pip install -r requirements.txt
