# Build stage
FROM maven:3.9.4-eclipse-temurin-17 AS build
WORKDIR /app

COPY . .

RUN mvn clean package -pl devices/oven -am -DskipTests

# Run stage
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/devices/oven/target/oven-1.0-SNAPSHOT.jar app.jar
EXPOSE 50053
CMD ["java", "-jar", "app.jar"]
