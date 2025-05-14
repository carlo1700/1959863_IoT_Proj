package com.smarthome.devices.coil;

import com.smarthome.core.AbstractDevice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Coil extends AbstractDevice {
    private static final Logger logger = LoggerFactory.getLogger(Coil.class);
    private static final String DEVICE_TYPE = "COIL";
    
    private ScheduledExecutorService executor;
    
    public Coil(String deviceManagerHost, int deviceManagerPort) {
        super(DEVICE_TYPE, deviceManagerHost, deviceManagerPort);
    }
    
    public Coil(String deviceId, String deviceManagerHost, int deviceManagerPort) {
        super(deviceId, DEVICE_TYPE, deviceManagerHost, deviceManagerPort);
    }

    @Override
    protected void initializeProperties() {
        properties.put("state", "RETRACTED"); // RETRACTED, EXTENDED
        properties.put("position", "0"); // 0-100%
        properties.put("autoMode", "OFF"); // ON, OFF
        properties.put("lastMaintenance", String.valueOf(System.currentTimeMillis()));
    }

    @Override
    protected void validateProperties(Map<String, String> newProperties) throws IllegalArgumentException {
        for (Map.Entry<String, String> entry : newProperties.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            
            switch (key) {
                case "state":
                    if (!value.equals("RETRACTED") && !value.equals("EXTENDED")) {
                        throw new IllegalArgumentException("State must be 'RETRACTED' or 'EXTENDED'");
                    }
                    break;
                    
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
                    
                case "autoMode":
                    if (!value.equals("ON") && !value.equals("OFF")) {
                        throw new IllegalArgumentException("Auto mode must be 'ON' or 'OFF'");
                    }
                    break;
                    
                case "lastMaintenance":
                    try {
                        Long.parseLong(value);
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException("Last maintenance must be a valid timestamp");
                    }
                    break;
                    
                default:
                    throw new IllegalArgumentException("Unknown property: " + key);
            }
        }
    }

    @Override
    protected void onStateUpdated() {
        String state = properties.get("state");
        String position = properties.get("position");
        logger.debug("Coil {} state updated: state={}, position={}%", 
                getDeviceId(), state, position);
    }

    @Override
    public Map<String, String> executeCommand(String command, Map<String, String> parameters) {
        Map<String, String> result = new HashMap<>();
        
        switch (command) {
            case "EXTEND":
                Map<String, String> extendUpdate = new HashMap<>();
                extendUpdate.put("state", "EXTENDED");
                extendUpdate.put("position", "100");
                if (updateState(extendUpdate)) {
                    result.putAll(extendUpdate);
                } else {
                    result.put("error", "Failed to extend coil");
                }
                break;
                
            case "RETRACT":
                Map<String, String> retractUpdate = new HashMap<>();
                retractUpdate.put("state", "RETRACTED");
                retractUpdate.put("position", "0");
                if (updateState(retractUpdate)) {
                    result.putAll(retractUpdate);
                } else {
                    result.put("error", "Failed to retract coil");
                }
                break;
                
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
                
            case "TOGGLE_AUTO":
                String currentMode = properties.get("autoMode");
                String newMode = "ON".equals(currentMode) ? "OFF" : "ON";
                Map<String, String> modeUpdate = new HashMap<>();
                modeUpdate.put("autoMode", newMode);
                if (updateState(modeUpdate)) {
                    result.put("autoMode", newMode);
                } else {
                    result.put("error", "Failed to toggle auto mode");
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
            }
        }, 5, 15, TimeUnit.SECONDS);
        
        logger.info("Coil {} started and sending periodic updates", getDeviceId());
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
        
        logger.info("Coil {} stopped", getDeviceId());
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
        
        Coil coil = new Coil(host, port);
        coil.start();
        
        Runtime.getRuntime().addShutdownHook(new Thread(coil::stop));
        
        logger.info("Coil device running. Press Ctrl+C to stop.");
    }
}