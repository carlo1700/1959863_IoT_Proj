package com.smarthome.devicemanager.api;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smarthome.devicemanager.DeviceManagerServiceImpl;

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

    // POST per registrare un token push
    @PostMapping("/register-token")
    public ResponseEntity<String> registerToken(@RequestBody Map<String, String> body) {
        try {
            String token = body.get("token");
            service.saveToken(token);   // ora chiami direttamente il DeviceManagerServiceImpl
            return ResponseEntity.ok("Token registrato");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Errore: " + e.getMessage());
        }
    }

    // POST per forzare lo scatto dell’allarme (utile nei test)
    @PostMapping("/trigger")
    public ResponseEntity<String> triggerAlarm() {
        try {
            service.forceTriggerAlarm();
            return ResponseEntity.ok("Alarm triggered manually and notifications sent.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500)
                    .body("Error: " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }
}
