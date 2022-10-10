FROM alpine as build
LABEL maintainer="lonelymoonstalker@gmail.com"

ARG MAVEN_VERSION=3.6.3
ARG USER_HOME_DIR="/root"
ARG BASE_URL=https://apache.osuosl.org/maven/maven-3/${MAVEN_VERSION}/binaries

RUN apk --update --no-cache add openjdk17 curl

RUN mkdir -p /usr/share/maven /usr/share/maven/ref \
 && curl -fsSL -o /tmp/apache-maven.tar.gz ${BASE_URL}/apache-maven-${MAVEN_VERSION}-bin.tar.gz \
 && tar -xzf /tmp/apache-maven.tar.gz -C /usr/share/maven --strip-components=1 \
 && rm -f /tmp/apache-maven.tar.gz \
 && ln -s /usr/share/maven/bin/mvn /usr/bin/mvn

ENV MAVEN_HOME /usr/share/maven
ENV MAVEN_CONFIG "$USER_HOME_DIR/.m2"
ENV JAVA_HOME /usr/lib/jvm/default-jvm/

WORKDIR /data
CMD ["mvn", "--version"]

ADD pom.xml ./pom.xml
ADD /gamification-api ./gamification-api
ADD /gamification-db ./gamification-db
ADD /gamification-common ./gamification-common
ADD /gamification-impl ./gamification-impl

RUN mvn clean package spring-boot:repackage

EXPOSE 8080

RUN echo "START RUN JAR"

ENTRYPOINT ["java", "-jar", "gamification-impl/target/gamification.jar"]
