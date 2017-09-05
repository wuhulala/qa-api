FROM ubuntu:14.04
MAINTAINER wuhulala "xueaohui_com@163.com"
ENV REFRESHED_AT 2017-09-05

RUN apt-get update
RUN apt-get -y -q install nginx

RUN mkdir -p /var/www/html
ADD nginx/global.conf /etc/nginx/conf.d/
ADD nginx/nginx.conf /etc/nginx/

EXPOSE 80