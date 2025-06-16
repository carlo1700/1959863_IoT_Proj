FROM maven:3.9.4-eclipse-temurin-17 AS build
WORKDIR /app

# Copia tutto il progetto tranne target (per evitare di copiare file di build locali)
COPY . .

RUN mvn clean package -pl devices/light -am -DskipTests

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/devices/light/target/light-1.0-SNAPSHOT.jar app.jar
EXPOSE 50052
CMD ["java", "-jar", "app.jar"]
