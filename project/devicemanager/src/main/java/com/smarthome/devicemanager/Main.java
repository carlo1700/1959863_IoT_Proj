package com.smarthome.devicemanager;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Main {
    private static final int PORT = 50051;

    public static void main(String[] args) throws IOException, InterruptedException {
        // Avvio dell'app Spring Boot per le REST API
        ConfigurableApplicationContext context = SpringApplication.run(Main.class, args);

        // Ottieni il bean gestito da Spring
        DeviceManagerServiceImpl grpcService = context.getBean(DeviceManagerServiceImpl.class);

        // Avvio del server gRPC in un thread separato
        new Thread(() -> {
            try {
                Server server = ServerBuilder.forPort(PORT)
                        .addService(grpcService) // bean Spring
                        .build()
                        .start();

                System.out.println("Device Manager gRPC server started on port " + PORT);

                Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                    System.err.println("*** shutting down gRPC server since JVM is shutting down");
                    try {
                        server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
                    } catch (InterruptedException e) {
                        e.printStackTrace(System.err);
                    }
                    System.err.println("*** server shut down");
                }));

                server.awaitTermination();
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
