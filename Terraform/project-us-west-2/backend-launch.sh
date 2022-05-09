#!/bin/bash
sudo yum update -y
sudo yum install docker -y
sudo systemctl enable docker
sudo systemctl start docker
docker run -p 80:80 -d -e REACT_API_URL=bowei-rds.c67d3uautcvu.us-west-2.rds.amazonaws.com/ boweiyo/venus-backend:250422