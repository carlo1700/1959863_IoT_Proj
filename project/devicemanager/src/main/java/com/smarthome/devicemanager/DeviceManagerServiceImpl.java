package com.smarthome.devicemanager;

import com.smarthome.proto.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

public class DeviceManagerServiceImpl extends DeviceManagerServiceGrpc.DeviceManagerServiceImplBase {
    
    private final Map<String, Device> devices = new ConcurrentHashMap<>();
    private final Map<String, ManagedChannel> channels = new ConcurrentHashMap<>();
    
    @Override
    public void registerDevice(RegisterDeviceRequest request, StreamObserver<RegisterDeviceResponse> responseObserver) {
        Device device = Device.newBuilder()
                .setDeviceId(request.getDeviceId())
                .setDeviceType(request.getDeviceType())
                .setAddress(request.getAddress())
                .setPort(request.getPort())
                .setOnline(true)
                .build();
        
        devices.put(request.getDeviceId(), device);
        
        // Create gRPC channel for the device
        ManagedChannel channel = ManagedChannelBuilder.forAddress(request.getAddress(), request.getPort())
                .usePlaintext()
                .build();
        channels.put(request.getDeviceId(), channel);
        
        RegisterDeviceResponse response = RegisterDeviceResponse.newBuilder()
                .setSuccess(true)
                .setMessage("Device registered: " + request.getDeviceId())
                .build();
        
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
    
    @Override
    public void unregisterDevice(UnregisterDeviceRequest request, StreamObserver<UnregisterDeviceResponse> responseObserver) {
        devices.remove(request.getDeviceId());
        
        ManagedChannel channel = channels.remove(request.getDeviceId());
        if (channel != null) {
            channel.shutdown();
        }
        
        UnregisterDeviceResponse response = UnregisterDeviceResponse.newBuilder()
                .setSuccess(true)
                .setMessage("Device unregistered: " + request.getDeviceId())
                .build();
        
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
    
    @Override
    public void listDevices(ListDevicesRequest request, StreamObserver<ListDevicesResponse> responseObserver) {
        ListDevicesResponse.Builder responseBuilder = ListDevicesResponse.newBuilder();
        responseBuilder.addAllDevices(devices.values());
        
        responseObserver.onNext(responseBuilder.build());
        responseObserver.onCompleted();
    }
    
    @Override
    public void sendCommand(SendCommandRequest request, StreamObserver<SendCommandResponse> responseObserver) {
        Device device = devices.get(request.getDeviceId());
        if (device == null) {
            SendCommandResponse response = SendCommandResponse.newBuilder()
                    .setSuccess(false)
                    .setMessage("Device not found: " + request.getDeviceId())
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
            return;
        }
        
        // Here you would implement the actual command forwarding to the device
        // For now, just return a success response
        SendCommandResponse response = SendCommandResponse.newBuilder()
                .setSuccess(true)
                .setMessage("Command sent to device: " + request.getDeviceId())
                .build();
        
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}