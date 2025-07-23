#!/bin/bash

# Start script for oracle-query-api

PORT=8080
exec java -jar -Dserver.port="${PORT}" -XX:MaxRAMPercentage=80 "oracle-query-api.jar"
