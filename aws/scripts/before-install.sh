#!/bin/bash
set -xe

# Delete the old  directory as needed.
if [ -d /usr/local/codedeployresources ]; then
    rm -rf /usr/local/codedeployresources/
fi

mkdir -vp /usr/local/codedeployresources

if [ ! -d /usr/local/Netflix-WS ]; then
  mkdir /usr/local/Netflix-WS
else
  cd /usr/local/Netflix-WS
  find . -type f -name 'Netflix-App-*-SNAPSHOT.jar' -exec mv {} "backup/{}_$(date '+%Y%m%d_%H%M%S')" ';'
  cd -
fi

if [ ! -d /usr/local/Netflix-WS/log ]; then
  mkdir /usr/local/Netflix-WS/log
fi
