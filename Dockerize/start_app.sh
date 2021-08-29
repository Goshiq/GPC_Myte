#!/bin/sh

cp ../target/*jar .
docker pull postgres
docker-compose up
