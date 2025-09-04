package com.smarthome.devicemanager.api;

import com.smarthome.devicemanager.DeviceManagerServiceImpl;
import com.smarthome.proto.Device;
import com.smarthome.proto.RegisterDeviceResponse;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

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
    public ResponseEntity<String> turnOnDevice(@PathVariable("deviceId") String deviceId) {
        try {
            return ResponseEntity.ok(service.turnOnDevice(deviceId));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error: " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }

    @PostMapping("/setupBlind/{deviceId}")
    public String setUpBlind(@PathVariable("deviceId") String deviceId) {
        return service.SetUpBlind(deviceId);
    }

     @PostMapping("/setdownBlind/{deviceId}")
    public String setDownBlind(@PathVariable String deviceId) {
        return service.SetDownBlind(deviceId);
    }

    @PostMapping("/turnOffDevice/{deviceId}")
    public ResponseEntity<String> turnOffDevice(@PathVariable("deviceId") String deviceId) {
        try {
            return ResponseEntity.ok(service.turnOffDevice(deviceId));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error: " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }

    @GetMapping("/status/{deviceId}")
    public String getStatusDevice(@PathVariable("deviceId") String deviceId) {
        return service.getStatusDevice(deviceId);
    }

    @PostMapping("/startDevice/{deviceId}")
    public String startDevice(@PathVariable("deviceId") String deviceId) {
        return service.startDevice(deviceId);
    }

    @PostMapping("/stopDevice/{deviceId}")
    public String stopDevice(@PathVariable("deviceId") String deviceId) {
        return service.stopDevice(deviceId);
    }

    @PostMapping("/setProgram/{deviceId}")
    public String setProgramDevice(@PathVariable("deviceId") String deviceId,
                                   @RequestParam(name = "program") int program) {
        return service.setProgramDevice(deviceId, program);
    }

    @PostMapping("/setTemperatureOven/{deviceId}")
    public String setTemperatureOven(@PathVariable("deviceId") String deviceId,
                                     @RequestParam(name = "temperature") int temperature) {
        return service.setTemperatureOven(deviceId, temperature);
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterDeviceResponse> register(
            @RequestParam(name = "id") String id,
            @RequestParam(name = "type") String type,
            @RequestParam(name = "address") String address,
            @RequestParam(name = "port") int port) {
        try {
            return ResponseEntity.ok(service.registerDeviceHttp(id, type, address, port));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500)
                    .body(RegisterDeviceResponse.newBuilder()
                            .setSuccess(false)
                            .setMessage("Error: " + e.getClass().getSimpleName() + " - " + e.getMessage())
                            .build());
        }
    }
}
