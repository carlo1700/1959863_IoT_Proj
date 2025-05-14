package com.smarthome.devices.oven;

import com.smarthome.core.AbstractDevice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Oven extends AbstractDevice {
    private static final Logger logger = LoggerFactory.getLogger(Oven.class);
    private static final String DEVICE_TYPE = "OVEN";
    
    private ScheduledExecutorService executor;
    private final Random random = new Random();
    
    public Oven(String deviceManagerHost, int deviceManagerPort) {
        super(DEVICE_TYPE, deviceManagerHost, deviceManagerPort);
    }
    
    public Oven(String deviceId, String deviceManagerHost, int deviceManagerPort) {
        super(deviceId, DEVICE_TYPE, deviceManagerHost, deviceManagerPort);
    }

    @Override
    protected void initializeProperties() {
        properties.put("power", "OFF");
        properties.put("temperature", "0"); // Current temperature in Celsius
        properties.put("targetTemperature", "0"); // Target temperature in Celsius
        properties.put("mode", "OFF"); // OFF, BAKE, BROIL, CONVECTION
        properties.put("timer", "0"); // Timer in minutes
        properties.put("lightOn", "false");
        properties.put("doorOpen", "false");
        properties.put("preheating", "false");
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
                    
                case "temperature":
                case "targetTemperature":
                    try {
                        int temp = Integer.parseInt(value);
                        if (temp < 0 || temp > 300) {
                            throw new IllegalArgumentException("Temperature must be between 0 and 300°C");
                        }
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException("Temperature must be a valid number");
                    }
                    break;
                    
                case "mode":
                    if (!value.equals("OFF") && !value.equals("BAKE") && 
                        !value.equals("BROIL") && !value.equals("CONVECTION")) {
                        throw new IllegalArgumentException("Invalid mode value");
                    }
                    break;
                    
                case "timer":
                    try {
                        int minutes = Integer.parseInt(value);
                        if (minutes < 0 || minutes > 180) {
                            throw new IllegalArgumentException("Timer must be between 0 and 180 minutes");
                        }
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException("Timer must be a valid number");
                    }
                    break;
                    
                case "lightOn":
                case "doorOpen":
                case "preheating":
                    if (!value.equals("true") && !value.equals("false")) {
                        throw new IllegalArgumentException(key + " must be 'true' or 'false'");
                    }
                    break;
                    
                default:
                    throw new IllegalArgumentException("Unknown property: " + key);
            }
        }
    }

    @Override
    protected void onStateUpdated() {
        String power = properties.get("power");
        String temp = properties.get("temperature");
        String mode = properties.get("mode");
        logger.debug("Oven {} state updated: power={}, temperature={}°C, mode={}", 
                getDeviceId(), power, temp, mode);
    }

    @Override
    public Map<String, String> executeCommand(String command, Map<String, String> parameters) {
        Map<String, String> result = new HashMap<>();
        
        switch (command) {
            case "SET_TEMPERATURE":
                if (parameters.containsKey("value")) {
                    String temp = parameters.get("value");
                    Map<String, String> tempUpdate = new HashMap<>();
                    tempUpdate.put("targetTemperature", temp);
                    tempUpdate.put("preheating", "true");
                    if (updateState(tempUpdate)) {
                        result.put("targetTemperature", temp);
                    } else {
                        result.put("error", "Failed to set temperature");
                    }
                } else {
                    result.put("error", "Missing temperature value");
                }
                break;
                
            case "SET_MODE":
                if (parameters.containsKey("value")) {
                    String mode = parameters.get("value");
                    Map<String, String> modeUpdate = new HashMap<>();
                    modeUpdate.put("mode", mode);
                    if (updateState(modeUpdate)) {
                        result.put("mode", mode);
                    } else {
                        result.put("error", "Failed to set mode");
                    }
                } else {
                    result.put("error", "Missing mode value");
                }
                break;
                
            case "SET_TIMER":
                if (parameters.containsKey("value")) {
                    String timer = parameters.get("value");
                    Map<String, String> timerUpdate = new HashMap<>();
                    timerUpdate.put("timer", timer);
                    if (updateState(timerUpdate)) {
                        result.put("timer", timer);
                    } else {
                        result.put("error", "Failed to set timer");
                    }
                } else {
                    result.put("error", "Missing timer value");
                }
                break;
                
            case "TOGGLE_LIGHT":
                String currentLight = properties.get("lightOn");
                String newLight = "true".equals(currentLight) ? "false" : "true";
                Map<String, String> lightUpdate = new HashMap<>();
                lightUpdate.put("lightOn", newLight);
                if (updateState(lightUpdate)) {
                    result.put("lightOn", newLight);
                } else {
                    result.put("error", "Failed to toggle light");
                }
                break;
                
            case "TOGGLE_POWER":
                String currentPower = properties.get("power");
                String newPower = "ON".equals(currentPower) ? "OFF" : "ON";
                Map<String, String> powerUpdate = new HashMap<>();
                powerUpdate.put("power", newPower);
                if ("OFF".equals(newPower)) {
                    powerUpdate.put("mode", "OFF");
                    powerUpdate.put("temperature", "0");
                    powerUpdate.put("targetTemperature", "0");
                    powerUpdate.put("timer", "0");
                    powerUpdate.put("preheating", "false");
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
        
        // Simulate temperature changes
        executor.scheduleAtFixedRate(this::simulateTemperature, 2, 5, TimeUnit.SECONDS);
        
        // Simulate timer countdown
        executor.scheduleAtFixedRate(this::updateTimer, 60, 60, TimeUnit.SECONDS);
        
        logger.info("Oven {} started and sending periodic updates", getDeviceId());
    }

    private void simulateTemperature() {
        if (!isOnline() || properties.get("power").equals("OFF")) {
            return;
        }
        
        try {
            int currentTemp = Integer.parseInt(properties.get("temperature"));
            int targetTemp = Integer.parseInt(properties.get("targetTemperature"));
            
            if (currentTemp != targetTemp) {
                int tempChange;
                if (currentTemp < targetTemp) {
                    // Heating up
                    tempChange = Math.min(10 + random.nextInt(5), targetTemp - currentTemp);
                } else {
                    // Cooling down
                    tempChange = Math.max(-5 - random.nextInt(3), targetTemp - currentTemp);
                }
                
                int newTemp = currentTemp + tempChange;
                
                Map<String, String> update = new HashMap<>();
                update.put("temperature", String.valueOf(newTemp));
                if (newTemp >= targetTemp && properties.get("preheating").equals("true")) {
                    update.put("preheating", "false");
                }
                updateState(update);
            }
        } catch (NumberFormatException e) {
            logger.error("Error parsing temperature values", e);
        }
    }

    private void updateTimer() {
        if (!isOnline() || properties.get("power").equals("OFF")) {
            return;
        }
        
        try {
            int currentTimer = Integer.parseInt(properties.get("timer"));
            if (currentTimer > 0) {
                int newTimer = currentTimer - 1;
                Map<String, String> update = new HashMap<>();
                update.put("timer", String.valueOf(newTimer));
                
                if (newTimer == 0) {
                    // Timer finished, turn off the oven
                    update.put("power", "OFF");
                    update.put("mode", "OFF");
                    update.put("temperature", "0");
                    update.put("targetTemperature", "0");
                    update.put("preheating", "false");
                    logger.info("Oven {} timer finished, turning off", getDeviceId());
                }
                
                updateState(update);
            }
        } catch (NumberFormatException e) {
            logger.error("Error updating timer", e);
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
        
        logger.info("Oven {} stopped", getDeviceId());
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
        
        Oven oven = new Oven(host, port);
        oven.start();
        
        Runtime.getRuntime().addShutdownHook(new Thread(oven::stop));
        
        logger.info("Oven device running. Press Ctrl+C to stop.");
    }
}