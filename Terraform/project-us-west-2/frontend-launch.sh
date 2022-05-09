#!/bin/bash
sudo yum update -y
sudo yum install docker -y
sudo systemctl enable docker
sudo systemctl start docker
docker run -p 80:80 -d -e REACT_API_URL=bowei-api-lb-2f6b27d12e53bd63.elb.us-west-2.amazonaws.com/ abhay1813/finalfrontend