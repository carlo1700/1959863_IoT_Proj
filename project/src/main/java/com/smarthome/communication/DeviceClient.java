package com.smarthome.communication;

import com.smarthome.core.Device;
import com.smarthome.grpc.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Client for communicating with the device manager via gRPC.
 */
public class DeviceClient {
    private static final Logger logger = LoggerFactory.getLogger(DeviceClient.class);
    
    private final String host;
    private final int port;
    private final Device device;
    private ManagedChannel channel;
    private DeviceServiceGrpc.DeviceServiceBlockingStub blockingStub;
    
    /**
     * Create a new device client
     * @param host Host where the device manager is running
     * @param port Port where the device manager is listening
     * @param device The device this client is for
     */
    public DeviceClient(String host, int port, Device device) {
        this.host = host;
        this.port = port;
        this.device = device;
    }
    
    /**
     * Connect to the device manager
     */
    private void connect() {
        if (channel == null || channel.isShutdown()) {
            channel = ManagedChannelBuilder.forAddress(host, port)
                    .usePlaintext()
                    .build();
            blockingStub = DeviceServiceGrpc.newBlockingStub(channel);
            logger.info("Connected to device manager at {}:{}", host, port);
        }
    }
    
    /**
     * Disconnect from the device manager
     */
    public void disconnect() {
        if (channel != null && !channel.isShutdown()) {
            try {
                channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
                logger.info("Disconnected from device manager");
            } catch (InterruptedException e) {
                logger.warn("Error disconnecting from device manager", e);
                Thread.currentThread().interrupt();
            }
        }
    }
    
    /**
     * Register this device with the device manager
     * @return true if registration was successful, false otherwise
     */
    public boolean register() {
        connect();
        
        try {
            RegistrationRequest request = RegistrationRequest.newBuilder()
                    .setDeviceId(device.getDeviceId())
                    .setDeviceType(device.getDeviceType())
                    .setIpAddress("localhost") // In a real system, this would be the actual IP
                    .setPort(0) // In a real system, this would be the port the device is listening on
                    .build();
            
            RegistrationResponse response = blockingStub.registerDevice(request);
            if (response.getSuccess()) {
                logger.info("Device {} registered successfully with token: {}", 
                        device.getDeviceId(), response.getRegistrationToken());
                return true;
            } else {
                logger.error("Failed to register device {}: {}", 
                        device.getDeviceId(), response.getErrorMessage());
                return false;
            }
        } catch (StatusRuntimeException e) {
            logger.error("RPC failed during device registration: {}", e.getStatus());
            return false;
        }
    }
    
    /**
     * Send a status update to the device manager
     * @return true if update was sent successfully, false otherwise
     */
    public boolean sendStatusUpdate() {
        if (!device.isOnline()) {
            return false;
        }
        
        connect();
        
        try {
            Map<String, String> statusProps = device.getStatus();
            StatusRequest request = StatusRequest.newBuilder()
                    .setDeviceId(device.getDeviceId())
                    .setDeviceType(device.getDeviceType())
                    .build();
            
            StatusResponse response = blockingStub.getStatus(request);
            logger.debug("Status update sent for device {}", device.getDeviceId());
            return true;
        } catch (StatusRuntimeException e) {
            logger.error("RPC failed during status update: {}", e.getStatus());
            return false;
        }
    }
    
    /**
     * Execute a command requested by the device manager
     * @param command The command to execute
     * @param parameters Parameters for the command
     * @return The command result or null if execution failed
     */
    public Map<String, String> executeCommand(String command, Map<String, String> parameters) {
        if (!device.isOnline()) {
            return null;
        }
        
        try {
            // Forward the command to the device to execute
            return device.executeCommand(command, parameters);
        } catch (Exception e) {
            logger.error("Error executing command {} on device {}: {}", 
                    command, device.getDeviceId(), e.getMessage());
            return null;
        }
    }
}