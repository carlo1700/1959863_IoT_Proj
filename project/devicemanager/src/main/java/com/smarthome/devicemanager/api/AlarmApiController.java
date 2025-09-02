package com.smarthome.devicemanager.api;

import com.smarthome.devicemanager.DeviceManagerServiceImpl;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/alarm")
public class AlarmApiController {

    private final DeviceManagerServiceImpl service;

    public AlarmApiController(DeviceManagerServiceImpl service) {
        this.service = service;
    }

    // POST per attivare/disattivare l'allarme
    @PostMapping("/activate")
    public ResponseEntity<String> activateAlarm(@RequestParam("enable") boolean enable) {
        try {
            String result = service.activateAlarm(enable);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500)
                    .body("Error: " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }

    // GET per controllare se l'allarme è scattato
    @GetMapping("/status")
    public ResponseEntity<String> getAlarmStatus() {
        try {
            String status = service.getAlarmStatus();
            return ResponseEntity.ok(status);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500)
                    .body("Error: " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }
}
