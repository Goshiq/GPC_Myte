#!/bin/sh

docker pull postgres
docker-compose -f ./Dockerize/docker-compose.yml up
