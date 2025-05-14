package com.smarthome.devices.blind;

import com.smarthome.core.AbstractDevice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Blind extends AbstractDevice {
    private static final Logger logger = LoggerFactory.getLogger(Blind.class);
    private static final String DEVICE_TYPE = "BLIND";
    
    private ScheduledExecutorService executor;
    
    public Blind(String deviceManagerHost, int deviceManagerPort) {
        super(DEVICE_TYPE, deviceManagerHost, deviceManagerPort);
    }
    
    public Blind(String deviceId, String deviceManagerHost, int deviceManagerPort) {
        super(deviceId, DEVICE_TYPE, deviceManagerHost, deviceManagerPort);
    }

    @Override
    protected void initializeProperties() {
        properties.put("position", "0"); // 0 (closed) to 100 (open)
        properties.put("tiltAngle", "0"); // -90 to 90 degrees
        properties.put("mode", "MANUAL"); // MANUAL, AUTO
        properties.put("lightLevel", "0"); // Current light level 0-100
    }

    @Override
    protected void validateProperties(Map<String, String> newProperties) throws IllegalArgumentException {
        for (Map.Entry<String, String> entry : newProperties.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            
            switch (key) {
                case "position":
                    try {
                        int position = Integer.parseInt(value);
                        if (position < 0 || position > 100) {
                            throw new IllegalArgumentException("Position must be between 0 and 100");
                        }
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException("Position must be a number between 0 and 100");
                    }
                    break;
                    
                case "tiltAngle":
                    try {
                        int angle = Integer.parseInt(value);
                        if (angle < -90 || angle > 90) {
                            throw new IllegalArgumentException("Tilt angle must be between -90 and 90 degrees");
                        }
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException("Tilt angle must be a number between -90 and 90");
                    }
                    break;
                    
                case "mode":
                    if (!value.equals("MANUAL") && !value.equals("AUTO")) {
                        throw new IllegalArgumentException("Mode must be 'MANUAL' or 'AUTO'");
                    }
                    break;
                    
                case "lightLevel":
                    try {
                        int level = Integer.parseInt(value);
                        if (level < 0 || level > 100) {
                            throw new IllegalArgumentException("Light level must be between 0 and 100");
                        }
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException("Light level must be a number between 0 and 100");
                    }
                    break;
                    
                default:
                    throw new IllegalArgumentException("Unknown property: " + key);
            }
        }
    }

    @Override
    protected void onStateUpdated() {
        String position = properties.get("position");
        String tiltAngle = properties.get("tiltAngle");
        logger.debug("Blind {} state updated: position={}%, tiltAngle={}°", 
                getDeviceId(), position, tiltAngle);
    }

    @Override
    public Map<String, String> executeCommand(String command, Map<String, String> parameters) {
        Map<String, String> result = new HashMap<>();
        
        switch (command) {
            case "SET_POSITION":
                if (parameters.containsKey("value")) {
                    String position = parameters.get("value");
                    Map<String, String> positionUpdate = new HashMap<>();
                    positionUpdate.put("position", position);
                    if (updateState(positionUpdate)) {
                        result.put("position", position);
                    } else {
                        result.put("error", "Failed to set position");
                    }
                } else {
                    result.put("error", "Missing position value");
                }
                break;
                
            case "SET_TILT":
                if (parameters.containsKey("value")) {
                    String angle = parameters.get("value");
                    Map<String, String> tiltUpdate = new HashMap<>();
                    tiltUpdate.put("tiltAngle", angle);
                    if (updateState(tiltUpdate)) {
                        result.put("tiltAngle", angle);
                    } else {
                        result.put("error", "Failed to set tilt angle");
                    }
                } else {
                    result.put("error", "Missing tilt angle value");
                }
                break;
                
            case "TOGGLE_MODE":
                String currentMode = properties.get("mode");
                String newMode = "MANUAL".equals(currentMode) ? "AUTO" : "MANUAL";
                Map<String, String> modeUpdate = new HashMap<>();
                modeUpdate.put("mode", newMode);
                if (updateState(modeUpdate)) {
                    result.put("mode", newMode);
                } else {
                    result.put("error", "Failed to toggle mode");
                }
                break;
                
            default:
                result.put("error", "Unknown command: " + command);
                break;
        }
        
        return result;
    }

    @Override
    protected void onStart() {
        executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(() -> {
            if (isOnline()) {
                getDeviceClient().sendStatusUpdate();
                simulateLightLevelChanges();
            }
        }, 5, 15, TimeUnit.SECONDS);
        
        logger.info("Blind {} started and sending periodic updates", getDeviceId());
    }

    private void simulateLightLevelChanges() {
        if ("AUTO".equals(properties.get("mode"))) {
            int currentLight = Integer.parseInt(properties.get("lightLevel"));
            int lightChange = (int) (Math.random() * 20) - 10; // Random change between -10 and +10
            int newLight = Math.max(0, Math.min(100, currentLight + lightChange));
            
            Map<String, String> update = new HashMap<>();
            update.put("lightLevel", String.valueOf(newLight));
            updateState(update);
        }
    }

    @Override
    protected void onStop() {
        if (executor != null) {
            executor.shutdown();
            try {
                if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
                    executor.shutdownNow();
                }
            } catch (InterruptedException e) {
                executor.shutdownNow();
                Thread.currentThread().interrupt();
            }
        }
        
        logger.info("Blind {} stopped", getDeviceId());
    }
    
    public static void main(String[] args) {
        String host = "localhost";
        int port = 9000;
        
        if (args.length >= 1) {
            host = args[0];
        }
        
        if (args.length >= 2) {
            try {
                port = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                logger.error("Invalid port number: {}", args[1]);
                System.exit(1);
            }
        }
        
        Blind blind = new Blind(host, port);
        blind.start();
        
        Runtime.getRuntime().addShutdownHook(new Thread(blind::stop));
        
        logger.info("Blind device running. Press Ctrl+C to stop.");
    }
}