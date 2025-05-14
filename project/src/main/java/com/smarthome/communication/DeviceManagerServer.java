package com.smarthome.communication;

import com.smarthome.grpc.*;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The central device manager server that communicates with all devices.
 */
public class DeviceManagerServer {
    private static final Logger logger = LoggerFactory.getLogger(DeviceManagerServer.class);
    
    private final int port;
    private final Server server;
    
    // Store registered devices and their information
    private final Map<String, DeviceInfo> registeredDevices = new ConcurrentHashMap<>();
    
    /**
     * Create a new device manager server
     * @param port Port to listen on
     */
    public DeviceManagerServer(int port) {
        this.port = port;
        this.server = ServerBuilder.forPort(port)
                .addService(new DeviceServiceImpl())
                .build();
    }
    
    /**
     * Start the server
     * @throws IOException if the server cannot be started
     */
    public void start() throws IOException {
        server.start();
        logger.info("Device Manager Server started, listening on port {}", port);
        
        // Add shutdown hook to gracefully stop the server
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            logger.info("Shutting down Device Manager Server");
            DeviceManagerServer.this.stop();
            logger.info("Device Manager Server shut down");
        }));
    }
    
    /**
     * Stop the server
     */
    public void stop() {
        if (server != null) {
            server.shutdown();
        }
    }
    
    /**
     * Block until the server is terminated
     * @throws InterruptedException if the thread is interrupted
     */
    public void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }
    
    /**
     * Information about a registered device
     */
    private static class DeviceInfo {
        String deviceId;
        String deviceType;
        String ipAddress;
        int port;
        String registrationToken;
        Map<String, String> metadata;
        Map<String, String> lastKnownState;
        long lastSeen;
        
        DeviceInfo(String deviceId, String deviceType, String ipAddress, int port, 
                   String registrationToken, Map<String, String> metadata) {
            this.deviceId = deviceId;
            this.deviceType = deviceType;
            this.ipAddress = ipAddress;
            this.port = port;
            this.registrationToken = registrationToken;
            this.metadata = metadata;
            this.lastKnownState = new HashMap<>();
            this.lastSeen = System.currentTimeMillis();
        }
    }
    
    /**
     * Implementation of the DeviceService for handling device communications
     */
    private class DeviceServiceImpl extends DeviceServiceGrpc.DeviceServiceImplBase {
        
        @Override
        public void getStatus(StatusRequest request, StreamObserver<StatusResponse> responseObserver) {
            String deviceId = request.getDeviceId();
            logger.debug("Received status request for device: {}", deviceId);
            
            DeviceInfo deviceInfo = registeredDevices.get(deviceId);
            StatusResponse.Builder responseBuilder = StatusResponse.newBuilder()
                    .setDeviceId(deviceId)
                    .setDeviceType(request.getDeviceType());
            
            if (deviceInfo != null) {
                // Update last seen timestamp
                deviceInfo.lastSeen = System.currentTimeMillis();
                
                responseBuilder.setIsOnline(true)
                        .putAllProperties(deviceInfo.lastKnownState);
            } else {
                responseBuilder.setIsOnline(false)
                        .setErrorMessage("Device not registered");
            }
            
            responseObserver.onNext(responseBuilder.build());
            responseObserver.onCompleted();
        }
        
        @Override
        public void updateState(StateUpdateRequest request, StreamObserver<StateUpdateResponse> responseObserver) {
            String deviceId = request.getDeviceId();
            logger.debug("Received state update for device: {}", deviceId);
            
            DeviceInfo deviceInfo = registeredDevices.get(deviceId);
            StateUpdateResponse.Builder responseBuilder = StateUpdateResponse.newBuilder()
                    .setDeviceId(deviceId);
            
            if (deviceInfo != null) {
                // Update last seen timestamp and state
                deviceInfo.lastSeen = System.currentTimeMillis();
                deviceInfo.lastKnownState.putAll(request.getPropertiesMap());
                
                responseBuilder.setSuccess(true);
                logger.info("Updated state for device {}: {}", deviceId, request.getPropertiesMap());
            } else {
                responseBuilder.setSuccess(false)
                        .setErrorMessage("Device not registered");
                logger.warn("Attempted state update for unregistered device: {}", deviceId);
            }
            
            responseObserver.onNext(responseBuilder.build());
            responseObserver.onCompleted();
        }
        
        @Override
        public void registerDevice(RegistrationRequest request, StreamObserver<RegistrationResponse> responseObserver) {
            String deviceId = request.getDeviceId();
            String deviceType = request.getDeviceType();
            logger.info("Received registration for device: {} ({})", deviceId, deviceType);
            
            RegistrationResponse.Builder responseBuilder = RegistrationResponse.newBuilder()
                    .setDeviceId(deviceId);
            
            // Generate a unique token for this device
            String token = UUID.randomUUID().toString();
            
            // Store the device information
            DeviceInfo deviceInfo = new DeviceInfo(
                    deviceId,
                    deviceType,
                    request.getIpAddress(),
                    request.getPort(),
                    token,
                    request.getMetadataMap()
            );
            
            registeredDevices.put(deviceId, deviceInfo);
            
            responseBuilder.setSuccess(true)
                    .setRegistrationToken(token);
            
            logger.info("Device registered successfully: {} ({})", deviceId, deviceType);
            
            responseObserver.onNext(responseBuilder.build());
            responseObserver.onCompleted();
        }
        
        @Override
        public void executeCommand(CommandRequest request, StreamObserver<CommandResponse> responseObserver) {
            String deviceId = request.getDeviceId();
            String command = request.getCommand();
            logger.debug("Received command execution request for device {}: {}", deviceId, command);
            
            DeviceInfo deviceInfo = registeredDevices.get(deviceId);
            CommandResponse.Builder responseBuilder = CommandResponse.newBuilder()
                    .setDeviceId(deviceId);
            
            if (deviceInfo != null) {
                // In a real implementation, we would connect to the device and send the command
                // For the simulation, we'll just log the command and return a success response
                
                deviceInfo.lastSeen = System.currentTimeMillis();
                
                responseBuilder.setSuccess(true)
                        .putResult("message", "Command executed successfully");
                
                logger.info("Executed command {} on device {}", command, deviceId);
            } else {
                responseBuilder.setSuccess(false)
                        .setErrorMessage("Device not registered");
                
                logger.warn("Attempted to execute command on unregistered device: {}", deviceId);
            }
            
            responseObserver.onNext(responseBuilder.build());
            responseObserver.onCompleted();
        }
    }
}