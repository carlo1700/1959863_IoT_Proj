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
import org.springframework.stereotype.Service;

@Service
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

                case "AIRCONDITIONER":
                    AirConditionerServiceGrpc.AirConditionerServiceBlockingStub airconditionerStub = AirConditionerServiceGrpc
                            .newBlockingStub(channel);
                    AirConditionerTurnOnResponse airconditionerResp = airconditionerStub
                            .turnOn(AirConditionerTurnOnRequest.newBuilder().build());
                    return airconditionerResp.getSuccess()
                            ? "airconditioner activated: " + airconditionerResp.getMessage()
                            : "Failed to activate airconditioner: " + airconditionerResp.getMessage();

                case "DISHWASHER":
                    DishwasherServiceGrpc.DishwasherServiceBlockingStub dishwasherStub = DishwasherServiceGrpc
                            .newBlockingStub(channel);
                    DishwasherTurnOnResponse dishResp = dishwasherStub
                            .turnOn(DishwasherTurnOnRequest.newBuilder().build());
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

                case "WASHINGMACHINE":
                    WashingMachineServiceGrpc.WashingMachineServiceBlockingStub washStub = WashingMachineServiceGrpc
                            .newBlockingStub(channel);
                    WashingMachineTurnOnResponse washResp = washStub
                            .turnOn(WashingMachineTurnOnRequest.newBuilder().build());
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

    public String SetUpBlind(String deviceId) {
        Device device = devices.get(deviceId);
        if (device == null) {
            return "Device not found: " + deviceId;
        }

        ManagedChannel channel = channels.get(deviceId);
        if (channel == null || channel.isShutdown()) {
            return "gRPC channel non disponibile per device: " + deviceId;
        }

        if (!device.getDeviceType().equalsIgnoreCase("BLIND")) {
            return "Device is not a blind: " + deviceId;
        }

        BlindServiceGrpc.BlindServiceBlockingStub blindStub = BlindServiceGrpc.newBlockingStub(channel);

        // 3. Costruisci la richiesta TurnUp
        BlindSetUpRequest request = BlindSetUpRequest.newBuilder().build();

        // 4. Invia il comando e gestisci eventuali errori
        try {
            BlindSetUpResponse resp = blindStub.setUp(request);
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

    public String SetDownBlind(String deviceId) {
        Device device = devices.get(deviceId);
        if (device == null) {
            return "Device not found: " + deviceId;
        }

        ManagedChannel channel = channels.get(deviceId);
        if (channel == null || channel.isShutdown()) {
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
                    TurnOffResponse lightResp = lightStub.turnOff(TurnOffRequest.newBuilder().build());
                    return lightResp.getSuccess()
                            ? "Light turned off: " + lightResp.getMessage()
                            : "Failed to turn off light: " + lightResp.getMessage();

                case "AIRCONDITIONER":
                    AirConditionerServiceGrpc.AirConditionerServiceBlockingStub airconditionerStub = AirConditionerServiceGrpc
                            .newBlockingStub(channel);
                    AirConditionerTurnOffResponse airconditionerResp = airconditionerStub
                            .turnOff(AirConditionerTurnOffRequest.newBuilder().build());
                    return airconditionerResp.getSuccess()
                            ? "airconditioner deactivated: " + airconditionerResp.getMessage()
                            : "Failed to deactivate airconditioner: " + airconditionerResp.getMessage();

                case "DISHWASHER":
                    DishwasherServiceGrpc.DishwasherServiceBlockingStub dishwasherStub = DishwasherServiceGrpc
                            .newBlockingStub(channel);
                    DishwasherTurnOffResponse dishResp = dishwasherStub
                            .turnOff(DishwasherTurnOffRequest.newBuilder().build());
                    return dishResp.getSuccess()
                            ? "Dishwasher turned off: " + dishResp.getMessage()
                            : "Failed to turn off dishwasher: " + dishResp.getMessage();

                case "MOTIONSENSOR":
                    MotionSensorServiceGrpc.MotionSensorServiceBlockingStub motionStub = MotionSensorServiceGrpc
                            .newBlockingStub(channel);
                    MotionSensorTurnOffResponse motionResp = motionStub
                            .turnOff(MotionSensorTurnOffRequest.newBuilder().build());
                    return motionResp.getSuccess()
                            ? "Motion sensor disabled: " + motionResp.getMessage()
                            : "Failed to disable motion sensor: " + motionResp.getMessage();

                case "OVEN":
                    OvenServiceGrpc.OvenServiceBlockingStub ovenStub = OvenServiceGrpc.newBlockingStub(channel);
                    OvenTurnOffResponse ovenResp = ovenStub.turnOff(OvenTurnOffRequest.newBuilder().build());
                    return ovenResp.getSuccess()
                            ? "Oven turned off: " + ovenResp.getMessage()
                            : "Failed to turn off oven: " + ovenResp.getMessage();

                case "WASHINGMACHINE":
                    WashingMachineServiceGrpc.WashingMachineServiceBlockingStub washStub = WashingMachineServiceGrpc
                            .newBlockingStub(channel);
                    WashingMachineTurnOffResponse washResp = washStub
                            .turnOff(WashingMachineTurnOffRequest.newBuilder().build());
                    return washResp.getSuccess()
                            ? "Washing machine turned off: " + washResp.getMessage()
                            : "Failed to turn off washing machine: " + washResp.getMessage();

                default:
                    return "Device type not supported: " + deviceType;
            }

        } catch (StatusRuntimeException e) {
            return "gRPC error on device " + deviceId + ": " + e.getStatus().getDescription();
        }
    }

    public String getStatusDevice(String deviceId) {
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
                case "AIRCONDITIONER": {
                    AirConditionerServiceGrpc.AirConditionerServiceBlockingStub stub = AirConditionerServiceGrpc
                            .newBlockingStub(channel);
                    AirConditionerGetStatusResponse resp = stub
                            .getStatus(AirConditionerGetStatusRequest.newBuilder().build());
                    return "Air conditioner status: is_on=" + resp.getIsOn() + ", current_program="
                            + resp.getCurrentProgram();
                }

                case "DISHWASHER": {
                    DishwasherServiceGrpc.DishwasherServiceBlockingStub stub = DishwasherServiceGrpc
                            .newBlockingStub(channel);
                    DishwasherGetStatusResponse resp = stub.getStatus(DishwasherGetStatusRequest.newBuilder().build());
                    return "Dishwasher status: program=" + resp.getCurrentProgram() + ", remainingTime="
                            + resp.getRemainingTime();
                }
                case "MOTIONSENSOR": {
                    MotionSensorServiceGrpc.MotionSensorServiceBlockingStub stub = MotionSensorServiceGrpc
                            .newBlockingStub(channel);
                    MotionSensorGetStatusResponse resp = stub
                            .getStatus(MotionSensorGetStatusRequest.newBuilder().build());
                    return "MotionSensor status: motionDetected=" + resp.getMotionDetected() + ", lastMotionTime="
                            + resp.getLastMotionTime();
                }
                case "OVEN": {
                    OvenServiceGrpc.OvenServiceBlockingStub stub = OvenServiceGrpc.newBlockingStub(channel);
                    OvenGetStatusResponse resp = stub.getStatus(OvenGetStatusRequest.newBuilder().build());

                    return "Oven status: isOn=" + resp.getIsOn()
                            + ", temp=" + resp.getTemperature()
                            + ", mode=" + resp.getCurrentProgram().name();
                }
                case "SOLARPANEL": {
                    SolarPanelServiceGrpc.SolarPanelServiceBlockingStub stub = SolarPanelServiceGrpc
                            .newBlockingStub(channel);
                    SolarPanelGetStatusResponse resp = stub.getStatus(SolarPanelGetStatusRequest.newBuilder().build());
                    return "SolarPanel status: powerOutput=" + resp.getCurrentPowerOutput()
                            + ", batteryStatus=" + resp.getBatteryStatus();
                }

                case "THERMOSTAT": {
                    ThermostatServiceGrpc.ThermostatServiceBlockingStub stub = ThermostatServiceGrpc
                            .newBlockingStub(channel);
                    ThermostatGetStatusResponse resp = stub.getStatus(ThermostatGetStatusRequest.newBuilder().build());
                    return "Thermostat status: temp=" + resp.getCurrentTemperature();
                }

                case "WASHINGMACHINE": {
                    WashingMachineServiceGrpc.WashingMachineServiceBlockingStub stub = WashingMachineServiceGrpc
                            .newBlockingStub(channel);
                    WashingMachineGetStatusResponse resp = stub
                            .getStatus(WashingMachineGetStatusRequest.newBuilder().build());

                    return "WashingMachine status: program=" + resp.getCurrentProgram() + ", is_running="
                            + resp.getIsRunning() + ", is_on=" + resp.getIsOn();
                }

                default:
                    return "Device type not supported: " + deviceType;
            }
        } catch (StatusRuntimeException e) {
            return "gRPC error on device " + deviceId + ": " + e.getStatus().getDescription();
        }
    }

    public String startDevice(String deviceId) {
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
                case "WASHINGMACHINE": {
                    WashingMachineServiceGrpc.WashingMachineServiceBlockingStub stub = WashingMachineServiceGrpc
                            .newBlockingStub(channel);
                    WashingMachineStartResponse resp = stub.start(WashingMachineStartRequest.newBuilder().build());
                    return resp.getSuccess() ? "Washing machine started: " + resp.getMessage()
                            : "Failed to start washing machine: " + resp.getMessage();
                }
                case "DISHWASHER": {
                    DishwasherServiceGrpc.DishwasherServiceBlockingStub stub = DishwasherServiceGrpc
                            .newBlockingStub(channel);
                    DishwasherStartResponse resp = stub.start(DishwasherStartRequest.newBuilder().build());
                    return resp.getSuccess() ? "Dishwasher started: " + resp.getMessage()
                            : "Failed to start dishwasher: " + resp.getMessage();
                }
                default:
                    return "Start not supported for device type: " + deviceType;
            }
        } catch (StatusRuntimeException e) {
            return "gRPC error on start for device " + deviceId + ": " + e.getStatus().getDescription();
        }
    }

    public String stopDevice(String deviceId) {
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
                case "WASHINGMACHINE": {
                    WashingMachineServiceGrpc.WashingMachineServiceBlockingStub stub = WashingMachineServiceGrpc
                            .newBlockingStub(channel);
                    WashingMachineStopResponse resp = stub.stop(WashingMachineStopRequest.newBuilder().build());
                    return resp.getSuccess() ? "Washing machine stopped: " + resp.getMessage()
                            : "Failed to stop washing machine: " + resp.getMessage();
                }
                case "DISHWASHER": {
                    DishwasherServiceGrpc.DishwasherServiceBlockingStub stub = DishwasherServiceGrpc
                            .newBlockingStub(channel);
                    DishwasherStopResponse resp = stub.stop(DishwasherStopRequest.newBuilder().build());
                    return resp.getSuccess() ? "Dishwasher stopped: " + resp.getMessage()
                            : "Failed to stop dishwasher: " + resp.getMessage();
                }
                default:
                    return "Stop not supported for device type: " + deviceType;
            }
        } catch (StatusRuntimeException e) {
            return "gRPC error on stop for device " + deviceId + ": " + e.getStatus().getDescription();
        }
    }

    public String setProgramDevice(String deviceId, int program) {
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
                case "WASHINGMACHINE": {
                    WashingMachineServiceGrpc.WashingMachineServiceBlockingStub stub = WashingMachineServiceGrpc
                            .newBlockingStub(channel);
                    WashingMachineSetProgramRequest req = WashingMachineSetProgramRequest.newBuilder()
                            .setProgramValue(program).build();
                    WashingMachineSetProgramResponse resp = stub.setProgram(req);
                    return resp.getSuccess() ? "Washing machine program set: " + resp.getMessage()
                            : "Failed to set program: " + resp.getMessage();
                }
                case "DISHWASHER": {
                    DishwasherServiceGrpc.DishwasherServiceBlockingStub stub = DishwasherServiceGrpc
                            .newBlockingStub(channel);
                    SetProgramRequest req = SetProgramRequest.newBuilder().setProgramValue(program).build();
                    SetProgramResponse resp = stub.setProgram(req);
                    return resp.getSuccess() ? "Dishwasher program set: " + resp.getMessage()
                            : "Failed to set program: " + resp.getMessage();
                }
                case "OVEN": {
                    OvenServiceGrpc.OvenServiceBlockingStub stub = OvenServiceGrpc.newBlockingStub(channel);
                    OvenSetProgramRequest req = OvenSetProgramRequest.newBuilder().setProgramValue(program).build();
                    OvenSetProgramResponse resp = stub.setProgram(req);
                    return resp.getSuccess() ? "Oven program set: " + resp.getMessage()
                            : "Failed to set oven program: " + resp.getMessage();
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
                    return "Set program not supported for device type: " + deviceType;
            }
        } catch (StatusRuntimeException e) {
            return "gRPC error on setProgram for device " + deviceId + ": " + e.getStatus().getDescription();
        }
    }

    public String setSensitivityMotionSensor(String deviceId, int sensitivity) {
        Device device = devices.get(deviceId);
        if (device == null) {
            return "Device not found: " + deviceId;
        }
        ManagedChannel channel = channels.get(deviceId);
        if (channel == null || channel.isShutdown()) {
            return "gRPC channel non disponibile per device: " + deviceId;
        }
        if (!device.getDeviceType().equalsIgnoreCase("MOTIONSENSOR")) {
            return "Device is not a motion sensor: " + deviceId;
        }
        try {
            MotionSensorServiceGrpc.MotionSensorServiceBlockingStub stub = MotionSensorServiceGrpc
                    .newBlockingStub(channel);
            SetSensitivityRequest req = SetSensitivityRequest.newBuilder().setSensitivity(sensitivity).build();
            SetSensitivityResponse resp = stub.setSensitivity(req);
            return resp.getSuccess() ? "Motion sensor sensitivity set: " + resp.getMessage()
                    : "Failed to set sensitivity: " + resp.getMessage();
        } catch (StatusRuntimeException e) {
            return "gRPC error on setSensitivity for device " + deviceId + ": " + e.getStatus().getDescription();
        }
    }

    public String setTemperatureOven(String deviceId, int temperature) {
        Device device = devices.get(deviceId);
        if (device == null) {
            return "Device not found: " + deviceId;
        }
        ManagedChannel channel = channels.get(deviceId);
        if (channel == null || channel.isShutdown()) {
            return "gRPC channel non disponibile per device: " + deviceId;
        }
        if (!device.getDeviceType().equalsIgnoreCase("OVEN")) {
            return "Device is not an oven: " + deviceId;
        }
        try {
            OvenServiceGrpc.OvenServiceBlockingStub stub = OvenServiceGrpc.newBlockingStub(channel);
            SetTemperatureRequest req = SetTemperatureRequest.newBuilder().setTemperature(temperature).build();
            SetTemperatureResponse resp = stub.setTemperature(req);
            return resp.getSuccess() ? "Oven temperature set: " + resp.getMessage()
                    : "Failed to set oven temperature: " + resp.getMessage();
        } catch (StatusRuntimeException e) {
            return "gRPC error on setTemperature for device " + deviceId + ": " + e.getStatus().getDescription();
        }
    }

    public RegisterDeviceResponse registerDeviceHttp(String deviceId, String deviceType,
            String address, int port) {
        Device device = Device.newBuilder()
                .setDeviceId(deviceId)
                .setDeviceType(deviceType)
                .setAddress(address)
                .setPort(port)
                .setOnline(true)
                .build();

        devices.put(deviceId, device);

        // Create gRPC channel for the device
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress(address, port)
                .usePlaintext()
                .build();
        channels.put(deviceId, channel);

        return RegisterDeviceResponse.newBuilder()
                .setSuccess(true)
                .setMessage("Device registered: " + deviceId)
                .build();
    }

    private boolean alarmActive = false;
    private boolean alarmTriggered = false;

    public synchronized String activateAlarm(boolean enable) {
        this.alarmActive = enable;
        this.alarmTriggered = false; // reset quando attivo/disattivo

        if (enable) {
            // controlla subito tutti i sensori
            checkAllSensors();
        }

        return "Alarm is now " + (alarmActive ? "active" : "inactive");
    }

    public synchronized void checkAllSensors() {
        for (Device device : devices.values()) {
            if (device.getDeviceType().equalsIgnoreCase("MOTIONSENSOR")) {
                ManagedChannel channel = channels.get(device.getDeviceId());
                if (channel == null || channel.isShutdown())
                    continue;

                try {
                    MotionSensorServiceGrpc.MotionSensorServiceBlockingStub stub = MotionSensorServiceGrpc
                            .newBlockingStub(channel);

                    MotionSensorGetStatusResponse resp = stub.getStatus(
                            MotionSensorGetStatusRequest.newBuilder().build());

                    if (resp.getMotionDetected() && alarmActive) {
                        alarmTriggered = true;
                    }

                } catch (StatusRuntimeException e) {
                    System.err.println("gRPC error on sensor " + device.getDeviceId() + ": " + e.getStatus());
                }
            }
        }
    }

    public synchronized String getAlarmStatus() {
        return alarmTriggered ? "Alarm triggered!" : "Alarm not triggered";
    }

}