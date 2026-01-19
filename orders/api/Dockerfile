# multi-stage Dockerfile
FROM gradle:8.6-jdk17 AS builder
WORKDIR /home/gradle/project
COPY --chown=gradle:gradle . /home/gradle/project
RUN gradle :orders:api:bootJar --no-daemon -x test

FROM eclipse-temurin:17-jre
ARG JAR_FILE=/home/gradle/project/orders/api/build/libs/orders-api-0.1.0-SNAPSHOT.jar
COPY --from=builder ${JAR_FILE} /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]