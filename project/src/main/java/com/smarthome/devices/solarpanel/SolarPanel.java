package com.smarthome.devices.solarpanel;

import com.smarthome.core.AbstractDevice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SolarPanel extends AbstractDevice {
    private static final Logger logger = LoggerFactory.getLogger(SolarPanel.class);
    private static final String DEVICE_TYPE = "SOLAR_PANEL";
    
    private ScheduledExecutorService executor;
    private final Random random = new Random();
    
    public SolarPanel(String deviceManagerHost, int deviceManagerPort) {
        super(DEVICE_TYPE, deviceManagerHost, deviceManagerPort);
    }
    
    public SolarPanel(String deviceId, String deviceManagerHost, int deviceManagerPort) {
        super(deviceId, DEVICE_TYPE, deviceManagerHost, deviceManagerPort);
    }

    @Override
    protected void initializeProperties() {
        properties.put("status", "ACTIVE"); // ACTIVE, INACTIVE, MAINTENANCE
        properties.put("currentPowerOutput", "0.0"); // in kW
        properties.put("dailyEnergy", "0.0"); // in kWh
        properties.put("totalEnergy", "0.0"); // in kWh
        properties.put("efficiency", "100"); // percentage
        properties.put("panelAngle", "30"); // degrees
        properties.put("sunIntensity", "0"); // 0-100%
        properties.put("temperature", "25"); // Celsius
        properties.put("cleaningNeeded", "false");
        properties.put("lastMaintenance", String.valueOf(System.currentTimeMillis()));
    }

    @Override
    protected void validateProperties(Map<String, String> newProperties) throws IllegalArgumentException {
        for (Map.Entry<String, String> entry : newProperties.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            
            switch (key) {
                case "status":
                    if (!value.equals("ACTIVE") && !value.equals("INACTIVE") && 
                        !value.equals("MAINTENANCE")) {
                        throw new IllegalArgumentException("Invalid status value");
                    }
                    break;
                    
                case "currentPowerOutput":
                case "dailyEnergy":
                case "totalEnergy":
                    try {
                        double power = Double.parseDouble(value);
                        if (power < 0) {
                            throw new IllegalArgumentException(key + " cannot be negative");
                        }
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException(key + " must be a valid number");
                    }
                    break;
                    
                case "efficiency":
                    try {
                        int efficiency = Integer.parseInt(value);
                        if (efficiency < 0 || efficiency > 100) {
                            throw new IllegalArgumentException("Efficiency must be between 0 and 100%");
                        }
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException("Efficiency must be a valid number");
                    }
                    break;
                    
                case "panelAngle":
                    try {
                        int angle = Integer.parseInt(value);
                        if (angle < 0 || angle > 90) {
                            throw new IllegalArgumentException("Panel angle must be between 0 and 90 degrees");
                        }
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException("Panel angle must be a valid number");
                    }
                    break;
                    
                case "sunIntensity":
                    try {
                        int intensity = Integer.parseInt(value);
                        if (intensity < 0 || intensity > 100) {
                            throw new IllegalArgumentException("Sun intensity must be between 0 and 100%");
                        }
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException("Sun intensity must be a valid number");
                    }
                    break;
                    
                case "temperature":
                    try {
                        int temp = Integer.parseInt(value);
                        if (temp < -20 || temp > 80) {
                            throw new IllegalArgumentException("Temperature must be between -20 and 80°C");
                        }
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException("Temperature must be a valid number");
                    }
                    break;
                    
                case "cleaningNeeded":
                    if (!value.equals("true") && !value.equals("false")) {
                        throw new IllegalArgumentException("Cleaning needed must be 'true' or 'false'");
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
        String status = properties.get("status");
        String power = properties.get("currentPowerOutput");
        String efficiency = properties.get("efficiency");
        logger.debug("Solar Panel {} state updated: status={}, power={}kW, efficiency={}%", 
                getDeviceId(), status, power, efficiency);
    }

    @Override
    public Map<String, String> executeCommand(String command, Map<String, String> parameters) {
        Map<String, String> result = new HashMap<>();
        
        switch (command) {
            case "SET_ANGLE":
                if (parameters.containsKey("value")) {
                    String angle = parameters.get("value");
                    Map<String, String> angleUpdate = new HashMap<>();
                    angleUpdate.put("panelAngle", angle);
                    if (updateState(angleUpdate)) {
                        result.put("panelAngle", angle);
                    } else {
                        result.put("error", "Failed to set panel angle");
                    }
                } else {
                    result.put("error", "Missing angle value");
                }
                break;
                
            case "START_MAINTENANCE":
                if (!properties.get("status").equals("MAINTENANCE")) {
                    Map<String, String> maintenanceUpdate = new HashMap<>();
                    maintenanceUpdate.put("status", "MAINTENANCE");
                    maintenanceUpdate.put("currentPowerOutput", "0.0");
                    if (updateState(maintenanceUpdate)) {
                        result.putAll(maintenanceUpdate);
                    } else {
                        result.put("error", "Failed to start maintenance");
                    }
                } else {
                    result.put("error", "Already in maintenance mode");
                }
                break;
                
            case "END_MAINTENANCE":
                if (properties.get("status").equals("MAINTENANCE")) {
                    Map<String, String> endMaintenanceUpdate = new HashMap<>();
                    endMaintenanceUpdate.put("status", "ACTIVE");
                    endMaintenanceUpdate.put("lastMaintenance", String.valueOf(System.currentTimeMillis()));
                    endMaintenanceUpdate.put("efficiency", "100");
                    endMaintenanceUpdate.put("cleaningNeeded", "false");
                    if (updateState(endMaintenanceUpdate)) {
                        result.putAll(endMaintenanceUpdate);
                    } else {
                        result.put("error", "Failed to end maintenance");
                    }
                } else {
                    result.put("error", "Not in maintenance mode");
                }
                break;
                
            case "GET_ENERGY_STATS":
                result.put("currentPowerOutput", properties.get("currentPowerOutput"));
                result.put("dailyEnergy", properties.get("dailyEnergy"));
                result.put("totalEnergy", properties.get("totalEnergy"));
                result.put("efficiency", properties.get("efficiency"));
                break;
                
            default:
                result.put("error", "Unknown command: " + command);
                break;
        }
        
        return result;
    }

    @Override
    protected void onStart() {
        executor = Executors.newScheduledThreadPool(3);
        
        // Send status updates
        executor.scheduleAtFixedRate(() -> {
            if (isOnline()) {
                getDeviceClient().sendStatusUpdate();
            }
        }, 5, 10, TimeUnit.SECONDS);
        
        // Simulate power generation
        executor.scheduleAtFixedRate(this::updatePowerGeneration, 10, 10, TimeUnit.SECONDS);
        
        // Simulate environmental changes
        executor.scheduleAtFixedRate(this::updateEnvironmentalConditions, 30, 30, TimeUnit.SECONDS);
        
        logger.info("Solar Panel {} started and sending periodic updates", getDeviceId());
    }

    private void updatePowerGeneration() {
        if (!isOnline() || !properties.get("status").equals("ACTIVE")) {
            return;
        }
        
        try {
            int sunIntensity = Integer.parseInt(properties.get("sunIntensity"));
            int efficiency = Integer.parseInt(properties.get("efficiency"));
            int panelAngle = Integer.parseInt(properties.get("panelAngle"));
            
            // Calculate power output based on conditions
            double angleEfficiency = Math.cos(Math.toRadians(Math.abs(panelAngle - 30))); // Optimal angle is 30°
            double powerOutput = (sunIntensity / 100.0) * (efficiency / 100.0) * angleEfficiency * 5.0; // Max 5kW
            powerOutput = Math.round(powerOutput * 100.0) / 100.0; // Round to 2 decimal places
            
            // Update energy production
            double dailyEnergy = Double.parseDouble(properties.get("dailyEnergy"));
            double totalEnergy = Double.parseDouble(properties.get("totalEnergy"));
            
            // Add energy produced in the last interval (10 seconds = 1/360 of an hour)
            double energyProduced = powerOutput / 360.0;
            dailyEnergy += energyProduced;
            totalEnergy += energyProduced;
            
            Map<String, String> update = new HashMap<>();
            update.put("currentPowerOutput", String.format("%.2f", powerOutput));
            update.put("dailyEnergy", String.format("%.2f", dailyEnergy));
            update.put("totalEnergy", String.format("%.2f", totalEnergy));
            
            updateState(update);
            
        } catch (NumberFormatException e) {
            logger.error("Error updating power generation", e);
        }
    }

    private void updateEnvironmentalConditions() {
        if (!isOnline()) {
            return;
        }
        
        try {
            Map<String, String> update = new HashMap<>();
            
            // Update sun intensity based on time of day
            long hour = (System.currentTimeMillis() / 3600000) % 24;
            int baseIntensity;
            if (hour >= 6 && hour <= 18) { // Daytime
                baseIntensity = 60 + random.nextInt(41); // 60-100%
            } else { // Night
                baseIntensity = random.nextInt(11); // 0-10%
            }
            update.put("sunIntensity", String.valueOf(baseIntensity));
            
            // Update temperature
            int currentTemp = Integer.parseInt(properties.get("temperature"));
            int tempChange = random.nextInt(3) - 1; // -1, 0, or 1
            int newTemp = Math.max(-20, Math.min(80, currentTemp + tempChange));
            update.put("temperature", String.valueOf(newTemp));
            
            // Gradually decrease efficiency
            int currentEfficiency = Integer.parseInt(properties.get("efficiency"));
            if (currentEfficiency > 70 && random.nextInt(100) < 5) { // 5% chance to decrease
                update.put("efficiency", String.valueOf(currentEfficiency - 1));
                
                if (currentEfficiency <= 80 && !properties.get("cleaningNeeded").equals("true")) {
                    update.put("cleaningNeeded", "true");
                    logger.warn("Solar Panel {} needs cleaning, efficiency dropped to {}%", 
                            getDeviceId(), currentEfficiency);
                }
            }
            
            updateState(update);
            
        } catch (NumberFormatException e) {
            logger.error("Error updating environmental conditions", e);
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
        
        logger.info("Solar Panel {} stopped", getDeviceId());
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
        
        SolarPanel solarPanel = new SolarPanel(host, port);
        solarPanel.start();
        
        Runtime.getRuntime().addShutdownHook(new Thread(solarPanel::stop));
        
        logger.info("Solar Panel device running. Press Ctrl+C to stop.");
    }
}