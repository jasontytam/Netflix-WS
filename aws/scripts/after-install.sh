#!/bin/bash
set -xe


# Copy war file from S3 bucket 
aws s3 cp s3://tamj-netflix-ws-deploy/Netflix-App-0.0.1-SNAPSHOT.jar /usr/local/codedeployresources/
