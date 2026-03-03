# Building
FROM maven:3-eclipse-temurin-25 AS build

LABEL FULL_NAME="Fernando Murillo Noya"
LABEL EMAIL_MAINTAINER="fernando.murillo.noya@gmail.com"
LABEL MICROSERVICE="Contract Services"
LABEL COURSE_WORK_GROUP="Grupo 1"

WORKDIR /workspace

COPY pom.xml ./
COPY .mvn/ .mvn/
COPY mvnw .
COPY src ./src

RUN --mount=type=cache,target=/root/.m2 mvn clean package

# Running
FROM eclipse-temurin:25-alpine

WORKDIR /app

ENV APP_PORT=8440

ARG JAR_FILE=target/*.jar

COPY --from=build /workspace/${JAR_FILE} /app/app.jar

EXPOSE ${APP_PORT}

ENTRYPOINT ["java", "-Dspring.profiles.active=local", "-Dspring.datasource.url=jdbc:postgresql://localhost:5432/contract_services", "-jar", "/app/app.jar"]