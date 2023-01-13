#!/bin/bash
set -x

NUMBER_OF_ATTEMPTS=10
SLEEP_TIME=3

for i in `seq 1 $NUMBER_OF_ATTEMPTS`;
do
  ps |grep Netflix-App |grep java | grep -o '^[ ]*[0-9]*' > /dev/null
  if [ $? -eq 0 ]; then
    echo "Process is running."
  else
    echo "Process is not running. retrying..."
  fi
  sleep $SLEEP_TIME
done
echo "Server did not come up after expected time. Failing."
exit 1