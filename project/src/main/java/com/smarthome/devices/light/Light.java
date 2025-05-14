package com.smarthome.devices.light;

import com.smarthome.core.AbstractDevice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Implementation of a smart light device.
 */
public class Light extends AbstractDevice {
    private static final Logger logger = LoggerFactory.getLogger(Light.class);
    private static final String DEVICE_TYPE = "LIGHT";
    
    private ScheduledExecutorService executor;
    
    public Light(String deviceManagerHost, int deviceManagerPort) {
        super(DEVICE_TYPE, deviceManagerHost, deviceManagerPort);
    }
    
    public Light(String deviceId, String deviceManagerHost, int deviceManagerPort) {
        super(deviceId, DEVICE_TYPE, deviceManagerHost, deviceManagerPort);
    }

    @Override
    protected void initializeProperties() {
        properties.put("power", "OFF");
        properties.put("brightness", "0");
        properties.put("color", "white");
        properties.put("temperature", "4000"); // In Kelvin
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
                    
                case "brightness":
                    try {
                        int brightness = Integer.parseInt(value);
                        if (brightness < 0 || brightness > 100) {
                            throw new IllegalArgumentException("Brightness must be between 0 and 100");
                        }
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException("Brightness must be a number between 0 and 100");
                    }
                    break;
                    
                case "color":
                    // Simple validation, in a real implementation this would be more complex
                    if (value.isEmpty()) {
                        throw new IllegalArgumentException("Color cannot be empty");
                    }
                    break;
                    
                case "temperature":
                    try {
                        int temperature = Integer.parseInt(value);
                        if (temperature < 2000 || temperature > 6500) {
                            throw new IllegalArgumentException("Temperature must be between 2000K and 6500K");
                        }
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException("Temperature must be a number between 2000 and 6500");
                    }
                    break;
                    
                default:
                    throw new IllegalArgumentException("Unknown property: " + key);
            }
        }
    }

    @Override
    protected void onStateUpdated() {
        // Simulate physical changes in the light
        String power = properties.get("power");
        String brightness = properties.get("brightness");
        
        logger.debug("Light {} state updated: power={}, brightness={}%", 
                getDeviceId(), power, brightness);
    }

    @Override
    public Map<String, String> executeCommand(String command, Map<String, String> parameters) {
        Map<String, String> result = new HashMap<>();
        
        switch (command) {
            case "TOGGLE":
                // Toggle the power state
                String currentPower = properties.get("power");
                String newPower = "ON".equals(currentPower) ? "OFF" : "ON";
                
                Map<String, String> newState = new HashMap<>();
                newState.put("power", newPower);
                updateState(newState);
                
                result.put("power", newPower);
                break;
                
            case "SET_BRIGHTNESS":
                if (parameters.containsKey("value")) {
                    String brightness = parameters.get("value");
                    Map<String, String> brightnessUpdate = new HashMap<>();
                    brightnessUpdate.put("brightness", brightness);
                    
                    if (updateState(brightnessUpdate)) {
                        result.put("brightness", brightness);
                    } else {
                        result.put("error", "Failed to update brightness");
                    }
                } else {
                    result.put("error", "Missing brightness value");
                }
                break;
                
            case "SET_COLOR":
                if (parameters.containsKey("value")) {
                    String color = parameters.get("value");
                    Map<String, String> colorUpdate = new HashMap<>();
                    colorUpdate.put("color", color);
                    
                    if (updateState(colorUpdate)) {
                        result.put("color", color);
                    } else {
                        result.put("error", "Failed to update color");
                    }
                } else {
                    result.put("error", "Missing color value");
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
        // Initialize the scheduled executor for regular status updates
        executor = Executors.newSingleThreadScheduledExecutor();
        
        // Send status updates to the device manager periodically
        executor.scheduleAtFixedRate(() -> {
            if (isOnline()) {
                getDeviceClient().sendStatusUpdate();
            }
        }, 5, 15, TimeUnit.SECONDS);
        
        logger.info("Light {} started and sending periodic updates", getDeviceId());
    }

    @Override
    protected void onStop() {
        // Shutdown the executor
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
        
        logger.info("Light {} stopped", getDeviceId());
    }
    
    /**
     * Main method to run a light device as a standalone process
     */
    public static void main(String[] args) {
        // Parse command line arguments for device manager host/port
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
        
        // Create and start the light device
        Light light = new Light(host, port);
        light.start();
        
        // Add shutdown hook
        Runtime.getRuntime().addShutdownHook(new Thread(light::stop));
        
        logger.info("Light device running. Press Ctrl+C to stop.");
    }
}