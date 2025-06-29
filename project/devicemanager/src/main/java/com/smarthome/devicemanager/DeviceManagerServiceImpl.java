package com.smarthome.devicemanager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.smarthome.proto.*;

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
    public void unregisterDevice(UnregisterDeviceRequest request,
            StreamObserver<UnregisterDeviceResponse> responseObserver) {
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

    public String turnOnDevice(String deviceId) {
        Device device = devices.get(deviceId);
        if (device == null) {
            return "Device not found: " + deviceId;
        }

        ManagedChannel channel = channels.get(deviceId);
        if (channel == null || channel.isShutdown()) {
            return "gRPC channel non disponibile per device: " + deviceId;
        }

        String deviceType = device.getDeviceType();

        try {
            switch (deviceType.toUpperCase()) {

                case "LIGHT":
                    LightServiceGrpc.LightServiceBlockingStub lightStub = LightServiceGrpc.newBlockingStub(channel);
                    TurnOnResponse lightResp = lightStub.turnOn(TurnOnRequest.newBuilder().build());
                    return lightResp.getSuccess()
                            ? "Light turned on: " + lightResp.getMessage()
                            : "Failed to turn on light: " + lightResp.getMessage();

                case "THERMOSTAT":
                    ThermostatServiceGrpc.ThermostatServiceBlockingStub thermostatStub = ThermostatServiceGrpc
                            .newBlockingStub(channel);
                    ThermostatTurnOnResponse heatResp = thermostatStub
                            .turnOn(ThermostatTurnOnRequest.newBuilder().build());
                    return heatResp.getSuccess()
                            ? "Thermostat heating started: " + heatResp.getMessage()
                            : "Failed to start thermostat: " + heatResp.getMessage();

                case "COIL":
                    CoilServiceGrpc.CoilServiceBlockingStub coilStub = CoilServiceGrpc.newBlockingStub(channel);
                    CoilTurnOnResponse coilResp = coilStub.turnOn(CoilTurnOnRequest.newBuilder().build());
                    return coilResp.getSuccess()
                            ? "Coil activated: " + coilResp.getMessage()
                            : "Failed to activate coil: " + coilResp.getMessage();

                case "DISHWASHER":
                    DishwasherServiceGrpc.DishwasherServiceBlockingStub dishwasherStub = DishwasherServiceGrpc
                            .newBlockingStub(channel);
                    DishwasherTurnOnResponse dishResp = dishwasherStub.turnOn(DishwasherTurnOnRequest.newBuilder().build());
                    return dishResp.getSuccess()
                            ? "Dishwasher started: " + dishResp.getMessage()
                            : "Failed to start dishwasher: " + dishResp.getMessage();

                case "MOTIONSENSOR":
                    MotionSensorServiceGrpc.MotionSensorServiceBlockingStub motionStub = MotionSensorServiceGrpc
                            .newBlockingStub(channel);
                    MotionSensorTurnOnResponse motionResp = motionStub
                            .turnOn(MotionSensorTurnOnRequest.newBuilder().build());
                    return motionResp.getSuccess()
                            ? "Motion sensor enabled: " + motionResp.getMessage()
                            : "Failed to enable motion sensor: " + motionResp.getMessage();

                case "OVEN":
                    OvenServiceGrpc.OvenServiceBlockingStub ovenStub = OvenServiceGrpc.newBlockingStub(channel);
                    OvenTurnOnResponse ovenResp = ovenStub.turnOn(OvenTurnOnRequest.newBuilder().build());
                    return ovenResp.getSuccess()
                            ? "Oven preheating: " + ovenResp.getMessage()
                            : "Failed to preheat oven: " + ovenResp.getMessage();

                case "SOLARPANEL":
                    SolarPanelServiceGrpc.SolarPanelServiceBlockingStub solarStub = SolarPanelServiceGrpc
                            .newBlockingStub(channel);
                    SolarPanelTurnOnResponse solarResp = solarStub
                            .turnOn(SolarPanelTurnOnRequest.newBuilder().build());
                    return solarResp.getSuccess()
                            ? "Solar panel activated: " + solarResp.getMessage()
                            : "Failed to activate solar panel: " + solarResp.getMessage();

                case "WASHINGMACHINE":
                    WashingMachineServiceGrpc.WashingMachineServiceBlockingStub washStub = WashingMachineServiceGrpc
                            .newBlockingStub(channel);
                    WashingMachineTurnOnResponse washResp = washStub.turnOn(WashingMachineTurnOnRequest.newBuilder().build());
                    return washResp.getSuccess()
                            ? "Washing machine cycle started: " + washResp.getMessage()
                            : "Failed to start washing machine: " + washResp.getMessage();

                default:
                    return "Device type not supported: " + deviceType;
            }

        } catch (StatusRuntimeException e) {
            return "gRPC error on device " + deviceId + ": " + e.getStatus().getDescription();
        }
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
        LightServiceGrpc.LightServiceBlockingStub lightStub = LightServiceGrpc.newBlockingStub(channel);

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