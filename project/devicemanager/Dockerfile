# Build stage
FROM maven:3.9.4-eclipse-temurin-17 AS build

# Scarica grpc-health-probe (statico, dovrebbe girare anche su Alpine)
RUN curl -L -o /bin/grpc-health-probe \
      https://github.com/grpc-ecosystem/grpc-health-probe/releases/download/v0.4.39/grpc_health_probe-linux-amd64 \
    && chmod +x /bin/grpc-health-probe

WORKDIR /app
COPY . .
RUN mvn clean package -pl devicemanager -am -DskipTests

# Run stage
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Copia la jar del device-manager
COPY --from=build /app/devicemanager/target/devicemanager-1.0-SNAPSHOT.jar app.jar

# Copia il grpc-health-probe dal build stage
COPY --from=build /bin/grpc-health-probe /usr/local/bin/grpc-health-probe
RUN chmod +x /usr/local/bin/grpc-health-probe

EXPOSE 50051
EXPOSE 8080

ENTRYPOINT ["java", "-Djava.net.preferIPv4Stack=true", "-jar", "app.jar"]