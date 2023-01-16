#!/bin/bash
set -xe

cd /usr/local/Netflix-WS/
java -jar Netflix-App-0.0.1-SNAPSHOT.jar > ./log/Netflix-App.console.$(date '+%Y%m%d_%H%M%S').log 2>&1 &