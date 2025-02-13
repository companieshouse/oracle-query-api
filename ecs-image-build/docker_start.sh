#!/bin/bash

# Start script for oracle-query-api

PORT=8080
exec java -jar -Dserver.port="${PORT}" "oracle-query-api.jar"
