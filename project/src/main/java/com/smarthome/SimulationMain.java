package com.smarthome;

import com.smarthome.communication.DeviceManagerServer;
import com.smarthome.devices.light.Light;
import com.smarthome.devices.thermostat.Thermostat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Main class for running the complete simulation with device manager and multiple devices.
 */
public class SimulationMain {
    private static final Logger logger = LoggerFactory.getLogger(SimulationMain.class);
    
    public static void main(String[] args) {
        try {
            // Configuration
            int managerPort = 9000;
            String managerHost = "localhost";
            
            // Start the device manager
            logger.info("Starting Device Manager on port {}", managerPort);
            DeviceManagerServer deviceManager = new DeviceManagerServer(managerPort);
            deviceManager.start();
            
            // Wait a bit for the server to start
            Thread.sleep(1000);
            
            // Create and start devices
            List<Thread> deviceThreads = new ArrayList<>();
            CountDownLatch shutdownLatch = new CountDownLatch(1);
            
            // Create a light device thread
            Thread lightThread = new Thread(() -> {
                try {
                    Light light = new Light(managerHost, managerPort);
                    light.start();
                    
                    // Keep the device running until shutdown
                    shutdownLatch.await();
                    
                    light.stop();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } catch (Exception e) {
                    logger.error("Error in light device thread", e);
                }
            });
            lightThread.setName("Light-Device");
            deviceThreads.add(lightThread);
            
            // Create a thermostat device thread
            Thread thermostatThread = new Thread(() -> {
                try {
                    Thermostat thermostat = new Thermostat(managerHost, managerPort);
                    thermostat.start();
                    
                    // Keep the device running until shutdown
                    shutdownLatch.await();
                    
                    thermostat.stop();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } catch (Exception e) {
                    logger.error("Error in thermostat device thread", e);
                }
            });
            thermostatThread.setName("Thermostat-Device");
            deviceThreads.add(thermostatThread);
            
            // Start all device threads
            logger.info("Starting {} device threads", deviceThreads.size());
            for (Thread thread : deviceThreads) {
                thread.start();
            }
            
            // Add shutdown hook to properly terminate all threads
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                logger.info("Shutting down simulation...");
                
                // Signal all device threads to stop
                shutdownLatch.countDown();
                
                // Wait for all device threads to finish
                for (Thread thread : deviceThreads) {
                    try {
                        thread.join(5000); // Wait up to 5 seconds
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                
                // Stop the device manager
                deviceManager.stop();
                
                logger.info("Simulation shutdown complete");
            }));
            
            logger.info("Simulation running with {} devices. Press Ctrl+C to stop.", deviceThreads.size());
            
            // Keep the main thread alive
            deviceManager.blockUntilShutdown();
            
        } catch (Exception e) {
            logger.error("Error running simulation", e);
            System.exit(1);
        }
    }
}