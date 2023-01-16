#!/bin/bash
set -xe

java -jar /usr/local/Netflix-WS/Netflix-App-0.0.1-SNAPSHOT.jar 2>&1 > Netflix-App.console.$(date '+%Y%m%d_%H%M%S').log &