package com.smarthome.devicemanager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.smarthome.proto.Device;
import com.smarthome.proto.DeviceManagerServiceGrpc;
import com.smarthome.proto.LightServiceGrpc;
import com.smarthome.proto.ListDevicesRequest;
import com.smarthome.proto.ListDevicesResponse;
import com.smarthome.proto.RegisterDeviceRequest;
import com.smarthome.proto.RegisterDeviceResponse;
import com.smarthome.proto.SendCommandRequest;
import com.smarthome.proto.SendCommandResponse;
import com.smarthome.proto.TurnOnRequest;
import com.smarthome.proto.TurnOnResponse;
import com.smarthome.proto.UnregisterDeviceRequest;
import com.smarthome.proto.UnregisterDeviceResponse;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;


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

    public List<Device> getDevices() {
        return new ArrayList<>(devices.values());
    }
    
    public String turnOnLight(String deviceId) {
        Device device = devices.get(deviceId);
        if (device == null) {
            return "Device not found: " + deviceId;
        }

        ManagedChannel channel = channels.get(deviceId);
        if (channel == null || channel.isShutdown()) {
            return "gRPC channel non disponibile per device: " + deviceId;
        }

        // 2. Crea lo stub blocking per LightService
        LightServiceGrpc.LightServiceBlockingStub lightStub =
            LightServiceGrpc.newBlockingStub(channel);

        // 3. Costruisci la richiesta TurnOn
        TurnOnRequest request = TurnOnRequest.newBuilder().build();

        // 4. Invia il comando e gestisci eventuali errori
        try {
            TurnOnResponse resp = lightStub.turnOn(request);
            if (resp.getSuccess()) {
                // 5. Restituisci il messaggio di conferma
                return "Light turned on for device " + deviceId + ": " + resp.getMessage();
            } else {
                return "Failed to turn on light for device " + deviceId + ": " + resp.getMessage();
            }
        } catch (StatusRuntimeException e) {
            return "gRPC error on turnOn for device " + deviceId + ": " + e.getStatus().getDescription();
        }
        
    }

}