FROM python:3.10

# Install grpc-health-probe
ADD https://github.com/grpc-ecosystem/grpc-health-probe/releases/download/v0.4.11/grpc_health_probe-linux-amd64 /bin/grpc_health_probe
RUN chmod +x /bin/grpc_health_probe


# Build stage
FROM maven:3.9.4-eclipse-temurin-17 AS build
WORKDIR /app

COPY . .

RUN mvn clean package -pl devices/dishwasher -am -DskipTests

# Run stage
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/devices/dishwasher/target/dishwasher-1.0-SNAPSHOT.jar app.jar
EXPOSE 50059
CMD ["java", "-Djava.net.preferIPv4Stack=true", "-jar", "app.jar"]

