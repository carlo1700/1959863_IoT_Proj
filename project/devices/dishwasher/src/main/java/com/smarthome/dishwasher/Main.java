package com.smarthome.dishwasher;

import com.smarthome.proto.*;

import io.grpc.ConnectivityState;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Main {
    private static final int PORT = 50059;

    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder.forPort(PORT)
                .addService(new DishwasherServiceImpl())
                .build()
                .start();

        System.out.println("Dishwasher gRPC server started on port " + PORT);

        // Registrazione al Device Manager
        registerWithDeviceManager();

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
    }

    private static void registerWithDeviceManager() {
        final int maxAttempts = 20;
        final int delayMillis = 2000;

        for (int attempt = 1; attempt <= maxAttempts; attempt++) {
            ManagedChannel channel = null;
            try {
                channel = ManagedChannelBuilder
                        .forAddress("devicemanager", 50051)
                        .usePlaintext()
                        .build();

                // Attendi fino a 10 secondi o finché il canale diventa READY
                boolean isReady = false;
                for (int i = 0; i < 10; i++) {
                    ConnectivityState state = channel.getState(true);
                    if (state == ConnectivityState.READY) {
                        isReady = true;
                        break;
                    }
                    Thread.sleep(1000);
                }

                if (!isReady) {
                    throw new IllegalStateException("Channel not ready");
                }

                DeviceManagerServiceGrpc.DeviceManagerServiceBlockingStub stub = DeviceManagerServiceGrpc
                        .newBlockingStub(channel);

                RegisterDeviceRequest request = RegisterDeviceRequest.newBuilder()
                        .setDeviceId("dishwasher1")
                        .setDeviceType("dishwasher")
                        .setAddress("dishwasher")
                        .setPort(PORT)
                        .build();

                RegisterDeviceResponse response = stub.registerDevice(request);
                System.out.println("Device registered: " + response.getMessage());
                return;

            } catch (Exception e) {
                System.err.println("Attempt " + attempt + " failed: " + e.getMessage());
            } finally {
                if (channel != null) {
                    channel.shutdown();
                }
            }

            try {
                Thread.sleep(delayMillis);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.err.println("Device registration failed after " + maxAttempts + " attempts.");
    }

}