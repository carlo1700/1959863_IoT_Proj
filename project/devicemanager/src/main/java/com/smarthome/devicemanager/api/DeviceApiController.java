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

    @PostMapping("/turnOnLight/{deviceId}")
    public String turnOnLight(@PathVariable String deviceId) {
        return service.turnOnLight(deviceId);
    }

    

}
