FROM adoptopenjdk/openjdk11:latest
LABEL maintainer="lonelymoonstalker@gmail.com"

ADD . /src
WORKDIR /src

EXPOSE 8080

RUN echo "START RUN JAR"

ENTRYPOINT ["java", "-jar", "target/gamification-auth.jar"]
