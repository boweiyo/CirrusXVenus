FROM openjdk:8-jre-alpine

MAINTAINER venus@gmail.com

COPY target/smart-bank-api.jar app.jar

ENTRYPOINT ["/usr/bin/java", "-jar", "app.jar"]