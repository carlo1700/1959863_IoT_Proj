package com.smarthome.devicemanager.api;

import com.smarthome.devicemanager.DeviceManagerServiceImpl;
import com.smarthome.proto.Device;
import com.smarthome.proto.RegisterDeviceRequest;
import com.smarthome.proto.RegisterDeviceResponse;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/devices")
public class DeviceApiController {

    private final DeviceManagerServiceImpl service;

    public DeviceApiController(DeviceManagerServiceImpl service) {
        this.service = service;
    }

    @GetMapping
    public List<Device> getDevices() {
        return service.getDevices();
    }

    @GetMapping("/count")
    public int getDeviceCount() {
        return service.getDevices().size();
    }

    @PostMapping("/turnOnDevice/{deviceId}")
    public String turnOnDevice(@PathVariable String deviceId) {
        return service.turnOnDevice(deviceId);
    }

    @PostMapping("/setupBlind/{deviceId}")
    public String setUpBlind(@PathVariable String deviceId) {
        return service.SetUpBlind(deviceId);
    }

    @PostMapping("/turnOffDevice/{deviceId}")
    public String turnOffDevice(@PathVariable String deviceId) {
        return service.turnOffDevice(deviceId);
    }

    @PostMapping("/turnOnLight/{deviceId}")
    public String turnOnLight(@PathVariable String deviceId) {
        return service.turnOnLight(deviceId);
    }

    @GetMapping("/status/{deviceId}")
    public String getStatusDevice(@PathVariable String deviceId) {
        return service.getStatusDevice(deviceId);
    }

    @PostMapping("/startDevice/{deviceId}")
    public String startDevice(@PathVariable String deviceId) {
        return service.startDevice(deviceId);
    }

    @PostMapping("/stopDevice/{deviceId}")
    public String stopDevice(@PathVariable String deviceId) {
        return service.stopDevice(deviceId);
    }

    @PostMapping("/setProgram/{deviceId}")
    public String setProgramDevice(@PathVariable String deviceId, @RequestParam int program) {
        return service.setProgramDevice(deviceId, program);
    }

    @PostMapping("/setPowerLevelCoil/{deviceId}")
    public String setPowerLevelCoil(@PathVariable String deviceId, @RequestParam int powerLevel) {
        return service.setPowerLevelCoil(deviceId, powerLevel);
    }

    @PostMapping("/setTemperatureOven/{deviceId}")
    public String setTemperatureOven(@PathVariable String deviceId, @RequestParam int temperature) {
        return service.setTemperatureOven(deviceId, temperature);
    }

    @PostMapping("/setTimerOven/{deviceId}")
    public String setTimerOven(@PathVariable String deviceId, @RequestParam int minutes) {
        return service.setTimerOven(deviceId, minutes);
    }

    @GetMapping("/getEnergyProductionSolarPanel/{deviceId}")
    public String getEnergyProductionSolarPanel(@PathVariable String deviceId) {
        return service.getEnergyProductionSolarPanel(deviceId);
    }

    @PostMapping("/setTargetTemperatureThermostat/{deviceId}")
    public String setTargetTemperatureThermostat(@PathVariable String deviceId, @RequestParam int targetTemperature) {
        return service.setTargetTemperatureThermostat(deviceId, targetTemperature);
    }

    @PostMapping("/setModeThermostat/{deviceId}")
    public String setModeThermostat(@PathVariable String deviceId, @RequestParam String mode) {
        return service.setModeThermostat(deviceId, mode);
    }

}