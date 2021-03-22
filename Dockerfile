
FROM maven:3.6.3-jdk-8 AS build

RUN mkdir /build
WORKDIR /build


COPY pom.xml /build/pom.xml
COPY src/ /build/src

RUN mvn -f /build/pom.xml clean package
RUN ls -la target

FROM openjdk:8-jre-slim

EXPOSE 8080
COPY --from=build /build/target/wordstats-0.0.1-SNAPSHOT.war /wordstats.war

ENTRYPOINT ["java","-jar","/wordstats.war"]
