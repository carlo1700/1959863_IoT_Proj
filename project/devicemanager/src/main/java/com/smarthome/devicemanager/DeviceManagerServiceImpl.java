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

                case "THERMOSTAT":
                    ThermostatServiceGrpc.ThermostatServiceBlockingStub thermostatStub = ThermostatServiceGrpc
                            .newBlockingStub(channel);
                    ThermostatTurnOffResponse thermoResp = thermostatStub
                            .turnOff(ThermostatTurnOffRequest.newBuilder().build());
                    return thermoResp.getSuccess()
                            ? "Thermostat turned off: " + thermoResp.getMessage()
                            : "Failed to turn off thermostat: " + thermoResp.getMessage();

                case "COIL":
                    CoilServiceGrpc.CoilServiceBlockingStub coilStub = CoilServiceGrpc.newBlockingStub(channel);
                    CoilTurnOffResponse coilResp = coilStub.turnOff(CoilTurnOffRequest.newBuilder().build());
                    return coilResp.getSuccess()
                            ? "Coil deactivated: " + coilResp.getMessage()
                            : "Failed to deactivate coil: " + coilResp.getMessage();

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

                case "SOLARPANEL":
                    SolarPanelServiceGrpc.SolarPanelServiceBlockingStub solarStub = SolarPanelServiceGrpc
                            .newBlockingStub(channel);
                    SolarPanelTurnOffResponse solarResp = solarStub
                            .turnOff(SolarPanelTurnOffRequest.newBuilder().build());
                    return solarResp.getSuccess()
                            ? "Solar panel deactivated: " + solarResp.getMessage()
                            : "Failed to deactivate solar panel: " + solarResp.getMessage();

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
                case "COIL": {
                    CoilServiceGrpc.CoilServiceBlockingStub stub = CoilServiceGrpc.newBlockingStub(channel);
                    CoilGetStatusResponse resp = stub.getStatus(CoilGetStatusRequest.newBuilder().build());
                    return "Coil status: powerLevel=" + resp.getPowerLevel() + ", temp=" + resp.getCurrentTemperature()
                            + ", consumption=" + resp.getPowerConsumption();
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
                    return "Oven status: temp=" + resp.getTemperature() + ", setTemp=" + resp.getTemperature();
                }
                case "SOLARPANEL": {
                    SolarPanelServiceGrpc.SolarPanelServiceBlockingStub stub = SolarPanelServiceGrpc
                            .newBlockingStub(channel);
                    SolarPanelGetStatusResponse resp = stub.getStatus(SolarPanelGetStatusRequest.newBuilder().build());
                    return "SolarPanel status: powerOutput=" + resp.getCurrentPowerOutput() + ", dailyProduction="
                            + resp.getDailyEnergyProduction();
                }
                case "THERMOSTAT": {
                    ThermostatServiceGrpc.ThermostatServiceBlockingStub stub = ThermostatServiceGrpc
                            .newBlockingStub(channel);
                    ThermostatGetStatusResponse resp = stub.getStatus(ThermostatGetStatusRequest.newBuilder().build());
                    return "Thermostat status: temp=" + resp.getCurrentTemperature() + ", targetTemp="
                            + resp.getTargetTemperature();
                }
                case "WASHINGMACHINE": {
                    WashingMachineServiceGrpc.WashingMachineServiceBlockingStub stub = WashingMachineServiceGrpc
                            .newBlockingStub(channel);
                    WashingMachineGetStatusResponse resp = stub
                            .getStatus(WashingMachineGetStatusRequest.newBuilder().build());
                    return "WashingMachine status: program=" + resp.getCurrentProgram() + ", remainingTime="
                            + resp.getRemainingTime();
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
                default:
                    return "Set program not supported for device type: " + deviceType;
            }
        } catch (StatusRuntimeException e) {
            return "gRPC error on setProgram for device " + deviceId + ": " + e.getStatus().getDescription();
        }
    }

    public String setPowerLevelCoil(String deviceId, int powerLevel) {
        Device device = devices.get(deviceId);
        if (device == null) {
            return "Device not found: " + deviceId;
        }
        ManagedChannel channel = channels.get(deviceId);
        if (channel == null || channel.isShutdown()) {
            return "gRPC channel non disponibile per device: " + deviceId;
        }
        if (!device.getDeviceType().equalsIgnoreCase("COIL")) {
            return "Device is not a coil: " + deviceId;
        }
        try {
            CoilServiceGrpc.CoilServiceBlockingStub stub = CoilServiceGrpc.newBlockingStub(channel);
            SetPowerLevelRequest req = SetPowerLevelRequest.newBuilder().setPowerLevel(powerLevel).build();
            SetPowerLevelResponse resp = stub.setPowerLevel(req);
            return resp.getSuccess() ? "Coil power level set: " + resp.getMessage()
                    : "Failed to set coil power level: " + resp.getMessage();
        } catch (StatusRuntimeException e) {
            return "gRPC error on setPowerLevel for device " + deviceId + ": " + e.getStatus().getDescription();
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

    public String setTimerOven(String deviceId, int minutes) {
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
            SetTimerRequest req = SetTimerRequest.newBuilder().setMinutes(minutes).build();
            SetTimerResponse resp = stub.setTimer(req);
            return resp.getSuccess() ? "Oven timer set: " + resp.getMessage()
                    : "Failed to set oven timer: " + resp.getMessage();
        } catch (StatusRuntimeException e) {
            return "gRPC error on setTimer for device " + deviceId + ": " + e.getStatus().getDescription();
        }
    }

    public String getEnergyProductionSolarPanel(String deviceId) {
        Device device = devices.get(deviceId);
        if (device == null) {
            return "Device not found: " + deviceId;
        }
        ManagedChannel channel = channels.get(deviceId);
        if (channel == null || channel.isShutdown()) {
            return "gRPC channel non disponibile per device: " + deviceId;
        }
        if (!device.getDeviceType().equalsIgnoreCase("SOLARPANEL")) {
            return "Device is not a solar panel: " + deviceId;
        }

        try {
            SolarPanelServiceGrpc.SolarPanelServiceBlockingStub stub = SolarPanelServiceGrpc.newBlockingStub(channel);

            // Creiamo la request per ottenere tutti i dati (start_time e end_time a 0)
            GetEnergyProductionRequest req = GetEnergyProductionRequest.newBuilder()
                    .setStartTime(0)
                    .setEndTime(0)
                    .build();

            GetEnergyProductionResponse resp = stub.getEnergyProduction(req);

            // Costruiamo la risposta dettagliata
            StringBuilder result = new StringBuilder();
            result.append("Total: ").append(resp.getTotalEnergy()).append(" kWh\n");
            result.append("Readings:\n");

            // Aggiungiamo ogni singola lettura
            for (EnergyReading reading : resp.getReadingsList()) {
                // Convertiamo il timestamp in una data leggibile
                java.time.Instant instant = java.time.Instant.ofEpochSecond(reading.getTimestamp());
                java.time.LocalDateTime dateTime = java.time.LocalDateTime.ofInstant(instant,
                        java.time.ZoneId.systemDefault());
                java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter
                        .ofPattern("yyyy-MM-dd HH:mm:ss");

                result.append("- ").append(dateTime.format(formatter))
                        .append(": ").append(reading.getPowerOutput()).append("W\n");
            }

            return result.toString();

        } catch (StatusRuntimeException e) {
            return "gRPC error on getEnergyProduction for device " + deviceId + ": " + e.getStatus().getDescription();
        }
    }

    public String setTargetTemperatureThermostat(String deviceId, int targetTemperature) {
        Device device = devices.get(deviceId);
        if (device == null) {
            return "Device not found: " + deviceId;
        }
        ManagedChannel channel = channels.get(deviceId);
        if (channel == null || channel.isShutdown()) {
            return "gRPC channel non disponibile per device: " + deviceId;
        }
        if (!device.getDeviceType().equalsIgnoreCase("THERMOSTAT")) {
            return "Device is not a thermostat: " + deviceId;
        }
        try {
            ThermostatServiceGrpc.ThermostatServiceBlockingStub stub = ThermostatServiceGrpc.newBlockingStub(channel);
            SetTargetTemperatureRequest req = SetTargetTemperatureRequest.newBuilder().setTargetTemperature(targetTemperature).build();
            SetTargetTemperatureResponse resp = stub.setTargetTemperature(req);
            return resp.getSuccess() ? "Thermostat target temperature set: " + resp.getMessage()
                    : "Failed to set target temperature: " + resp.getMessage();
        } catch (StatusRuntimeException e) {
            return "gRPC error on setTargetTemperature for device " + deviceId + ": " + e.getStatus().getDescription();
        }
    }

    public String setModeThermostat(String deviceId, String mode) {
        Device device = devices.get(deviceId);
        if (device == null) {
            return "Device not found: " + deviceId;
        }
        ManagedChannel channel = channels.get(deviceId);
        if (channel == null || channel.isShutdown()) {
            return "gRPC channel non disponibile per device: " + deviceId;
        }
        if (!device.getDeviceType().equalsIgnoreCase("THERMOSTAT")) {
            return "Device is not a thermostat: " + deviceId;
        }
        try {
            ThermostatServiceGrpc.ThermostatServiceBlockingStub stub = ThermostatServiceGrpc.newBlockingStub(channel);
            ThermostatMode thermostatMode = ThermostatMode.valueOf(mode.toUpperCase());
            SetModeRequest req = SetModeRequest.newBuilder().setMode(thermostatMode).build();
            SetModeResponse resp = stub.setMode(req);
            return resp.getSuccess() ? "Thermostat mode set: " + resp.getMessage()
                    : "Failed to set mode: " + resp.getMessage();
        } catch (StatusRuntimeException e) {
            return "gRPC error on setMode for device " + deviceId + ": " + e.getStatus().getDescription();
        }
    }

}