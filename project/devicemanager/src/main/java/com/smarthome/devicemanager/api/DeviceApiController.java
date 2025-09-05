package com.smarthome.devicemanager.api;

import com.smarthome.devicemanager.DeviceManagerServiceImpl;
import com.smarthome.proto.Device;
import com.smarthome.proto.RegisterDeviceResponse;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.Set;

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
    // ===== Rooms =====
    @PostMapping("/rooms/{roomId}")
    public ResponseEntity<String> createRoom(@PathVariable("roomId") String roomId) {
        return ResponseEntity.ok(service.createRoom(roomId));
    }

    @PostMapping("/rooms/{roomId}/devices/{deviceId}")
    public ResponseEntity<String> addDeviceToRoom(@PathVariable("roomId") String roomId,
                                                @PathVariable("deviceId") String deviceId) {
        return ResponseEntity.ok(service.addDeviceToRoom(roomId, deviceId));
    }

    @DeleteMapping("/rooms/{roomId}/devices/{deviceId}")
    public ResponseEntity<String> removeDeviceFromRoom(@PathVariable("roomId") String roomId,
                                                    @PathVariable("deviceId") String deviceId) {
        return ResponseEntity.ok(service.removeDeviceFromRoom(roomId, deviceId));
    }

    @GetMapping("/rooms/{roomId}/devices")
    public ResponseEntity<Set<String>> listRoomDevices(@PathVariable("roomId") String roomId) {
        return ResponseEntity.ok(service.listRoomDevices(roomId));
    }

    // broadcast comandi
    @PostMapping("/rooms/{roomId}/turnOn")
    public ResponseEntity<Map<String,String>> turnOnRoom(@PathVariable("roomId") String roomId) {
        return ResponseEntity.ok(service.turnOnRoom(roomId));
    }

    @PostMapping("/rooms/{roomId}/turnOff")
    public ResponseEntity<Map<String,String>> turnOffRoom(@PathVariable("roomId") String roomId) {
        return ResponseEntity.ok(service.turnOffRoom(roomId));
    }

    @PostMapping("/rooms/{roomId}/start")
    public ResponseEntity<Map<String,String>> startRoom(@PathVariable("roomId") String roomId) {
        return ResponseEntity.ok(service.startRoom(roomId));
    }

    @PostMapping("/rooms/{roomId}/stop")
    public ResponseEntity<Map<String,String>> stopRoom(@PathVariable("roomId") String roomId) {
        return ResponseEntity.ok(service.stopRoom(roomId));
    }

    @PostMapping("/rooms/{roomId}/setProgram")
    public ResponseEntity<Map<String,String>> setProgramRoom(@PathVariable("roomId") String roomId,
                                                            @RequestParam(name="program") int program) {
        return ResponseEntity.ok(service.setProgramRoom(roomId, program));
    }

    @PostMapping("/rooms/{roomId}/setTemperature")
    public ResponseEntity<Map<String,String>> setTemperatureRoom(@PathVariable("roomId") String roomId,
                                                                @RequestParam(name="temperature") int temperature) {
        return ResponseEntity.ok(service.setTemperatureRoom(roomId, temperature));
    }

    // ===== Groups =====
    @PostMapping("/groups/{groupId}")
    public ResponseEntity<String> createGroup(@PathVariable("groupId") String groupId) {
        return ResponseEntity.ok(service.createGroup(groupId));
    }

    @PostMapping("/groups/{groupId}/devices/{deviceId}")
    public ResponseEntity<String> addDeviceToGroup(@PathVariable("groupId") String groupId,
                                                @PathVariable("deviceId") String deviceId) {
        return ResponseEntity.ok(service.addDeviceToGroup(groupId, deviceId));
    }

    @DeleteMapping("/groups/{groupId}/devices/{deviceId}")
    public ResponseEntity<String> removeDeviceFromGroup(@PathVariable("groupId") String groupId,
                                                        @PathVariable("deviceId") String deviceId) {
        return ResponseEntity.ok(service.removeDeviceFromGroup(groupId, deviceId));
    }

    @GetMapping("/groups/{groupId}/devices")
    public ResponseEntity<Set<String>> listGroupDevices(@PathVariable("groupId") String groupId) {
        return ResponseEntity.ok(service.listGroupDevices(groupId));
    }

    // broadcast comandi
    @PostMapping("/groups/{groupId}/turnOn")
    public ResponseEntity<Map<String,String>> turnOnGroup(@PathVariable("groupId") String groupId) {
        return ResponseEntity.ok(service.turnOnGroup(groupId));
    }

    @PostMapping("/groups/{groupId}/turnOff")
    public ResponseEntity<Map<String,String>> turnOffGroup(@PathVariable("groupId") String groupId) {
        return ResponseEntity.ok(service.turnOffGroup(groupId));
    }

    @PostMapping("/groups/{groupId}/start")
    public ResponseEntity<Map<String,String>> startGroup(@PathVariable("groupId") String groupId) {
        return ResponseEntity.ok(service.startGroup(groupId));
    }

    @PostMapping("/groups/{groupId}/stop")
    public ResponseEntity<Map<String,String>> stopGroup(@PathVariable("groupId") String groupId) {
        return ResponseEntity.ok(service.stopGroup(groupId));
    }

    @PostMapping("/groups/{groupId}/setProgram")
    public ResponseEntity<Map<String,String>> setProgramGroup(@PathVariable("groupId") String groupId,
                                                            @RequestParam(name="program") int program) {
        return ResponseEntity.ok(service.setProgramGroup(groupId, program));
    }

    @PostMapping("/groups/{groupId}/setTemperature")
    public ResponseEntity<Map<String,String>> setTemperatureGroup(@PathVariable("groupId") String groupId,
                                                                @RequestParam(name="temperature") int temperature) {
        return ResponseEntity.ok(service.setTemperatureGroup(groupId, temperature));
    }
    
    @GetMapping("/rooms")
    public ResponseEntity<?> listRooms() {
        try {
            return ResponseEntity.ok(service.listRooms()); // esponi un passthrough
        } catch (Exception e) {
            return ResponseEntity.status(500).body("ERROR listing rooms: " + e.getMessage());
        }
    }

    @GetMapping("/groups")
    public ResponseEntity<?> listGroups() {
        try {
            return ResponseEntity.ok(service.listGroups());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("ERROR listing groups: " + e.getMessage());
        }
    }
    
    @GetMapping("/rooms/{roomId}")
    public ResponseEntity<Set<String>> getRoom(@PathVariable String roomId) {
        Set<String> devices = service.listAllRooms().get(roomId);
        if (devices == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(devices);
    }

    @GetMapping("/groups/{groupId}")
    public ResponseEntity<Set<String>> getGroup(@PathVariable String groupId) {
        Set<String> devices = service.listAllGroups().get(groupId);
        if (devices == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(devices);
    }
    
    // in DeviceApiController.java (o nuovo LogApiController)
    @GetMapping("/logs/recent")
    public ResponseEntity<?> recentLogs(@RequestParam(defaultValue = "50") int limit) {
        try { return ResponseEntity.ok(service.getRecentLogs(limit)); }
        catch (Exception e) { return ResponseEntity.status(500).body("ERROR listing logs: " + e.getMessage()); }
    }

    @GetMapping("/logs/byDevice/{deviceId}")
    public ResponseEntity<?> logsByDevice(@PathVariable String deviceId,
                                        @RequestParam(defaultValue = "50") int limit) {
        try { return ResponseEntity.ok(service.getLogsByDevice(deviceId, limit)); }
        catch (Exception e) { return ResponseEntity.status(500).body("ERROR listing logs for device: " + e.getMessage()); }
    }
    
    // ---- Rename/Delete Rooms ----
    @PatchMapping("/rooms/{oldId}/rename")
    public ResponseEntity<String> renameRoom(@PathVariable String oldId,
                                            @RequestParam("newId") String newId) {
        return ResponseEntity.ok(service.renameRoom(oldId, newId));
    }

    @DeleteMapping("/rooms/{roomId}")
    public ResponseEntity<String> deleteRoom(@PathVariable String roomId) {
        return ResponseEntity.ok(service.deleteRoom(roomId));
    }

    // ---- Rename/Delete Groups ----
    @PatchMapping("/groups/{oldId}/rename")
    public ResponseEntity<String> renameGroup(@PathVariable String oldId,
                                            @RequestParam("newId") String newId) {
        return ResponseEntity.ok(service.renameGroup(oldId, newId));
    }

    @DeleteMapping("/groups/{groupId}")
    public ResponseEntity<String> deleteGroup(@PathVariable String groupId) {
        return ResponseEntity.ok(service.deleteGroup(groupId));
    }

    // ---- Rename Device ----
    @PatchMapping("/devices/{oldId}/rename")
    public ResponseEntity<String> renameDevice(@PathVariable String oldId,
                                            @RequestParam("newId") String newId) {
        return ResponseEntity.ok(service.renameDevice(oldId, newId));
    }

    // Imposta/aggiorna display name
    @PatchMapping("/devices/{deviceId}/displayName")
    public ResponseEntity<String> setDisplayName(@PathVariable String deviceId,
                                                @RequestParam("name") String name) {
        return ResponseEntity.ok(service.setDeviceDisplayName(deviceId, name));
    }

    // Cancella display name
    @DeleteMapping("/devices/{deviceId}/displayName")
    public ResponseEntity<String> clearDisplayName(@PathVariable String deviceId) {
        return ResponseEntity.ok(service.clearDeviceDisplayName(deviceId));
    }

    // Elenco alias (solo quelli impostati)
    @GetMapping("/devices/displayNames")
    public ResponseEntity<Map<String,String>> listDisplayNames() {
        return ResponseEntity.ok(service.listDisplayNames());
    }

}
