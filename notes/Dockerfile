FROM openjdk:8-jre
EXPOSE 8082
MAINTAINER Nicolas Gros <nicolasgros@protonmail.com>
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]