#!/bin/bash
set -x


SLEEP_TIME=3

PID=`ps axf |grep Netflix-App |grep java | grep -o '^[ ]*[0-9]*' |xargs`
if [ ! -z "$PID" ]; then
  echo "Process is running (pid: $PID). Issuing kill..."
  pkill -9 -f Netflix-App
  sleep $SLEEP_TIME
  exit 0
else
  echo "Process is not running."
  exit 0
fi
