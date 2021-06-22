FROM openjdk:8-jre
EXPOSE 8083
MAINTAINER Nicolas Gros <nicolasgros@protonmail.com>
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]