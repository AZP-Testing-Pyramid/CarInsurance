# syntax=docker/dockerfile:1.4
FROM node:18.12.1-bullseye-slim as builder

# set working directory
RUN mkdir /usr/src/app
WORKDIR /usr/src/app

# add `/app/node_modules/.bin` to $PATH
ENV PATH /usr/src/app/node_modules/.bin:$PATH

# install and cache app dependencies
COPY package*.json /usr/src/app
RUN npm install

COPY . /usr/src/app

# start app
CMD ng serve --host 0.0.0.0
