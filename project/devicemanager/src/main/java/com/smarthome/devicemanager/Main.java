package com.smarthome.devicemanager;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.health.v1.HealthCheckResponse;
import io.grpc.protobuf.services.HealthStatusManager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Main {
    private static final int PORT = 50051;

    public static void main(String[] args) throws IOException, InterruptedException {
        // Avvio dell'app Spring Boot per le REST API
        ConfigurableApplicationContext context = SpringApplication.run(Main.class, args);

        System.out.println("✅ Spring Boot application started");

        try {
            // Ottieni il bean gestito da Spring
            DeviceManagerServiceImpl grpcService = context.getBean(DeviceManagerServiceImpl.class);
            HealthStatusManager healthStatusManager = new HealthStatusManager();

            // Costruisci e avvia il server gRPC
            Server server = ServerBuilder.forPort(PORT)
                    .addService(grpcService)
                    .addService(healthStatusManager.getHealthService())
                    .build()
                    .start();

            healthStatusManager.setStatus("", HealthCheckResponse.ServingStatus.SERVING);
            System.out.println("✅ Device Manager gRPC server started on port " + PORT);

            // Hook di shutdown
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                System.err.println("*** Shutting down gRPC server since JVM is shutting down");
                try {
                    server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace(System.err);
                }
                System.err.println("*** Server shut down");
            }));

            // Mantieni attivo il server
            server.awaitTermination();

        } catch (Exception e) {
            System.err.println("❌ Failed to start gRPC server: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
