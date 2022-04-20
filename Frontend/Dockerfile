# pull official base image
FROM node:alpine

MAINTAINER venus@gmail.com

# set working directory
WORKDIR /app

# install app dependencies
COPY src/ /app/src
COPY public/ /app/public
COPY package.json /app


# start app
RUN ["npm","install"]
CMD ["npm", "start"]
