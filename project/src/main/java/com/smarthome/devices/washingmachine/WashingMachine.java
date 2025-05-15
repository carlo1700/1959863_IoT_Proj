package com.smarthome.devices.washingmachine;

import com.smarthome.core.AbstractDevice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class WashingMachine extends AbstractDevice {
    private static final Logger logger = LoggerFactory.getLogger(WashingMachine.class);
    private static final String DEVICE_TYPE = "WASHING_MACHINE";
    
    private ScheduledExecutorService executor;
    private final Random random = new Random();
    
    public WashingMachine(String deviceManagerHost, int deviceManagerPort) {
        super(DEVICE_TYPE, deviceManagerHost, deviceManagerPort);
    }
    
    public WashingMachine(String deviceId, String deviceManagerHost, int deviceManagerPort) {
        super(deviceId, DEVICE_TYPE, deviceManagerHost, deviceManagerPort);
    }

    @Override
    protected void initializeProperties() {
        properties.put("power", "OFF");
        properties.put("status", "IDLE"); // IDLE, WASHING, RINSING, SPINNING, FINISHED
        properties.put("program", "NORMAL"); // NORMAL, DELICATE, HEAVY_DUTY, QUICK_WASH
        properties.put("temperature", "30"); // in Celsius
        properties.put("spinSpeed", "800"); // in RPM
        properties.put("timeRemaining", "0"); // in minutes
        properties.put("doorLocked", "false");
        properties.put("waterLevel", "0"); // 0-100%
        properties.put("detergentLevel", "100"); // 0-100%
    }

    @Override
    protected void validateProperties(Map<String, String> newProperties) throws IllegalArgumentException {
        for (Map.Entry<String, String> entry : newProperties.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            
            switch (key) {
                case "power":
                    if (!value.equals("ON") && !value.equals("OFF")) {
                        throw new IllegalArgumentException("Power must be 'ON' or 'OFF'");
                    }
                    break;
                    
                case "status":
                    if (!value.equals("IDLE") && !value.equals("WASHING") && 
                        !value.equals("RINSING") && !value.equals("SPINNING") && 
                        !value.equals("FINISHED")) {
                        throw new IllegalArgumentException("Invalid status value");
                    }
                    break;
                    
                case "program":
                    if (!value.equals("NORMAL") && !value.equals("DELICATE") && 
                        !value.equals("HEAVY_DUTY") && !value.equals("QUICK_WASH")) {
                        throw new IllegalArgumentException("Invalid program value");
                    }
                    break;
                    
                case "temperature":
                    try {
                        int temp = Integer.parseInt(value);
                        if (temp < 20 || temp > 90) {
                            throw new IllegalArgumentException("Temperature must be between 20 and 90°C");
                        }
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException("Temperature must be a valid number");
                    }
                    break;
                    
                case "spinSpeed":
                    try {
                        int speed = Integer.parseInt(value);
                        if (speed < 400 || speed > 1600) {
                            throw new IllegalArgumentException("Spin speed must be between 400 and 1600 RPM");
                        }
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException("Spin speed must be a valid number");
                    }
                    break;
                    
                case "timeRemaining":
                    try {
                        int time = Integer.parseInt(value);
                        if (time < 0 || time > 180) {
                            throw new IllegalArgumentException("Time remaining must be between 0 and 180 minutes");
                        }
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException("Time remaining must be a valid number");
                    }
                    break;
                    
                case "doorLocked":
                    if (!value.equals("true") && !value.equals("false")) {
                        throw new IllegalArgumentException("Door locked must be 'true' or 'false'");
                    }
                    break;
                    
                case "waterLevel":
                case "detergentLevel":
                    try {
                        int level = Integer.parseInt(value);
                        if (level < 0 || level > 100) {
                            throw new IllegalArgumentException(key + " must be between 0 and 100%");
                        }
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException(key + " must be a valid number");
                    }
                    break;
                    
                default:
                    throw new IllegalArgumentException("Unknown property: " + key);
            }
        }
    }

    @Override
    protected void onStateUpdated() {
        String status = properties.get("status");
        String program = properties.get("program");
        String timeRemaining = properties.get("timeRemaining");
        logger.debug("Washing Machine {} state updated: status={}, program={}, timeRemaining={}min", 
                getDeviceId(), status, program, timeRemaining);
    }

    @Override
    public Map<String, String> executeCommand(String command, Map<String, String> parameters) {
        Map<String, String> result = new HashMap<>();
        
        switch (command) {
            case "START_WASH":
                if (properties.get("power").equals("OFF")) {
                    result.put("error", "Machine is powered off");
                    break;
                }
                
                Map<String, String> startUpdate = new HashMap<>();
                startUpdate.put("status", "WASHING");
                startUpdate.put("doorLocked", "true");
                startUpdate.put("waterLevel", "50");
                
                // Set time based on program
                String program = properties.get("program");
                int washTime;
                switch (program) {
                    case "QUICK_WASH":
                        washTime = 30;
                        break;
                    case "DELICATE":
                        washTime = 45;
                        break;
                    case "HEAVY_DUTY":
                        washTime = 90;
                        break;
                    default: // NORMAL
                        washTime = 60;
                        break;
                }
                startUpdate.put("timeRemaining", String.valueOf(washTime));
                
                if (updateState(startUpdate)) {
                    result.putAll(startUpdate);
                } else {
                    result.put("error", "Failed to start wash cycle");
                }
                break;
                
            case "SET_PROGRAM":
                if (parameters.containsKey("value")) {
                    String program = parameters.get("value");
                    Map<String, String> programUpdate = new HashMap<>();
                    programUpdate.put("program", program);
                    if (updateState(programUpdate)) {
                        result.put("program", program);
                    } else {
                        result.put("error", "Failed to set program");
                    }
                } else {
                    result.put("error", "Missing program value");
                }
                break;
                
            case "SET_TEMPERATURE":
                if (parameters.containsKey("value")) {
                    String temperature = parameters.get("value");
                    Map<String, String> tempUpdate = new HashMap<>();
                    tempUpdate.put("temperature", temperature);
                    if (updateState(tempUpdate)) {
                        result.put("temperature", temperature);
                    } else {
                        result.put("error", "Failed to set temperature");
                    }
                } else {
                    result.put("error", "Missing temperature value");
                }
                break;
                
            case "SET_SPIN_SPEED":
                if (parameters.containsKey("value")) {
                    String speed = parameters.get("value");
                    Map<String, String> speedUpdate = new HashMap<>();
                    speedUpdate.put("spinSpeed", speed);
                    if (updateState(speedUpdate)) {
                        result.put("spinSpeed", speed);
                    } else {
                        result.put("error", "Failed to set spin speed");
                    }
                } else {
                    result.put("error", "Missing spin speed value");
                }
                break;
                
            case "PAUSE":
                if (properties.get("status").equals("WASHING") || 
                    properties.get("status").equals("RINSING") || 
                    properties.get("status").equals("SPINNING")) {
                    Map<String, String> pauseUpdate = new HashMap<>();
                    pauseUpdate.put("status", "IDLE");
                    if (updateState(pauseUpdate)) {
                        result.put("status", "IDLE");
                    } else {
                        result.put("error", "Failed to pause cycle");
                    }
                } else {
                    result.put("error", "Cannot pause: machine is not running");
                }
                break;
                
            case "TOGGLE_POWER":
                String currentPower = properties.get("power");
                String newPower = "ON".equals(currentPower) ? "OFF" : "ON";
                Map<String, String> powerUpdate = new HashMap<>();
                powerUpdate.put("power", newPower);
                if ("OFF".equals(newPower)) {
                    powerUpdate.put("status", "IDLE");
                    powerUpdate.put("timeRemaining", "0");
                    powerUpdate.put("doorLocked", "false");
                    powerUpdate.put("waterLevel", "0");
                }
                if (updateState(powerUpdate)) {
                    result.putAll(powerUpdate);
                } else {
                    result.put("error", "Failed to toggle power");
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
        executor = Executors.newScheduledThreadPool(2);
        
        // Send status updates
        executor.scheduleAtFixedRate(() -> {
            if (isOnline()) {
                getDeviceClient().sendStatusUpdate();
            }
        }, 5, 10, TimeUnit.SECONDS);
        
        // Simulate wash cycle progress
        executor.scheduleAtFixedRate(this::updateWashCycle, 60, 60, TimeUnit.SECONDS);
        
        logger.info("Washing Machine {} started and sending periodic updates", getDeviceId());
    }

    private void updateWashCycle() {
        if (!isOnline() || properties.get("power").equals("OFF")) {
            return;
        }
        
        String status = properties.get("status");
        if (status.equals("IDLE") || status.equals("FINISHED")) {
            return;
        }
        
        try {
            int timeRemaining = Integer.parseInt(properties.get("timeRemaining"));
            if (timeRemaining > 0) {
                timeRemaining--;
                Map<String, String> update = new HashMap<>();
                update.put("timeRemaining", String.valueOf(timeRemaining));
                
                // Update status based on time remaining
                if (timeRemaining == 0) {
                    update.put("status", "FINISHED");
                    update.put("doorLocked", "false");
                    update.put("waterLevel", "0");
                } else if (timeRemaining <= 5) {
                    update.put("status", "SPINNING");
                } else if (timeRemaining <= 15) {
                    update.put("status", "RINSING");
                }
                
                updateState(update);
            }
        } catch (NumberFormatException e) {
            logger.error("Error updating wash cycle", e);
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
        
        logger.info("Washing Machine {} stopped", getDeviceId());
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
        
        WashingMachine washingMachine = new WashingMachine(host, port);
        washingMachine.start();
        
        Runtime.getRuntime().addShutdownHook(new Thread(washingMachine::stop));
        
        logger.info("Washing Machine device running. Press Ctrl+C to stop.");
    }
}