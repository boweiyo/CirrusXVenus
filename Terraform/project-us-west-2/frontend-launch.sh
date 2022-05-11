#!/bin/bash
sudo yum update -y
sudo yum install docker -y
sudo systemctl enable docker
sudo systemctl start docker
docker run -p 80:3000 -d -e REACT_APP_API_URL=https://bowei.cloudtech-training.com/ abhay1813/finalfrontend