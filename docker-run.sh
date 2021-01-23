#!/bin/bash

JAVA_OPTS="$JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -XX:+UnlockExperimentalVMOptions -XX:+UseCGroupMemoryLimitForHeap"

exec java $JAVA_OPTS -jar /app.jar
