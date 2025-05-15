package com.smarthome.devices.dishwasher;

import com.smarthome.core.AbstractDevice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Dishwasher extends AbstractDevice {
    private static final Logger logger = LoggerFactory.getLogger(Dishwasher.class);
    private static final String DEVICE_TYPE = "DISHWASHER";
    
    private ScheduledExecutorService executor;
    private final Random random = new Random();
    
    public Dishwasher(String deviceManagerHost, int deviceManagerPort) {
        super(DEVICE_TYPE, deviceManagerHost, deviceManagerPort);
    }
    
    public Dishwasher(String deviceId, String deviceManagerHost, int deviceManagerPort) {
        super(deviceId, DEVICE_TYPE, deviceManagerHost, deviceManagerPort);
    }

    @Override
    protected void initializeProperties() {
        properties.put("power", "OFF");
        properties.put("status", "IDLE"); // IDLE, WASHING, RINSING, DRYING, FINISHED
        properties.put("program", "NORMAL"); // ECO, NORMAL, INTENSIVE, QUICK, RINSE
        properties.put("temperature", "50"); // in Celsius
        properties.put("timeRemaining", "0"); // in minutes
        properties.put("doorLocked", "false");
        properties.put("waterLevel", "0"); // 0-100%
        properties.put("rinseAidLevel", "100"); // 0-100%
        properties.put("saltLevel", "100"); // 0-100%
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
                        !value.equals("RINSING") && !value.equals("DRYING") && 
                        !value.equals("FINISHED")) {
                        throw new IllegalArgumentException("Invalid status value");
                    }
                    break;
                    
                case "program":
                    if (!value.equals("ECO") && !value.equals("NORMAL") && 
                        !value.equals("INTENSIVE") && !value.equals("QUICK") && 
                        !value.equals("RINSE")) {
                        throw new IllegalArgumentException("Invalid program value");
                    }
                    break;
                    
                case "temperature":
                    try {
                        int temp = Integer.parseInt(value);
                        if (temp < 30 || temp > 75) {
                            throw new IllegalArgumentException("Temperature must be between 30 and 75°C");
                        }
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException("Temperature must be a valid number");
                    }
                    break;
                    
                case "timeRemaining":
                    try {
                        int time = Integer.parseInt(value);
                        if (time < 0 || time > 240) {
                            throw new IllegalArgumentException("Time remaining must be between 0 and 240 minutes");
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
                case "rinseAidLevel":
                case "saltLevel":
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
        logger.debug("Dishwasher {} state updated: status={}, program={}, timeRemaining={}min", 
                getDeviceId(), status, program, timeRemaining);
    }

    @Override
    public Map<String, String> executeCommand(String command, Map<String, String> parameters) {
        Map<String, String> result = new HashMap<>();
        
        switch (command) {
            case "START_WASH":
                if (properties.get("power").equals("OFF")) {
                    result.put("error", "Dishwasher is powered off");
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
                    case "QUICK":
                        washTime = 30;
                        break;
                    case "ECO":
                        washTime = 180;
                        break;
                    case "INTENSIVE":
                        washTime = 150;
                        break;
                    case "RINSE":
                        washTime = 15;
                        break;
                    default: // NORMAL
                        washTime = 120;
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
                    String newProgram = parameters.get("value");
                    Map<String, String> programUpdate = new HashMap<>();
                    programUpdate.put("program", newProgram);
                    if (updateState(programUpdate)) {
                        result.put("program", newProgram);
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
                
            case "PAUSE":
                if (properties.get("status").equals("WASHING") || 
                    properties.get("status").equals("RINSING")) {
                    Map<String, String> pauseUpdate = new HashMap<>();
                    pauseUpdate.put("status", "IDLE");
                    if (updateState(pauseUpdate)) {
                        result.put("status", "IDLE");
                    } else {
                        result.put("error", "Failed to pause cycle");
                    }
                } else {
                    result.put("error", "Cannot pause: dishwasher is not running");
                }
                break;
                
            case "CHECK_LEVELS":
                result.put("rinseAidLevel", properties.get("rinseAidLevel"));
                result.put("saltLevel", properties.get("saltLevel"));
                result.put("detergentLevel", properties.get("detergentLevel"));
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
        
        // Simulate consumable usage
        executor.scheduleAtFixedRate(this::updateConsumableLevels, 300, 300, TimeUnit.SECONDS);
        
        logger.info("Dishwasher {} started and sending periodic updates", getDeviceId());
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
                } else if (timeRemaining <= 15) {
                    update.put("status", "DRYING");
                } else if (timeRemaining <= 30) {
                    update.put("status", "RINSING");
                }
                
                updateState(update);
            }
        } catch (NumberFormatException e) {
            logger.error("Error updating wash cycle", e);
        }
    }

    private void updateConsumableLevels() {
        if (!isOnline() || !properties.get("status").equals("WASHING")) {
            return;
        }
        
        try {
            Map<String, String> update = new HashMap<>();
            boolean updateNeeded = false;
            
            // Update consumable levels
            for (String consumable : new String[]{"rinseAidLevel", "saltLevel", "detergentLevel"}) {
                int level = Integer.parseInt(properties.get(consumable));
                if (level > 0) {
                    level -= random.nextInt(5); // Random decrease between 0-4%
                    level = Math.max(0, level);
                    update.put(consumable, String.valueOf(level));
                    updateNeeded = true;
                    
                    if (level < 20) {
                        logger.warn("Dishwasher {} {} is low: {}%", 
                                getDeviceId(), consumable, level);
                    }
                }
            }
            
            if (updateNeeded) {
                updateState(update);
            }
        } catch (NumberFormatException e) {
            logger.error("Error updating consumable levels", e);
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
        
        logger.info("Dishwasher {} stopped", getDeviceId());
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
        
        Dishwasher dishwasher = new Dishwasher(host, port);
        dishwasher.start();
        
        Runtime.getRuntime().addShutdownHook(new Thread(dishwasher::stop));
        
        logger.info("Dishwasher device running. Press Ctrl+C to stop.");
    }
}