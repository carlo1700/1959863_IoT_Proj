package com.smarthome.devicemanager;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.Set;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.function.Function;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.smarthome.proto.*;

import io.grpc.ConnectivityState;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.netty.shaded.io.grpc.netty.NettyChannelBuilder;
import io.grpc.stub.StreamObserver;

import com.smarthome.logging.DeviceEventRepository;
import com.smarthome.logging.PgDataSource;
import com.smarthome.logging.RoomGroupRepository;
import com.smarthome.logging.DeviceAliasRepository;

import jakarta.annotation.PostConstruct;

@Service
public class DeviceManagerServiceImpl extends DeviceManagerServiceGrpc.DeviceManagerServiceImplBase {
    // repo persistente
    private final RoomGroupRepository rgRepo = new RoomGroupRepository(PgDataSource.get());

    // cache in-memory persistita
    private final ConcurrentHashMap<String, Set<String>> rooms  = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, Set<String>> groups = new ConcurrentHashMap<>();

    private final Map<String, Device> devices = new ConcurrentHashMap<>();
    private final Map<String, ManagedChannel> channels = new ConcurrentHashMap<>();

    // ====== LOGGING ======
    private final DeviceEventRepository repo = new DeviceEventRepository(PgDataSource.get());

    private final DeviceAliasRepository aliasRepo = new DeviceAliasRepository(PgDataSource.get());

    private final ConcurrentHashMap<String, String> displayNames = new ConcurrentHashMap<>();

    private void log(String deviceId, String action, String status, String payloadJson, String errorMsg) {
        try {
                repo.insertEvent(
                deviceId,                         // device_id
                "DeviceManager",                  // source
                action,                           // action (es. "TurnOn")
                status,                           // status (PENDING/SUCCESS/FAILURE)
                currentUser(),                    // user_name
                (payloadJson == null || payloadJson.isBlank()) ? null : payloadJson, // JSONB
                errorMsg                          // error_msg
                );
        } catch (Exception e) {
                System.err.println("DB log error [" + action + "@" + deviceId + "]: " + e.getMessage());
        }
    }

    private String currentUser() {
        // Se in futuro hai un utente reale, rimpiazza questo metodo.
        return "system";
    }

    @Override
        public void registerDevice(RegisterDeviceRequest request, StreamObserver<RegisterDeviceResponse> responseObserver) {
        String address = request.getAddress();
        int port = request.getPort();
        String deviceId = request.getDeviceId();
        String deviceType = request.getDeviceType();
        String normalizedType = deviceType.replace("_", "");
        String payload = String.format("{\"type\":\"%s\",\"address\":\"%s\",\"port\":%d}", deviceType, request.getAddress(), request.getPort());
        log(deviceId, "Register", "PENDING", payload, null);

        try {
                Device device = Device.newBuilder()
                        .setDeviceId(deviceId)
                        .setDeviceType(normalizedType)
                        .setAddress(request.getAddress())
                        .setPort(request.getPort())
                        .setOnline(true)
                        .build();
                devices.put(deviceId, device);

                ManagedChannel old = channels.remove(deviceId);
                if (old != null && !old.isShutdown()) {
                try { old.shutdownNow(); } catch (Exception ignore) {}
                }
                InetSocketAddress socketAddress = new InetSocketAddress(address, port);

                // Creazione canale gRPC con Netty
                ManagedChannel channel = NettyChannelBuilder.forAddress(socketAddress)
                        .usePlaintext()
                        .keepAliveTime(30, TimeUnit.SECONDS)
                        .keepAliveTimeout(10, TimeUnit.SECONDS)
                        .build();
                // Aggiungi un watcher per tracciare lo stato della connessione
                channel.notifyWhenStateChanged(channel.getState(true), () -> {
                        ConnectivityState newState = channel.getState(false);
                        System.out.println("🔌 Channel state for device " + request.getDeviceId() + ": " + newState);
                        });
                channels.put(request.getDeviceId(), channel);

                RegisterDeviceResponse response = RegisterDeviceResponse.newBuilder()
                        .setSuccess(true)
                        .setMessage("Device registered: " + deviceId)
                        .build();
                log(deviceId, "Register", "SUCCESS", payload, null);

                responseObserver.onNext(response);
                responseObserver.onCompleted();

                System.out.println("✅ Device " + request.getDeviceId() +
                " registered at " + request.getAddress() + ":" + request.getPort());
        } catch (Exception e) {
                log(deviceId, "Register", "FAILURE", payload, e.getMessage());
                responseObserver.onNext(RegisterDeviceResponse.newBuilder()
                        .setSuccess(false)
                        .setMessage("Register failed: " + e.getMessage())
                        .build());
                responseObserver.onCompleted();
        }
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

        // log
        log(request.getDeviceId(), "Unregister", "SUCCESS", "{}", null);

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
            log(request.getDeviceId(), "SendCommand", "FAILURE", "{}", "Device not found");
            responseObserver.onNext(response);
            responseObserver.onCompleted();
            return;
        }

        // (placeholder di inoltro comando)
        SendCommandResponse response = SendCommandResponse.newBuilder()
                .setSuccess(true)
                .setMessage("Command sent to device: " + request.getDeviceId())
                .build();

        log(request.getDeviceId(), "SendCommand", "SUCCESS", "{}", null);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    public List<Device> getDevices() {
        return new ArrayList<>(devices.values());
    }

    public String turnOnDevice(String deviceId) {
        Device device = devices.get(deviceId);
        if (device == null) {
            log(deviceId, "TurnOn", "FAILURE", "{}", "Device not found");
            return "Device not found: " + deviceId;
        }

        ManagedChannel channel = channels.get(deviceId);
        if (channel == null || channel.isShutdown()) {
            log(deviceId, "TurnOn", "FAILURE", "{}", "Channel unavailable");
            return "gRPC channel non disponibile per device: " + deviceId;
        }

        String deviceType = device.getDeviceType();
        log(deviceId, "TurnOn", "PENDING", "{}", null);

        try {
            switch (deviceType.toUpperCase()) {

                case "LIGHT": {
                    LightServiceGrpc.LightServiceBlockingStub lightStub = LightServiceGrpc.newBlockingStub(channel);
                    TurnOnResponse lightResp = lightStub.turnOn(TurnOnRequest.newBuilder().build());
                    String msg = lightResp.getSuccess()
                            ? "Light turned on: " + lightResp.getMessage()
                            : "Failed to turn on light: " + lightResp.getMessage();
                    log(deviceId, "TurnOn", lightResp.getSuccess() ? "SUCCESS" : "FAILURE", "{}",
                            lightResp.getSuccess() ? null : lightResp.getMessage());
                    return msg;
                }

                case "AIRCONDITIONER": {
                    AirConditionerServiceGrpc.AirConditionerServiceBlockingStub airconditionerStub = AirConditionerServiceGrpc
                            .newBlockingStub(channel);
                    AirConditionerTurnOnResponse airconditionerResp = airconditionerStub
                            .turnOn(AirConditionerTurnOnRequest.newBuilder().build());
                    String msg = airconditionerResp.getSuccess()
                            ? "airconditioner activated: " + airconditionerResp.getMessage()
                            : "Failed to activate airconditioner: " + airconditionerResp.getMessage();
                    log(deviceId, "TurnOn", airconditionerResp.getSuccess() ? "SUCCESS" : "FAILURE", "{}",
                            airconditionerResp.getSuccess() ? null : airconditionerResp.getMessage());
                    return msg;
                }

                case "DISHWASHER": {
                    DishwasherServiceGrpc.DishwasherServiceBlockingStub dishwasherStub = DishwasherServiceGrpc
                            .newBlockingStub(channel);
                    DishwasherTurnOnResponse dishResp = dishwasherStub
                            .turnOn(DishwasherTurnOnRequest.newBuilder().build());
                    String msg = dishResp.getSuccess()
                            ? "Dishwasher started: " + dishResp.getMessage()
                            : "Failed to start dishwasher: " + dishResp.getMessage();
                    log(deviceId, "TurnOn", dishResp.getSuccess() ? "SUCCESS" : "FAILURE", "{}",
                            dishResp.getSuccess() ? null : dishResp.getMessage());
                    return msg;
                }

                case "MOTIONSENSOR": {
                    MotionSensorServiceGrpc.MotionSensorServiceBlockingStub motionStub = MotionSensorServiceGrpc
                            .newBlockingStub(channel);
                    MotionSensorTurnOnResponse motionResp = motionStub
                            .turnOn(MotionSensorTurnOnRequest.newBuilder().build());
                    String msg = motionResp.getSuccess()
                            ? "Motion sensor enabled: " + motionResp.getMessage()
                            : "Failed to enable motion sensor: " + motionResp.getMessage();
                    log(deviceId, "TurnOn", motionResp.getSuccess() ? "SUCCESS" : "FAILURE", "{}",
                            motionResp.getSuccess() ? null : motionResp.getMessage());
                    return msg;
                }

                case "OVEN": {
                    OvenServiceGrpc.OvenServiceBlockingStub ovenStub = OvenServiceGrpc.newBlockingStub(channel);
                    OvenTurnOnResponse ovenResp = ovenStub.turnOn(OvenTurnOnRequest.newBuilder().build());
                    String msg = ovenResp.getSuccess()
                            ? "Oven preheating: " + ovenResp.getMessage()
                            : "Failed to preheat oven: " + ovenResp.getMessage();
                    log(deviceId, "TurnOn", ovenResp.getSuccess() ? "SUCCESS" : "FAILURE", "{}",
                            ovenResp.getSuccess() ? null : ovenResp.getMessage());
                    return msg;
                }

                case "WASHINGMACHINE": {
                    WashingMachineServiceGrpc.WashingMachineServiceBlockingStub washStub = WashingMachineServiceGrpc
                            .newBlockingStub(channel);
                    WashingMachineTurnOnResponse washResp = washStub
                            .turnOn(WashingMachineTurnOnRequest.newBuilder().build());
                    String msg = washResp.getSuccess()
                            ? "Washing machine cycle started: " + washResp.getMessage()
                            : "Failed to start washing machine: " + washResp.getMessage();
                    log(deviceId, "TurnOn", washResp.getSuccess() ? "SUCCESS" : "FAILURE", "{}",
                            washResp.getSuccess() ? null : washResp.getMessage());
                    return msg;
                }

                default:
                    log(deviceId, "TurnOn", "FAILURE", "{}", "Unsupported type: " + deviceType);
                    return "Device type not supported: " + deviceType;
            }

        } catch (StatusRuntimeException e) {
            log(deviceId, "TurnOn", "FAILURE", "{}", e.getStatus().getDescription());
            return "gRPC error on device " + deviceId + ": " + e.getStatus().getDescription();
        } catch (Exception e) {
            log(deviceId, "TurnOn", "FAILURE", "{}", e.getMessage());
            return "Error on device " + deviceId + ": " + e.getMessage();
        }
    }

    public String SetUpBlind(String deviceId) {
        Device device = devices.get(deviceId);
        if (device == null) {
            log(deviceId, "SetUpBlind", "FAILURE", "{}", "Device not found");
            return "Device not found: " + deviceId;
        }

        ManagedChannel channel = channels.get(deviceId);
        if (channel == null || channel.isShutdown()) {
            log(deviceId, "SetUpBlind", "FAILURE", "{}", "Channel unavailable");
            return "gRPC channel non disponibile per device: " + deviceId;
        }

        if (!device.getDeviceType().equalsIgnoreCase("BLIND")) {
            log(deviceId, "SetUpBlind", "FAILURE", "{}", "Wrong type (not BLIND)");
            return "Device is not a blind: " + deviceId;
        }

        BlindServiceGrpc.BlindServiceBlockingStub blindStub = BlindServiceGrpc.newBlockingStub(channel);
        BlindSetUpRequest request = BlindSetUpRequest.newBuilder().build();

        log(deviceId, "SetUpBlind", "PENDING", "{}", null);

        try {
            BlindSetUpResponse resp = blindStub.setUp(request);
            String msg = resp.getSuccess()
                    ? "Light turned on for device " + deviceId + ": " + resp.getMessage()
                    : "Failed to turn on light for device " + deviceId + ": " + resp.getMessage();
            log(deviceId, "SetUpBlind", resp.getSuccess() ? "SUCCESS" : "FAILURE", "{}",
                    resp.getSuccess() ? null : resp.getMessage());
            return msg;
        } catch (StatusRuntimeException e) {
            log(deviceId, "SetUpBlind", "FAILURE", "{}", e.getStatus().getDescription());
            return "gRPC error on turnOn for device " + deviceId + ": " + e.getStatus().getDescription();
        }
    }

        public String SetDownBlind(String deviceId) {
                Device device = devices.get(deviceId);
                if (device == null) {
                log(deviceId, "TurnOff", "FAILURE", "{}", "Device not found");
                return "Device not found: " + deviceId;
                }

                ManagedChannel channel = channels.get(deviceId);
                if (channel == null || channel.isShutdown()) {
                log(deviceId, "TurnOff", "FAILURE", "{}", "Channel unavailable");
                return "gRPC channel non disponibile per device: " + deviceId;
                }

                if (!device.getDeviceType().equalsIgnoreCase("BLIND")) {
                return "Device is not a blind: " + deviceId;
                }

                BlindServiceGrpc.BlindServiceBlockingStub blindStub = BlindServiceGrpc.newBlockingStub(channel);
                BlindSetDownRequest request = BlindSetDownRequest.newBuilder().build();

                try {
                BlindSetDownResponse resp = blindStub.setDown(request);
                if (resp.getSuccess()) {
                        return "Blind moved down for device " + deviceId + ": " + resp.getMessage();
                } else {
                        return "Failed to move down blind for device " + deviceId + ": " + resp.getMessage();
                }
                } catch (StatusRuntimeException e) {
                return "gRPC error on setDown for device " + deviceId + ": " + e.getStatus().getDescription();
                }
        }

    public String turnOffDevice(String deviceId) {
        Device device = devices.get(deviceId);
        if (device == null) {
            log(deviceId, "TurnOff", "FAILURE", "{}", "Device not found");
            return "Device not found: " + deviceId;
        }

        ManagedChannel channel = channels.get(deviceId);
        if (channel == null || channel.isShutdown()) {
            log(deviceId, "TurnOff", "FAILURE", "{}", "Channel unavailable");
            return "gRPC channel non disponibile per device: " + deviceId;
        }

        String deviceType = device.getDeviceType();
        log(deviceId, "TurnOff", "PENDING", "{}", null);

        try {
            switch (deviceType.toUpperCase()) {

                case "LIGHT": {
                    LightServiceGrpc.LightServiceBlockingStub lightStub = LightServiceGrpc.newBlockingStub(channel);
                    TurnOffResponse lightResp = lightStub.turnOff(TurnOffRequest.newBuilder().build());
                    String msg = lightResp.getSuccess()
                            ? "Light turned off: " + lightResp.getMessage()
                            : "Failed to turn off light: " + lightResp.getMessage();
                    log(deviceId, "TurnOff", lightResp.getSuccess() ? "SUCCESS" : "FAILURE", "{}",
                            lightResp.getSuccess() ? null : lightResp.getMessage());
                    return msg;
                }

                case "AIRCONDITIONER": {
                    AirConditionerServiceGrpc.AirConditionerServiceBlockingStub airconditionerStub = AirConditionerServiceGrpc
                            .newBlockingStub(channel);
                    AirConditionerTurnOffResponse airconditionerResp = airconditionerStub
                            .turnOff(AirConditionerTurnOffRequest.newBuilder().build());
                    String msg = airconditionerResp.getSuccess()
                            ? "airconditioner deactivated: " + airconditionerResp.getMessage()
                            : "Failed to deactivate airconditioner: " + airconditionerResp.getMessage();
                    log(deviceId, "TurnOff", airconditionerResp.getSuccess() ? "SUCCESS" : "FAILURE", "{}",
                            airconditionerResp.getSuccess() ? null : airconditionerResp.getMessage());
                    return msg;
                }

                case "DISHWASHER": {
                    DishwasherServiceGrpc.DishwasherServiceBlockingStub dishwasherStub = DishwasherServiceGrpc
                            .newBlockingStub(channel);
                    DishwasherTurnOffResponse dishResp = dishwasherStub
                            .turnOff(DishwasherTurnOffRequest.newBuilder().build());
                    String msg = dishResp.getSuccess()
                            ? "Dishwasher turned off: " + dishResp.getMessage()
                            : "Failed to turn off dishwasher: " + dishResp.getMessage();
                    log(deviceId, "TurnOff", dishResp.getSuccess() ? "SUCCESS" : "FAILURE", "{}",
                            dishResp.getSuccess() ? null : dishResp.getMessage());
                    return msg;
                }

                case "MOTIONSENSOR": {
                    MotionSensorServiceGrpc.MotionSensorServiceBlockingStub motionStub = MotionSensorServiceGrpc
                            .newBlockingStub(channel);
                    MotionSensorTurnOffResponse motionResp = motionStub
                            .turnOff(MotionSensorTurnOffRequest.newBuilder().build());
                    String msg = motionResp.getSuccess()
                            ? "Motion sensor disabled: " + motionResp.getMessage()
                            : "Failed to disable motion sensor: " + motionResp.getMessage();
                    log(deviceId, "TurnOff", motionResp.getSuccess() ? "SUCCESS" : "FAILURE", "{}",
                            motionResp.getSuccess() ? null : motionResp.getMessage());
                    return msg;
                }

                case "OVEN": {
                    OvenServiceGrpc.OvenServiceBlockingStub ovenStub = OvenServiceGrpc.newBlockingStub(channel);
                    OvenTurnOffResponse ovenResp = ovenStub.turnOff(OvenTurnOffRequest.newBuilder().build());
                    String msg = ovenResp.getSuccess()
                            ? "Oven turned off: " + ovenResp.getMessage()
                            : "Failed to turn off oven: " + ovenResp.getMessage();
                    log(deviceId, "TurnOff", ovenResp.getSuccess() ? "SUCCESS" : "FAILURE", "{}",
                            ovenResp.getSuccess() ? null : ovenResp.getMessage());
                    return msg;
                }

                case "WASHINGMACHINE": {
                    WashingMachineServiceGrpc.WashingMachineServiceBlockingStub washStub = WashingMachineServiceGrpc
                            .newBlockingStub(channel);
                    WashingMachineTurnOffResponse washResp = washStub
                            .turnOff(WashingMachineTurnOffRequest.newBuilder().build());
                    String msg = washResp.getSuccess()
                            ? "Washing machine turned off: " + washResp.getMessage()
                            : "Failed to turn off washing machine: " + washResp.getMessage();
                    log(deviceId, "TurnOff", washResp.getSuccess() ? "SUCCESS" : "FAILURE", "{}",
                            washResp.getSuccess() ? null : washResp.getMessage());
                    return msg;
                }

                default:
                    log(deviceId, "TurnOff", "FAILURE", "{}", "Unsupported type: " + deviceType);
                    return "Device type not supported: " + deviceType;
            }

        } catch (StatusRuntimeException e) {
            log(deviceId, "TurnOff", "FAILURE", "{}", e.getStatus().getDescription());
            return "gRPC error on device " + deviceId + ": " + e.getStatus().getDescription();
        } catch (Exception e) {
            log(deviceId, "TurnOff", "FAILURE", "{}", e.getMessage());
            return "Error on device " + deviceId + ": " + e.getMessage();
        }
    }

    public String getStatusDevice(String deviceId) {
        Device device = devices.get(deviceId);
        if (device == null) {
            log(deviceId, "GetStatus", "FAILURE", "{}", "Device not found");
            return "Device not found: " + deviceId;
        }

        ManagedChannel channel = channels.get(deviceId);
        if (channel == null || channel.isShutdown()) {
            log(deviceId, "GetStatus", "FAILURE", "{}", "Channel unavailable");
            return "gRPC channel non disponibile per device: " + deviceId;
        }

        String deviceType = device.getDeviceType();
        log(deviceId, "GetStatus", "PENDING", "{}", null);

        try {
            switch (deviceType.toUpperCase()) {
                case "AIRCONDITIONER": {
                    AirConditionerServiceGrpc.AirConditionerServiceBlockingStub stub = AirConditionerServiceGrpc
                            .newBlockingStub(channel);
                    AirConditionerGetStatusResponse resp = stub
                            .getStatus(AirConditionerGetStatusRequest.newBuilder().build());
                    String out = "Air conditioner status: is_on=" + resp.getIsOn() + ", current_program="
                            + resp.getCurrentProgram();
                    log(deviceId, "GetStatus", "SUCCESS", "{}", null);
                    return out;
                }

                case "BLIND": {
                    BlindServiceGrpc.BlindServiceBlockingStub stub = BlindServiceGrpc.newBlockingStub(channel);
                    BlindGetStatusResponse resp = stub.getStatus(BlindGetStatusRequest.newBuilder().build());
                    String out = "Blind status: is_up=" + resp.getIsUp();
                    log(deviceId, "GetStatus", "SUCCESS", "{}", null);
                    return out;
                }

                case "DISHWASHER": {
                    DishwasherServiceGrpc.DishwasherServiceBlockingStub stub = DishwasherServiceGrpc
                            .newBlockingStub(channel);
                    DishwasherGetStatusResponse resp = stub.getStatus(DishwasherGetStatusRequest.newBuilder().build());
                    String out = "Dishwasher status: is_on=" + resp.getIsOn()
                            + ", is_running=" + resp.getIsRunning()
                            + ", program=" + resp.getCurrentProgram()
                            + ", remainingTime=" + resp.getRemainingTime();
                    log(deviceId, "GetStatus", "SUCCESS", "{}", null);
                    return out;
                }

                case "LIGHT": {
                    LightServiceGrpc.LightServiceBlockingStub stub = LightServiceGrpc
                            .newBlockingStub(channel);
                    GetStatusResponse resp = stub.getStatus(GetStatusRequest.newBuilder().build());
                    String out = "Light status: is_on=" + resp.getIsOn();
                    log(deviceId, "GetStatus", "SUCCESS", "{}", null);
                    return out;
                }

                case "MOTIONSENSOR": {
                    MotionSensorServiceGrpc.MotionSensorServiceBlockingStub stub = MotionSensorServiceGrpc
                            .newBlockingStub(channel);
                    MotionSensorGetStatusResponse resp = stub
                            .getStatus(MotionSensorGetStatusRequest.newBuilder().build());
                    String out = "MotionSensor status: " +
                            "is_on=" +
                            // resp.getIsOn() +
                            ", motionDetected=" + resp.getMotionDetected();
                    log(deviceId, "GetStatus", "SUCCESS", "{}", null);
                    return out;
                }
                case "OVEN": {
                    OvenServiceGrpc.OvenServiceBlockingStub stub = OvenServiceGrpc.newBlockingStub(channel);
                    OvenGetStatusResponse resp = stub.getStatus(OvenGetStatusRequest.newBuilder().build());
                    String out = "Oven status: isOn=" + resp.getIsOn()
                            + ", temp=" + resp.getTemperature()
                            + ", mode=" + resp.getCurrentProgram().name();
                    log(deviceId, "GetStatus", "SUCCESS", "{}", null);
                    return out;
                }
                case "SOLARPANEL": {
                    SolarPanelServiceGrpc.SolarPanelServiceBlockingStub stub = SolarPanelServiceGrpc
                            .newBlockingStub(channel);
                    SolarPanelGetStatusResponse resp = stub.getStatus(SolarPanelGetStatusRequest.newBuilder().build());
                    String out = "SolarPanel status: powerOutput=" + resp.getCurrentPowerOutput()
                            + ", batteryStatus=" + resp.getBatteryStatus();
                    log(deviceId, "GetStatus", "SUCCESS", "{}", null);
                    return out;
                }

                case "THERMOSTAT": {
                    ThermostatServiceGrpc.ThermostatServiceBlockingStub stub = ThermostatServiceGrpc
                            .newBlockingStub(channel);
                    ThermostatGetStatusResponse resp = stub.getStatus(ThermostatGetStatusRequest.newBuilder().build());
                    String out = "Thermostat status: temp=" + resp.getCurrentTemperature();
                    log(deviceId, "GetStatus", "SUCCESS", "{}", null);
                    return out;
                }

                case "WASHINGMACHINE": {
                    WashingMachineServiceGrpc.WashingMachineServiceBlockingStub stub = WashingMachineServiceGrpc
                            .newBlockingStub(channel);
                    WashingMachineGetStatusResponse resp = stub
                            .getStatus(WashingMachineGetStatusRequest.newBuilder().build());
                    String out = "WashingMachine status: program=" + resp.getCurrentProgram() + ", is_running="
                            + resp.getIsRunning() + ", is_on=" + resp.getIsOn();
                    log(deviceId, "GetStatus", "SUCCESS", "{}", null);
                    return out;
                }

                default:
                    log(deviceId, "GetStatus", "FAILURE", "{}", "Unsupported type: " + deviceType);
                    return "Device type not supported: " + deviceType;
            }
        } catch (StatusRuntimeException e) {
            log(deviceId, "GetStatus", "FAILURE", "{}", e.getStatus().getDescription());
            return "gRPC error on device " + deviceId + ": " + e.getStatus().getDescription();
        } catch (Exception e) {
            log(deviceId, "GetStatus", "FAILURE", "{}", e.getMessage());
            return "Error on device " + deviceId + ": " + e.getMessage();
        }
    }

    public String startDevice(String deviceId) {
        Device device = devices.get(deviceId);
        if (device == null) {
            log(deviceId, "Start", "FAILURE", "{}", "Device not found");
            return "Device not found: " + deviceId;
        }
        ManagedChannel channel = channels.get(deviceId);
        if (channel == null || channel.isShutdown()) {
            log(deviceId, "Start", "FAILURE", "{}", "Channel unavailable");
            return "gRPC channel non disponibile per device: " + deviceId;
        }
        String deviceType = device.getDeviceType();
        log(deviceId, "Start", "PENDING", "{}", null);
        try {
            switch (deviceType.toUpperCase()) {
                case "WASHINGMACHINE": {
                    WashingMachineServiceGrpc.WashingMachineServiceBlockingStub stub = WashingMachineServiceGrpc
                            .newBlockingStub(channel);
                    WashingMachineStartResponse resp = stub.start(WashingMachineStartRequest.newBuilder().build());
                    String msg = resp.getSuccess() ? "Washing machine started: " + resp.getMessage()
                            : "Failed to start washing machine: " + resp.getMessage();
                    log(deviceId, "Start", resp.getSuccess() ? "SUCCESS" : "FAILURE", "{}",
                            resp.getSuccess() ? null : resp.getMessage());
                    return msg;
                }
                case "DISHWASHER": {
                    DishwasherServiceGrpc.DishwasherServiceBlockingStub stub = DishwasherServiceGrpc
                            .newBlockingStub(channel);
                    DishwasherStartResponse resp = stub.start(DishwasherStartRequest.newBuilder().build());
                    String msg = resp.getSuccess() ? "Dishwasher started: " + resp.getMessage()
                            : "Failed to start dishwasher: " + resp.getMessage();
                    log(deviceId, "Start", resp.getSuccess() ? "SUCCESS" : "FAILURE", "{}",
                            resp.getSuccess() ? null : resp.getMessage());
                    return msg;
                }
                default:
                    log(deviceId, "Start", "FAILURE", "{}", "Unsupported type: " + deviceType);
                    return "Start not supported for device type: " + deviceType;
            }
        } catch (StatusRuntimeException e) {
            log(deviceId, "Start", "FAILURE", "{}", e.getStatus().getDescription());
            return "gRPC error on start for device " + deviceId + ": " + e.getStatus().getDescription();
        } catch (Exception e) {
            log(deviceId, "Start", "FAILURE", "{}", e.getMessage());
            return "Error on start for device " + deviceId + ": " + e.getMessage();
        }
    }

    public String stopDevice(String deviceId) {
        Device device = devices.get(deviceId);
        if (device == null) {
            log(deviceId, "Stop", "FAILURE", "{}", "Device not found");
            return "Device not found: " + deviceId;
        }
        ManagedChannel channel = channels.get(deviceId);
        if (channel == null || channel.isShutdown()) {
            log(deviceId, "Stop", "FAILURE", "{}", "Channel unavailable");
            return "gRPC channel non disponibile per device: " + deviceId;
        }
        String deviceType = device.getDeviceType();
        log(deviceId, "Stop", "PENDING", "{}", null);
        try {
            switch (deviceType.toUpperCase()) {
                case "WASHINGMACHINE": {
                    WashingMachineServiceGrpc.WashingMachineServiceBlockingStub stub = WashingMachineServiceGrpc
                            .newBlockingStub(channel);
                    WashingMachineStopResponse resp = stub.stop(WashingMachineStopRequest.newBuilder().build());
                    String msg = resp.getSuccess() ? "Washing machine stopped: " + resp.getMessage()
                            : "Failed to stop washing machine: " + resp.getMessage();
                    log(deviceId, "Stop", resp.getSuccess() ? "SUCCESS" : "FAILURE", "{}",
                            resp.getSuccess() ? null : resp.getMessage());
                    return msg;
                }
                case "DISHWASHER": {
                    DishwasherServiceGrpc.DishwasherServiceBlockingStub stub = DishwasherServiceGrpc
                            .newBlockingStub(channel);
                    DishwasherStopResponse resp = stub.stop(DishwasherStopRequest.newBuilder().build());
                    String msg = resp.getSuccess() ? "Dishwasher stopped: " + resp.getMessage()
                            : "Failed to stop dishwasher: " + resp.getMessage();
                    log(deviceId, "Stop", resp.getSuccess() ? "SUCCESS" : "FAILURE", "{}",
                            resp.getSuccess() ? null : resp.getMessage());
                    return msg;
                }
                default:
                    log(deviceId, "Stop", "FAILURE", "{}", "Unsupported type: " + deviceType);
                    return "Stop not supported for device type: " + deviceType;
            }
        } catch (StatusRuntimeException e) {
            log(deviceId, "Stop", "FAILURE", "{}", e.getStatus().getDescription());
            return "gRPC error on stop for device " + deviceId + ": " + e.getStatus().getDescription();
        } catch (Exception e) {
            log(deviceId, "Stop", "FAILURE", "{}", e.getMessage());
            return "Error on stop for device " + deviceId + ": " + e.getMessage();
        }
    }

    public String setProgramDevice(String deviceId, int program) {
        Device device = devices.get(deviceId);
        if (device == null) {
            log(deviceId, "SetProgram", "FAILURE", "{}", "Device not found");
            return "Device not found: " + deviceId;
        }
        ManagedChannel channel = channels.get(deviceId);
        if (channel == null || channel.isShutdown()) {
            log(deviceId, "SetProgram", "FAILURE", "{}", "Channel unavailable");
            return "gRPC channel non disponibile per device: " + deviceId;
        }
        String deviceType = device.getDeviceType();
        log(deviceId, "SetProgram", "PENDING", String.format("{\"program\":%d}", program), null);
        try {
            switch (deviceType.toUpperCase()) {
                case "WASHINGMACHINE": {
                    WashingMachineServiceGrpc.WashingMachineServiceBlockingStub stub = WashingMachineServiceGrpc
                            .newBlockingStub(channel);
                    WashingMachineSetProgramRequest req = WashingMachineSetProgramRequest.newBuilder()
                            .setProgramValue(program).build();
                    WashingMachineSetProgramResponse resp = stub.setProgram(req);
                    String msg = resp.getSuccess() ? "Washing machine program set: " + resp.getMessage()
                            : "Failed to set program: " + resp.getMessage();
                    log(deviceId, "SetProgram", resp.getSuccess() ? "SUCCESS" : "FAILURE",
                            String.format("{\"program\":%d}", program),
                            resp.getSuccess() ? null : resp.getMessage());
                    return msg;
                }
                case "DISHWASHER": {
                    DishwasherServiceGrpc.DishwasherServiceBlockingStub stub = DishwasherServiceGrpc
                            .newBlockingStub(channel);
                    SetProgramRequest req = SetProgramRequest.newBuilder().setProgramValue(program).build();
                    SetProgramResponse resp = stub.setProgram(req);
                    String msg = resp.getSuccess() ? "Dishwasher program set: " + resp.getMessage()
                            : "Failed to set program: " + resp.getMessage();
                    log(deviceId, "SetProgram", resp.getSuccess() ? "SUCCESS" : "FAILURE",
                            String.format("{\"program\":%d}", program),
                            resp.getSuccess() ? null : resp.getMessage());
                    return msg;
                }
                case "OVEN": {
                    OvenServiceGrpc.OvenServiceBlockingStub stub = OvenServiceGrpc.newBlockingStub(channel);
                    OvenSetProgramRequest req = OvenSetProgramRequest.newBuilder().setProgramValue(program).build();
                    OvenSetProgramResponse resp = stub.setProgram(req);
                    String msg = resp.getSuccess() ? "Oven program set: " + resp.getMessage()
                            : "Failed to set oven program: " + resp.getMessage();
                    log(deviceId, "SetProgram", resp.getSuccess() ? "SUCCESS" : "FAILURE",
                            String.format("{\"program\":%d}", program),
                            resp.getSuccess() ? null : resp.getMessage());
                    return msg;
                }
                 case "AIRCONDITIONER": {
                    AirConditionerServiceGrpc.AirConditionerServiceBlockingStub stub = AirConditionerServiceGrpc
                            .newBlockingStub(channel);
                    SetAirConditionerProgramRequest req = SetAirConditionerProgramRequest.newBuilder()
                            .setProgramValue(program).build();
                    SetAirConditionerProgramResponse resp = stub.setProgram(req);
                    return resp.getSuccess() ? "Air conditioner program set: " + resp.getMessage()
                            : "Failed to set air conditioner program: " + resp.getMessage();
                }
                default:
                    log(deviceId, "SetProgram", "FAILURE", String.format("{\"program\":%d}", program),
                            "Unsupported type: " + deviceType);
                    return "Set program not supported for device type: " + deviceType;
            }
        } catch (StatusRuntimeException e) {
            log(deviceId, "SetProgram", "FAILURE", String.format("{\"program\":%d}", program),
                    e.getStatus().getDescription());
            return "gRPC error on setProgram for device " + deviceId + ": " + e.getStatus().getDescription();
        } catch (Exception e) {
            log(deviceId, "SetProgram", "FAILURE", String.format("{\"program\":%d}", program),
                    e.getMessage());
            return "gRPC error on setProgram for device " + deviceId + ": " + e.getMessage();
        }
    }

    public String setSensitivityMotionSensor(String deviceId, int sensitivity) {
        Device device = devices.get(deviceId);
        if (device == null) {
            log(deviceId, "SetSensitivity", "FAILURE", "{}", "Device not found");
            return "Device not found: " + deviceId;
        }
        ManagedChannel channel = channels.get(deviceId);
        if (channel == null || channel.isShutdown()) {
            log(deviceId, "SetSensitivity", "FAILURE", "{}", "Channel unavailable");
            return "gRPC channel non disponibile per device: " + deviceId;
        }
        if (!device.getDeviceType().equalsIgnoreCase("MOTIONSENSOR")) {
            log(deviceId, "SetSensitivity", "FAILURE", "{}", "Wrong type (not MOTIONSENSOR)");
            return "Device is not a motion sensor: " + deviceId;
        }
        log(deviceId, "SetSensitivity", "PENDING", String.format("{\"sensitivity\":%d}", sensitivity), null);
        try {
            MotionSensorServiceGrpc.MotionSensorServiceBlockingStub stub = MotionSensorServiceGrpc
                    .newBlockingStub(channel);
            SetSensitivityRequest req = SetSensitivityRequest.newBuilder().setSensitivity(sensitivity).build();
            SetSensitivityResponse resp = stub.setSensitivity(req);
            String msg = resp.getSuccess() ? "Motion sensor sensitivity set: " + resp.getMessage()
                    : "Failed to set sensitivity: " + resp.getMessage();
            log(deviceId, "SetSensitivity", resp.getSuccess() ? "SUCCESS" : "FAILURE",
                    String.format("{\"sensitivity\":%d}", sensitivity),
                    resp.getSuccess() ? null : resp.getMessage());
            return msg;
        } catch (StatusRuntimeException e) {
            log(deviceId, "SetSensitivity", "FAILURE",
                    String.format("{\"sensitivity\":%d}", sensitivity),
                    e.getStatus().getDescription());
            return "gRPC error on setSensitivity for device " + deviceId + ": " + e.getStatus().getDescription();
        } catch (Exception e) {
            log(deviceId, "SetSensitivity", "FAILURE",
                    String.format("{\"sensitivity\":%d}", sensitivity),
                    e.getMessage());
            return "gRPC error on setSensitivity for device " + deviceId + ": " + e.getMessage();
        }
    }

    public String setTemperatureOven(String deviceId, int temperature) {
        Device device = devices.get(deviceId);
        if (device == null) {
            log(deviceId, "SetTemperature", "FAILURE", "{}", "Device not found");
            return "Device not found: " + deviceId;
        }
        ManagedChannel channel = channels.get(deviceId);
        if (channel == null || channel.isShutdown()) {
            log(deviceId, "SetTemperature", "FAILURE", "{}", "Channel unavailable");
            return "gRPC channel non disponibile per device: " + deviceId;
        }
        if (!device.getDeviceType().equalsIgnoreCase("OVEN")) {
            log(deviceId, "SetTemperature", "FAILURE", "{}", "Wrong type (not OVEN)");
            return "Device is not an oven: " + deviceId;
        }
        log(deviceId, "SetTemperature", "PENDING", String.format("{\"temperature\":%d}", temperature), null);
        try {
            OvenServiceGrpc.OvenServiceBlockingStub stub = OvenServiceGrpc.newBlockingStub(channel);
            SetTemperatureRequest req = SetTemperatureRequest.newBuilder().setTemperature(temperature).build();
            SetTemperatureResponse resp = stub.setTemperature(req);
            String msg = resp.getSuccess() ? "Oven temperature set: " + resp.getMessage()
                    : "Failed to set oven temperature: " + resp.getMessage();
            log(deviceId, "SetTemperature", resp.getSuccess() ? "SUCCESS" : "FAILURE",
                    String.format("{\"temperature\":%d}", temperature),
                    resp.getSuccess() ? null : resp.getMessage());
            return msg;
        } catch (StatusRuntimeException e) {
            log(deviceId, "SetTemperature", "FAILURE",
                    String.format("{\"temperature\":%d}", temperature),
                    e.getStatus().getDescription());
            return "gRPC error on setTemperature for device " + deviceId + ": " + e.getStatus().getDescription();
        } catch (Exception e) {
            log(deviceId, "SetTemperature", "FAILURE",
                    String.format("{\"temperature\":%d}", temperature),
                    e.getMessage());
            return "gRPC error on setTemperature for device " + deviceId + ": " + e.getMessage();
        }
    }

        // campi per l’allarme
        private boolean alarmActive = false;
        private boolean alarmTriggered = false;

        public synchronized RegisterDeviceResponse registerDeviceHttp(String deviceId, String deviceType,
                                                              String address, int port) {
        // normalizza tipo (alcune API usano underscore, altre no)
        String normalizedType = deviceType.replace("_", "");

        String payload = String.format("{\"type\":\"%s\",\"address\":\"%s\",\"port\":%d}", deviceType, address, port);
        log(deviceId, "Register", "PENDING", payload, null);

        // chiudi canale precedente se stai ri-registrando lo stesso id
        ManagedChannel old = channels.remove(deviceId);
        if (old != null && !old.isShutdown()) {
                try {
                old.shutdownNow();
                } catch (Exception ignore) {}
        }

        try {
                // crea device
                Device device = Device.newBuilder()
                        .setDeviceId(deviceId)
                        .setDeviceType(normalizedType)
                        .setAddress(address)
                        .setPort(port)
                        .setOnline(true)
                        .build();
                devices.put(deviceId, device);

                // crea canale gRPC
                ManagedChannel ch = ManagedChannelBuilder.forAddress(address, port)
                        .usePlaintext()
                        .build();
                channels.put(deviceId, ch);

                log(deviceId, "Register", "SUCCESS", payload, null);

                // ritorna RegisterDeviceResponse come nella prima versione
                return RegisterDeviceResponse.newBuilder()
                        .setSuccess(true)
                        .setMessage("Device registered: " + deviceId)
                        .build();

        } catch (Exception e) {
                log(deviceId, "Register", "FAILURE", payload, e.getMessage());
                throw e;
        }
        }

        public synchronized String activateAlarm(boolean enable) {
        this.alarmActive = enable;
        this.alarmTriggered = false; 

        return "Alarm is now " + (alarmActive ? "active" : "inactive");
        }

        // Scheduler che controlla i sensori ogni 5 secondi
        @Scheduled(fixedRate = 5000)
        public synchronized void scheduledSensorCheck() {
                if (alarmActive) {
                System.out.println("⏱ Scheduled check: checking all sensors... (alarmActive=" + alarmActive + ")");
                checkAllSensors();
                System.out.println("⏱ Scheduled check completed. alarmTriggered=" + alarmTriggered);
                }
        }

        public synchronized void checkAllSensors() {
                if (devices == null || devices.isEmpty()) {
                return;
                }
                for (Device device : devices.values()) {
                        if ("MOTIONSENSOR".equalsIgnoreCase(device.getDeviceType())) {
                        ManagedChannel channel = channels.get(device.getDeviceId());
                        if (channel == null || channel.isShutdown())
                                continue;

                        try {
                                MotionSensorServiceGrpc.MotionSensorServiceBlockingStub stub =
                                        MotionSensorServiceGrpc.newBlockingStub(channel);

                                MotionSensorGetStatusResponse resp = stub.getStatus(MotionSensorGetStatusRequest.newBuilder().build());

                                if (resp.getMotionDetected() && alarmActive) {
                                alarmTriggered = true;
                                }

                        } catch (StatusRuntimeException e) {
                                System.err.println("❌ gRPC error on " + device.getDeviceId() + ": " + e.getStatus());                        }
                        }
                }
        }
        //Stato allarme
        public synchronized String getAlarmStatus() {
        return alarmTriggered ? "Alarm triggered!" : "Alarm not triggered";
        }

        private Set<String> ensureSet(Map<String, Set<String>> map, String key) {
                return map.computeIfAbsent(key, k -> ConcurrentHashMap.newKeySet());
        }

        private Map<String, String> executeForDevices(Collection<String> ids, Function<String, String> actionPerDevice) {
        Map<String, String> out = new LinkedHashMap<>();
                if (ids == null || ids.isEmpty()) return out;
                for (String id : ids) {
                        try {
                        out.put(id, actionPerDevice.apply(id));
                        } catch (Exception e) {
                        out.put(id, "ERROR: " + e.getMessage());
                        }
                }
        return out;
        }

        // ===== Rooms =====
        public String createRoom(String roomId) {
        try {
                rgRepo.createRoom(roomId); // <-- prima era roomGroupRepo
                rooms.putIfAbsent(roomId, ConcurrentHashMap.newKeySet());
                return "Room created: " + roomId;
        } catch (Exception e) {
                return "ERROR creating room: " + e.getMessage();
        }
        }

        public String addDeviceToRoom(String roomId, String deviceId) {
                if (!devices.containsKey(deviceId)) return "Device not found: " + deviceId;
                try {
                        rgRepo.addDeviceToRoom(roomId, deviceId); // <-- prima era roomGroupRepo
                        rooms.computeIfAbsent(roomId, k -> ConcurrentHashMap.newKeySet()).add(deviceId);
                        return "Added " + deviceId + " to room " + roomId;
                } catch (Exception e) {
                        return "ERROR adding device to room: " + e.getMessage();
                }
        }

        public String removeDeviceFromRoom(String roomId, String deviceId) {
        try {
                rgRepo.removeDeviceFromRoom(roomId, deviceId); // <-- prima era roomGroupRepo
                Set<String> set = rooms.get(roomId);
                if (set != null) set.remove(deviceId);
                return "Removed " + deviceId + " from room " + roomId;
        } catch (Exception e) {
                return "ERROR removing device from room: " + e.getMessage();
        }
        }

        public Set<String> listRoomDevices(String roomId) {
        return rooms.getOrDefault(roomId, Set.of());
        }

        // ===== Groups =====
        public String createGroup(String groupId) {
        try {
                rgRepo.createGroup(groupId); // <-- prima era roomGroupRepo
                groups.putIfAbsent(groupId, ConcurrentHashMap.newKeySet());
                return "Group created: " + groupId;
        } catch (Exception e) {
                return "ERROR creating group: " + e.getMessage();
        }
        }

        public String addDeviceToGroup(String groupId, String deviceId) {
        if (!devices.containsKey(deviceId)) return "Device not found: " + deviceId;
        try {
                rgRepo.addDeviceToGroup(groupId, deviceId); // <-- prima era roomGroupRepo
                groups.computeIfAbsent(groupId, k -> ConcurrentHashMap.newKeySet()).add(deviceId);
                return "Added " + deviceId + " to group " + groupId;
        } catch (Exception e) {
                return "ERROR adding device to group: " + e.getMessage();
        }
        }

        public String removeDeviceFromGroup(String groupId, String deviceId) {
        try {
                rgRepo.removeDeviceFromGroup(groupId, deviceId); // <-- prima era roomGroupRepo
                Set<String> set = groups.get(groupId);
                if (set != null) set.remove(deviceId);
                return "Removed " + deviceId + " from group " + groupId;
        } catch (Exception e) {
                return "ERROR removing device from group: " + e.getMessage();
        }
        }

        public Set<String> listGroupDevices(String groupId) {
        return groups.getOrDefault(groupId, Set.of());
        }

        // broadcast comandi
        public Map<String, String> turnOnRoom(String roomId) {
        Set<String> ids = listRoomDevices(roomId);
        return executeForDevices(ids, this::turnOnDevice);
        }

        public Map<String, String> turnOffRoom(String roomId) {
        Set<String> ids = listRoomDevices(roomId);
        return executeForDevices(ids, this::turnOffDevice);
        }

        public Map<String, String> startRoom(String roomId) {
        Set<String> ids = listRoomDevices(roomId);
        return executeForDevices(ids, this::startDevice);
        }

        public Map<String, String> stopRoom(String roomId) {
        Set<String> ids = listRoomDevices(roomId);
        return executeForDevices(ids, this::stopDevice);
        }

        public Map<String, String> setProgramRoom(String roomId, int program) {
        Set<String> ids = listRoomDevices(roomId);
        return executeForDevices(ids, id -> setProgramDevice(id, program));
        }

        public Map<String, String> setTemperatureRoom(String roomId, int temperature) {
        Set<String> ids = listRoomDevices(roomId);
        return executeForDevices(ids, id -> setTemperatureOven(id, temperature));
        }

        public Map<String, String> turnOnGroup(String groupId) {
        Set<String> ids = listGroupDevices(groupId);
        return executeForDevices(ids, this::turnOnDevice);
        }

        public Map<String, String> turnOffGroup(String groupId) {
        Set<String> ids = listGroupDevices(groupId);
        return executeForDevices(ids, this::turnOffDevice);
        }

        public Map<String, String> startGroup(String groupId) {
        Set<String> ids = listGroupDevices(groupId);
        return executeForDevices(ids, this::startDevice);
        }

        public Map<String, String> stopGroup(String groupId) {
        Set<String> ids = listGroupDevices(groupId);
        return executeForDevices(ids, this::stopDevice);
        }

        public Map<String, String> setProgramGroup(String groupId, int program) {
        Set<String> ids = listGroupDevices(groupId);
        return executeForDevices(ids, id -> setProgramDevice(id, program));
        }

        public Map<String, String> setTemperatureGroup(String groupId, int temperature) {
        Set<String> ids = listGroupDevices(groupId);
        return executeForDevices(ids, id -> setTemperatureOven(id, temperature));
        }

        @PostConstruct
        public void initRoomsAndGroups() {
        try {
                rooms.clear();
                groups.clear();

                // Rooms
                for (String roomId : rgRepo.listRooms()) {
                Set<String> set = ConcurrentHashMap.newKeySet();
                set.addAll(rgRepo.listDevicesInRoom(roomId));
                rooms.put(roomId, set);
                }

                // Groups
                for (String groupId : rgRepo.listGroups()) {
                Set<String> set = ConcurrentHashMap.newKeySet();
                set.addAll(rgRepo.listDevicesInGroup(groupId));
                groups.put(groupId, set);
                }

                System.out.println("Rooms/Groups loaded from DB: rooms=" + rooms.keySet() + " groups=" + groups.keySet());
        
                displayNames.clear();
                displayNames.putAll(aliasRepo.loadAll());
                System.out.println("Device aliases loaded: " + displayNames);
        } catch (Exception e) {
                System.err.println("Failed loading rooms/groups/aliases: " + e.getMessage());
        }
        }

        // Passthrough per le API di listing
        public List<String> listRooms() throws Exception {
        return rgRepo.listRooms();
        }
        public List<String> listGroups() throws Exception {
        return rgRepo.listGroups();
        }
        public Map<String, Set<String>> listAllRooms() {
        return Collections.unmodifiableMap(rooms);
        }

        public Map<String, Set<String>> listAllGroups() {
        return Collections.unmodifiableMap(groups);
        }
        // in DeviceManagerServiceImpl
        public List<DeviceEventRepository.DeviceEvent> getRecentLogs(int limit) throws Exception {
        return repo.listRecent(limit);
        }

        public List<DeviceEventRepository.DeviceEvent> getLogsByDevice(String deviceId, int limit) throws Exception {
        return repo.listByDevice(deviceId, limit);
        }

        /** Rinomina stanza: DB + cache in-memory + log */
        public synchronized String renameRoom(String oldId, String newId) {
        if (oldId.equals(newId)) return "No-op: same room id.";

        try {
                // DB
                rgRepo.renameRoom(oldId, newId);

                // Cache in-memory
                Set<String> devs = rooms.remove(oldId);
                if (devs == null) devs = ConcurrentHashMap.newKeySet();
                rooms.put(newId, devs);

                log(newId, "RenameRoom", "SUCCESS",
                String.format("{\"from\":\"%s\",\"to\":\"%s\"}", oldId, newId), null);
                return "Room renamed: " + oldId + " → " + newId;
        } catch (Exception e) {
                log(oldId, "RenameRoom", "FAILURE", "{}", e.getMessage());
                return "ERROR renaming room: " + e.getMessage();
        }
        }

        /** Elimina stanza: DB + cache + log */
        public synchronized String deleteRoom(String roomId) {
        try {
                rgRepo.deleteRoom(roomId);
                rooms.remove(roomId);
                log(roomId, "DeleteRoom", "SUCCESS", "{}", null);
                return "Room deleted: " + roomId;
        } catch (Exception e) {
                log(roomId, "DeleteRoom", "FAILURE", "{}", e.getMessage());
                return "ERROR deleting room: " + e.getMessage();
        }
        }

        /** Rinomina gruppo: DB + cache + log */
        public synchronized String renameGroup(String oldId, String newId) {
        if (oldId.equals(newId)) return "No-op: same group id.";
        try {
                rgRepo.renameGroup(oldId, newId);
                Set<String> devs = groups.remove(oldId);
                if (devs == null) devs = ConcurrentHashMap.newKeySet();
                groups.put(newId, devs);

                log(newId, "RenameGroup", "SUCCESS",
                String.format("{\"from\":\"%s\",\"to\":\"%s\"}", oldId, newId), null);
                return "Group renamed: " + oldId + " → " + newId;
        } catch (Exception e) {
                log(oldId, "RenameGroup", "FAILURE", "{}", e.getMessage());
                return "ERROR renaming group: " + e.getMessage();
        }
        }

        /** Elimina gruppo: DB + cache + log */
        public synchronized String deleteGroup(String groupId) {
        try {
                rgRepo.deleteGroup(groupId);
                groups.remove(groupId);
                log(groupId, "DeleteGroup", "SUCCESS", "{}", null);
                return "Group deleted: " + groupId;
        } catch (Exception e) {
                log(groupId, "DeleteGroup", "FAILURE", "{}", e.getMessage());
                return "ERROR deleting group: " + e.getMessage();
        }
        }

        /**
         * Rinomina un device (cambia il suo "deviceId").
         * Nota: il device remoto rimane connesso sul canale già aperto; qui re-indicizziamo le mappe,
         * aggiorniamo i riferimenti in rooms/groups e in DB per i link. 
         */
        public synchronized String renameDevice(String oldId, String newId) {
                if (oldId.equals(newId)) return "No-op: same device id.";
                        Device dev = devices.get(oldId);
                if (dev == null) return "Device not found: " + oldId;
                if (devices.containsKey(newId)) return "Device id already exists: " + newId;

                try {
                        // DB: aggiorna le tabelle di linking (room_devices / group_devices)
                        rgRepo.renameDeviceEverywhere(oldId, newId);

                        // Canale gRPC: re-key
                        ManagedChannel ch = channels.remove(oldId);
                        if (ch != null) channels.put(newId, ch);

                        // Device proto aggiornato e re-key nella mappa
                        Device updated = Device.newBuilder(dev)
                                .setDeviceId(newId)
                                .build();
                        devices.remove(oldId);
                        devices.put(newId, updated);

                        // Rooms cache
                        rooms.forEach((room, set) -> {
                        if (set.remove(oldId)) set.add(newId);
                        });

                        // Groups cache
                        groups.forEach((grp, set) -> {
                        if (set.remove(oldId)) set.add(newId);
                        });

                        log(newId, "RenameDevice", "SUCCESS",
                        String.format("{\"from\":\"%s\",\"to\":\"%s\"}", oldId, newId), null);
                        return "Device renamed: " + oldId + " → " + newId;
                } catch (Exception e) {
                        log(oldId, "RenameDevice", "FAILURE", "{}", e.getMessage());
                        return "ERROR renaming device: " + e.getMessage();
                }
        }

        /** Imposta/aggiorna il display name (alias) di un device – persistente in DB e in cache. */
        public synchronized String setDeviceDisplayName(String deviceId, String displayName) {
                if (!devices.containsKey(deviceId)) {
                        return "Device not found: " + deviceId;
                }
                try {
                        aliasRepo.upsertAlias(deviceId, displayName);
                        displayNames.put(deviceId, displayName);
                        log(deviceId, "SetDisplayName", "SUCCESS",
                                String.format("{\"display_name\":\"%s\"}", displayName), null);
                        return "Display name set for " + deviceId + ": " + displayName;
                        } catch (Exception e) {
                                log(deviceId, "SetDisplayName", "FAILURE",
                                        String.format("{\"display_name\":\"%s\"}", displayName), e.getMessage());
                                return "ERROR setting display name: " + e.getMessage();
                }
        }

        /** Rimuove l’alias del device (torna al deviceId come nome “visuale”). */
        public synchronized String clearDeviceDisplayName(String deviceId) {
                if (!devices.containsKey(deviceId)) {
                        return "Device not found: " + deviceId;
                }
                try {
                        aliasRepo.deleteAlias(deviceId);
                        displayNames.remove(deviceId);
                        log(deviceId, "ClearDisplayName", "SUCCESS", "{}", null);
                        return "Display name cleared for " + deviceId;
                } catch (Exception e) {
                        log(deviceId, "ClearDisplayName", "FAILURE", "{}", e.getMessage());
                        return "ERROR clearing display name: " + e.getMessage();
                }
        }

        /** Recupera il display name se presente, altrimenti il deviceId. */
        public String getDisplayNameOrId(String deviceId) {
                String dn = displayNames.get(deviceId);
                return (dn == null || dn.isBlank()) ? deviceId : dn;
        }

        /** Opzionale: ritorna la mappa completa deviceId -> displayName (solo quelli che hanno alias). */
        public Map<String,String> listDisplayNames() {
                return new HashMap<>(displayNames);
        }

}