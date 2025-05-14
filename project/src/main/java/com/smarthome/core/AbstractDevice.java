package com.smarthome.core;

import com.smarthome.communication.DeviceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Abstract base class implementing common functionality for all devices.
 */
public abstract class AbstractDevice implements Device {
    private static final Logger logger = LoggerFactory.getLogger(AbstractDevice.class);
    
    private final String deviceId;
    private final String deviceType;
    private final AtomicBoolean online = new AtomicBoolean(false);
    private final DeviceClient deviceClient;
    protected final Map<String, String> properties = new HashMap<>();
    
    /**
     * Create a new device with a random UUID
     * @param deviceType The type of this device
     * @param deviceManagerHost Host where the device manager is running
     * @param deviceManagerPort Port where the device manager is listening
     */
    public AbstractDevice(String deviceType, String deviceManagerHost, int deviceManagerPort) {
        this(UUID.randomUUID().toString(), deviceType, deviceManagerHost, deviceManagerPort);
    }
    
    /**
     * Create a new device with the specified ID
     * @param deviceId The unique ID for this device
     * @param deviceType The type of this device
     * @param deviceManagerHost Host where the device manager is running
     * @param deviceManagerPort Port where the device manager is listening
     */
    public AbstractDevice(String deviceId, String deviceType, String deviceManagerHost, int deviceManagerPort) {
        this.deviceId = deviceId;
        this.deviceType = deviceType;
        this.deviceClient = new DeviceClient(deviceManagerHost, deviceManagerPort, this);
        
        // Initialize with default properties
        initializeProperties();
    }
    
    /**
     * Initialize default properties for this device type
     */
    protected abstract void initializeProperties();

    @Override
    public String getDeviceId() {
        return deviceId;
    }

    @Override
    public String getDeviceType() {
        return deviceType;
    }

    @Override
    public Map<String, String> getStatus() {
        return new HashMap<>(properties); // Return a copy to prevent external modification
    }

    @Override
    public boolean updateState(Map<String, String> newProperties) {
        if (newProperties == null || !isOnline()) {
            return false;
        }
        
        try {
            // Check if the new properties are valid before applying them
            validateProperties(newProperties);
            
            // Apply valid property updates
            for (Map.Entry<String, String> entry : newProperties.entrySet()) {
                properties.put(entry.getKey(), entry.getValue());
            }
            
            // Perform any device-specific actions based on the updated state
            onStateUpdated();
            
            logger.info("Device {} state updated: {}", deviceId, newProperties);
            return true;
        } catch (IllegalArgumentException e) {
            logger.error("Failed to update device {} state: {}", deviceId, e.getMessage());
            return false;
        }
    }

    /**
     * Validate the provided properties before applying them to the device
     * @param newProperties Properties to validate
     * @throws IllegalArgumentException if properties are invalid
     */
    protected abstract void validateProperties(Map<String, String> newProperties) throws IllegalArgumentException;
    
    /**
     * Called after the device state has been updated
     * Override to perform device-specific actions on state change
     */
    protected void onStateUpdated() {
        // Default implementation does nothing
    }

    @Override
    public void start() {
        if (online.compareAndSet(false, true)) {
            logger.info("Starting device: {} ({})", deviceId, deviceType);
            
            // Register with the device manager
            boolean registered = deviceClient.register();
            if (!registered) {
                logger.error("Failed to register device {} with device manager", deviceId);
                online.set(false);
                return;
            }
            
            // Call device-specific startup logic
            onStart();
            
            logger.info("Device started: {} ({})", deviceId, deviceType);
        }
    }

    /**
     * Called when the device is started
     * Override to perform device-specific startup actions
     */
    protected void onStart() {
        // Default implementation does nothing
    }

    @Override
    public void stop() {
        if (online.compareAndSet(true, false)) {
            logger.info("Stopping device: {} ({})", deviceId, deviceType);
            
            // Call device-specific shutdown logic
            onStop();
            
            // Disconnect from the device manager
            deviceClient.disconnect();
            
            logger.info("Device stopped: {} ({})", deviceId, deviceType);
        }
    }

    /**
     * Called when the device is stopped
     * Override to perform device-specific shutdown actions
     */
    protected void onStop() {
        // Default implementation does nothing
    }

    @Override
    public boolean isOnline() {
        return online.get();
    }

    /**
     * Get the client used to communicate with the device manager
     * @return The device client
     */
    protected DeviceClient getDeviceClient() {
        return deviceClient;
    }
}