package com.smarthome.core;

import java.util.Map;
import java.util.UUID;

/**
 * Base interface for all smart home devices.
 * Defines the common operations that all devices should implement.
 */
public interface Device {
    
    /**
     * Get the unique ID of this device
     * @return Device ID
     */
    String getDeviceId();
    
    /**
     * Get the type of this device (e.g., "LIGHT", "THERMOSTAT")
     * @return Device type
     */
    String getDeviceType();
    
    /**
     * Get the current status of the device including all properties
     * @return Map of property names to their current values
     */
    Map<String, String> getStatus();
    
    /**
     * Update the state of the device with new property values
     * @param properties Map of property names to new values
     * @return true if update was successful, false otherwise
     */
    boolean updateState(Map<String, String> properties);
    
    /**
     * Execute a device-specific command
     * @param command Name of the command to execute
     * @param parameters Parameters for the command
     * @return Map of result values or empty map if no results
     */
    Map<String, String> executeCommand(String command, Map<String, String> parameters);
    
    /**
     * Start the device simulation
     */
    void start();
    
    /**
     * Stop the device simulation
     */
    void stop();
    
    /**
     * Check if the device is currently online/active
     * @return true if device is online, false otherwise
     */
    boolean isOnline();
}