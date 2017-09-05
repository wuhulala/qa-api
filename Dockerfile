FROM java:8

MAINTAINER xueaohui

ADD target/qa-api.jar app.jar

EXPOSE 9001

ENTRYPOINT ["java","-jar","/app.jar"]