# Build stage

FROM maven:3.9.4-eclipse-temurin-17 AS build

RUN curl -L -o /bin/grpc-health-probe https://github.com/grpc-ecosystem/grpc-health-probe/releases/download/v0.4.39/grpc-health-probe-linux-amd64 \
    && chmod +x /bin/grpc-health-probe

WORKDIR /app

COPY . .

RUN mvn clean package -pl devicemanager -am -DskipTests

# Run stage
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/devicemanager/target/devicemanager-1.0-SNAPSHOT.jar app.jar
EXPOSE 50051
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
