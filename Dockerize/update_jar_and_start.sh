#!/bin/sh

rm -rf *.jar
docker rm -f dockerize_gpc_1
docker rmi -f dockerize_gpc
./start_app.sh
