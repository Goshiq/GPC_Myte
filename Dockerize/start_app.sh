#!/bin/sh

cp ../target/*jar .
docker pull postgres
docker rm -f dockerize_gpc_1
docker rmi -f dockerize_gpc
docker-compose up
