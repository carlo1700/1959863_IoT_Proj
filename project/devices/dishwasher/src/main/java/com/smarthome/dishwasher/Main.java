package com.smarthome.dishwasher;

import com.smarthome.proto.*;
import com.smarthome.proto.DeviceManagerServiceGrpc.DeviceManagerServiceBlockingStub;

import io.grpc.ConnectivityState;
import io.grpc.ManagedChannel;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.netty.NettyChannelBuilder;
import java.net.InetSocketAddress;

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
        long delayMillis = 500;

        for (int attempt = 1; attempt <= maxAttempts; attempt++) {
            ManagedChannel channel = NettyChannelBuilder
                    .forAddress(new InetSocketAddress("devicemanager", 50051))
                    .usePlaintext()
                    .build();

            ConnectivityState state = channel.getState(true);
            System.out.println("gRPC channel state: " + state);

            if (state != ConnectivityState.READY) {
                System.out.println("Channel not ready, waiting...");
                try {
                    channel.awaitTermination(2, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            DeviceManagerServiceBlockingStub stub = DeviceManagerServiceGrpc
                    .newBlockingStub(channel)
                    .withDeadlineAfter(15, TimeUnit.SECONDS);

            try {
                System.out.println("Attempt " + attempt + " to register with Device Manager...");

                RegisterDeviceRequest request = RegisterDeviceRequest.newBuilder()
                        .setDeviceId("dishwasher1")
                        .setDeviceType("DISHWASHER")
                        .setAddress("dishwasher")
                        .setPort(PORT)
                        .build();

                RegisterDeviceResponse response = stub.registerDevice(request);
                System.out.println("Device registered: " + response.getMessage());
                return;

            } catch (Exception e) {
                System.err.println(
                        "Attempt " + attempt + " failed: " + e.getClass().getSimpleName() + " - " + e.getMessage());
                e.printStackTrace();
            } finally {
                channel.shutdown();
            }

            try {
                Thread.sleep(delayMillis);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
                break;
            }
            delayMillis = Math.min(delayMillis * 2, 5000);
        }

        System.err.println("Device registration failed after " + maxAttempts + " attempts.");
    }

}