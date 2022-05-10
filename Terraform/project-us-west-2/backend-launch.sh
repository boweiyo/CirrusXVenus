#!/bin/bash
sudo yum update -y
sudo yum install docker -y
sudo systemctl enable docker
sudo systemctl start docker
docker run -p 8080:8080 -d -e spring.datasource.url=jdbc:postgresql://${venus-db}/smartbankapp -e spring.datasource.username=postgres -e spring.datasource.password=postgres boweiyo/venus-backend:250422
