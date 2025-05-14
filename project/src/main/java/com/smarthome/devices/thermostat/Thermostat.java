package com.smarthome.devices.thermostat;

import com.smarthome.core.AbstractDevice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Implementation of a smart thermostat device.
 */
public class Thermostat extends AbstractDevice {
    private static final Logger logger = LoggerFactory.getLogger(Thermostat.class);
    private static final String DEVICE_TYPE = "THERMOSTAT";
    
    private ScheduledExecutorService executor;
    private final Random random = new Random();
    
    public Thermostat(String deviceManagerHost, int deviceManagerPort) {
        super(DEVICE_TYPE, deviceManagerHost, deviceManagerPort);
    }
    
    public Thermostat(String deviceId, String deviceManagerHost, int deviceManagerPort) {
        super(deviceId, DEVICE_TYPE, deviceManagerHost, deviceManagerPort);
    }

    @Override
    protected void initializeProperties() {
        properties.put("power", "OFF");
        properties.put("currentTemperature", "21.0");
        properties.put("targetTemperature", "21.0");
        properties.put("mode", "HEAT"); // HEAT, COOL, AUTO
        properties.put("humidity", "45");
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
                    
                case "currentTemperature":
                    try {
                        double temp = Double.parseDouble(value);
                        if (temp < -30.0 || temp > 50.0) {
                            throw new IllegalArgumentException("Current temperature must be between -30 and 50");
                        }
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException("Current temperature must be a valid number");
                    }
                    break;
                    
                case "targetTemperature":
                    try {
                        double temp = Double.parseDouble(value);
                        if (temp < 5.0 || temp > 35.0) {
                            throw new IllegalArgumentException("Target temperature must be between 5 and 35");
                        }
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException("Target temperature must be a valid number");
                    }
                    break;
                    
                case "mode":
                    if (!value.equals("HEAT") && !value.equals("COOL") && !value.equals("AUTO")) {
                        throw new IllegalArgumentException("Mode must be 'HEAT', 'COOL', or 'AUTO'");
                    }
                    break;
                    
                case "humidity":
                    try {
                        int humidity = Integer.parseInt(value);
                        if (humidity < 0 || humidity > 100) {
                            throw new IllegalArgumentException("Humidity must be between 0 and 100");
                        }
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException("Humidity must be a valid number between 0 and 100");
                    }
                    break;
                    
                default:
                    throw new IllegalArgumentException("Unknown property: " + key);
            }
        }
    }

    @Override
    protected void onStateUpdated() {
        // Simulate physical changes in the thermostat
        String power = properties.get("power");
        String mode = properties.get("mode");
        String targetTemp = properties.get("targetTemperature");
        
        logger.debug("Thermostat {} state updated: power={}, mode={}, target={}°C", 
                getDeviceId(), power, mode, targetTemp);
    }

    @Override
    public Map<String, String> executeCommand(String command, Map<String, String> parameters) {
        Map<String, String> result = new HashMap<>();
        
        switch (command) {
            case "SET_TEMPERATURE":
                if (parameters.containsKey("value")) {
                    String temperature = parameters.get("value");
                    Map<String, String> tempUpdate = new HashMap<>();
                    tempUpdate.put("targetTemperature", temperature);
                    
                    if (updateState(tempUpdate)) {
                        result.put("targetTemperature", temperature);
                    } else {
                        result.put("error", "Failed to update temperature");
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
                        result.put("error", "Failed to update mode");
                    }
                } else {
                    result.put("error", "Missing mode value");
                }
                break;
                
            case "TOGGLE_POWER":
                String currentPower = properties.get("power");
                String newPower = "ON".equals(currentPower) ? "OFF" : "ON";
                
                Map<String, String> powerUpdate = new HashMap<>();
                powerUpdate.put("power", newPower);
                updateState(powerUpdate);
                
                result.put("power", newPower);
                break;
                
            default:
                result.put("error", "Unknown command: " + command);
                break;
        }
        
        return result;
    }

    @Override
    protected void onStart() {
        // Initialize the scheduled executor for regular status updates and temperature simulation
        executor = Executors.newScheduledThreadPool(2);
        
        // Send status updates to the device manager periodically
        executor.scheduleAtFixedRate(() -> {
            if (isOnline()) {
                getDeviceClient().sendStatusUpdate();
            }
        }, 5, 10, TimeUnit.SECONDS);
        
        // Simulate temperature changes
        executor.scheduleAtFixedRate(this::simulateTemperatureChanges, 10, 20, TimeUnit.SECONDS);
        
        logger.info("Thermostat {} started and sending periodic updates", getDeviceId());
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
        
        logger.info("Thermostat {} stopped", getDeviceId());
    }
    
    /**
     * Simulate temperature changes based on the current state
     */
    private void simulateTemperatureChanges() {
        if (!isOnline() || properties.get("power").equals("OFF")) {
            return;
        }
        
        try {
            double currentTemp = Double.parseDouble(properties.get("currentTemperature"));
            double targetTemp = Double.parseDouble(properties.get("targetTemperature"));
            String mode = properties.get("mode");
            
            // Simulate temperature changes based on mode and target
            double tempChange = 0.0;
            
            if (mode.equals("HEAT") && currentTemp < targetTemp) {
                // Heating up
                tempChange = 0.2 + (random.nextDouble() * 0.3);
            } else if (mode.equals("COOL") && currentTemp > targetTemp) {
                // Cooling down
                tempChange = -0.2 - (random.nextDouble() * 0.3);
            } else if (mode.equals("AUTO")) {
                // Automatically adjust toward target
                if (Math.abs(currentTemp - targetTemp) > 0.5) {
                    tempChange = currentTemp < targetTemp ? 0.2 + (random.nextDouble() * 0.2) : -0.2 - (random.nextDouble() * 0.2);
                }
            } else {
                // Natural drift
                tempChange = (random.nextDouble() * 0.2) - 0.1;
            }
            
            // Apply the temperature change
            if (tempChange != 0.0) {
                double newTemp = Math.round((currentTemp + tempChange) * 10.0) / 10.0;
                
                Map<String, String> update = new HashMap<>();
                update.put("currentTemperature", String.valueOf(newTemp));
                updateState(update);
                
                logger.debug("Thermostat {} temperature changed: {} -> {}", 
                        getDeviceId(), currentTemp, newTemp);
            }
            
            // Occasionally update humidity
            if (random.nextInt(5) == 0) {
                int humidity = Integer.parseInt(properties.get("humidity"));
                int humidityChange = random.nextInt(3) - 1; // -1, 0, or 1
                
                if (humidityChange != 0) {
                    int newHumidity = Math.max(0, Math.min(100, humidity + humidityChange));
                    
                    Map<String, String> update = new HashMap<>();
                    update.put("humidity", String.valueOf(newHumidity));
                    updateState(update);
                }
            }
            
        } catch (NumberFormatException e) {
            logger.error("Error parsing temperature values", e);
        }
    }
    
    /**
     * Main method to run a thermostat device as a standalone process
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
        
        // Create and start the thermostat device
        Thermostat thermostat = new Thermostat(host, port);
        thermostat.start();
        
        // Add shutdown hook
        Runtime.getRuntime().addShutdownHook(new Thread(thermostat::stop));
        
        logger.info("Thermostat device running. Press Ctrl+C to stop.");
    }
}