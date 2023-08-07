FROM maven:3.8.6-eclipse-temurin-17 AS build
COPY /src /src
COPY pom.xml .
RUN mvn  clean install
FROM openjdk:17-alpine
COPY --from=build target/*.jar app.jar
EXPOSE 8082
ENTRYPOINT ["java","-jar","/app.jar"]