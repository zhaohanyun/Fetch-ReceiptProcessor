FROM maven:3.8.5-eclipse-temurin-17-alpine AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/Fetch-ReceiptProcessor.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
