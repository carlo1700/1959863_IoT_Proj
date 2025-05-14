package com.smarthome.devices.motionsensor;

import com.smarthome.core.AbstractDevice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MotionSensor extends AbstractDevice {
    private static final Logger logger = LoggerFactory.getLogger(MotionSensor.class);
    private static final String DEVICE_TYPE = "MOTION_SENSOR";
    
    private ScheduledExecutorService executor;
    private final Random random = new Random();
    
    public MotionSensor(String deviceManagerHost, int deviceManagerPort) {
        super(DEVICE_TYPE, deviceManagerHost, deviceManagerPort);
    }
    
    public MotionSensor(String deviceId, String deviceManagerHost, int deviceManagerPort) {
        super(deviceId, DEVICE_TYPE, deviceManagerHost, deviceManagerPort);
    }

    @Override
    protected void initializeProperties() {
        properties.put("motionDetected", "false");
        properties.put("sensitivity", "50"); // 1-100
        properties.put("lastDetection", "0"); // timestamp
        properties.put("batteryLevel", "100"); // 0-100%
    }

    @Override
    protected void validateProperties(Map<String, String> newProperties) throws IllegalArgumentException {
        for (Map.Entry<String, String> entry : newProperties.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            
            switch (key) {
                case "motionDetected":
                    if (!value.equals("true") && !value.equals("false")) {
                        throw new IllegalArgumentException("Motion detected must be 'true' or 'false'");
                    }
                    break;
                    
                case "sensitivity":
                    try {
                        int sensitivity = Integer.parseInt(value);
                        if (sensitivity < 1 || sensitivity > 100) {
                            throw new IllegalArgumentException("Sensitivity must be between 1 and 100");
                        }
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException("Sensitivity must be a number between 1 and 100");
                    }
                    break;
                    
                case "lastDetection":
                    try {
                        Long.parseLong(value);
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException("Last detection must be a valid timestamp");
                    }
                    break;
                    
                case "batteryLevel":
                    try {
                        int battery = Integer.parseInt(value);
                        if (battery < 0 || battery > 100) {
                            throw new IllegalArgumentException("Battery level must be between 0 and 100");
                        }
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException("Battery level must be a number between 0 and 100");
                    }
                    break;
                    
                default:
                    throw new IllegalArgumentException("Unknown property: " + key);
            }
        }
    }

    @Override
    protected void onStateUpdated() {
        String motionState = properties.get("motionDetected");
        String batteryLevel = properties.get("batteryLevel");
        logger.debug("Motion Sensor {} state updated: motion={}, battery={}%", 
                getDeviceId(), motionState, batteryLevel);
    }

    @Override
    public Map<String, String> executeCommand(String command, Map<String, String> parameters) {
        Map<String, String> result = new HashMap<>();
        
        switch (command) {
            case "SET_SENSITIVITY":
                if (parameters.containsKey("value")) {
                    String sensitivity = parameters.get("value");
                    Map<String, String> sensitivityUpdate = new HashMap<>();
                    sensitivityUpdate.put("sensitivity", sensitivity);
                    if (updateState(sensitivityUpdate)) {
                        result.put("sensitivity", sensitivity);
                    } else {
                        result.put("error", "Failed to set sensitivity");
                    }
                } else {
                    result.put("error", "Missing sensitivity value");
                }
                break;
                
            case "CHECK_BATTERY":
                result.put("batteryLevel", properties.get("batteryLevel"));
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
        
        // Simulate motion detection
        executor.scheduleAtFixedRate(this::simulateMotion, 2, 5, TimeUnit.SECONDS);
        
        // Simulate battery drain
        executor.scheduleAtFixedRate(this::simulateBatteryDrain, 30, 60, TimeUnit.SECONDS);
        
        logger.info("Motion Sensor {} started and sending periodic updates", getDeviceId());
    }

    private void simulateMotion() {
        if (!isOnline()) {
            return;
        }
        
        int sensitivity = Integer.parseInt(properties.get("sensitivity"));
        boolean motionDetected = random.nextInt(100) < sensitivity;
        
        Map<String, String> update = new HashMap<>();
        update.put("motionDetected", String.valueOf(motionDetected));
        
        if (motionDetected) {
            update.put("lastDetection", String.valueOf(System.currentTimeMillis()));
        }
        
        updateState(update);
    }

    private void simulateBatteryDrain() {
        if (!isOnline()) {
            return;
        }
        
        int currentBattery = Integer.parseInt(properties.get("batteryLevel"));
        if (currentBattery > 0) {
            int newBattery = currentBattery - 1;
            
            Map<String, String> update = new HashMap<>();
            update.put("batteryLevel", String.valueOf(newBattery));
            updateState(update);
            
            if (newBattery < 20) {
                logger.warn("Motion Sensor {} battery low: {}%", getDeviceId(), newBattery);
            }
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
        
        logger.info("Motion Sensor {} stopped", getDeviceId());
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
        
        MotionSensor sensor = new MotionSensor(host, port);
        sensor.start();
        
        Runtime.getRuntime().addShutdownHook(new Thread(sensor::stop));
        
        logger.info("Motion Sensor device running. Press Ctrl+C to stop.");
    }
}