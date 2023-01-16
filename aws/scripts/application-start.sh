#!/bin/bash
set -xe

cd /usr/local/Netflix-WS/
java -jar Netflix-App-0.0.1-SNAPSHOT.jar 2>&1 > ./log/Netflix-App.console.$(date '+%Y%m%d_%H%M%S').log &