# Smart Home Device Simulation

This project implements a Java-based simulation of smart home devices using gRPC as middleware for communication. The system demonstrates a distributed architecture where multiple devices operate independently and communicate through a central device manager.

## System Architecture

The system consists of:

1. **Device Manager** - Central hub that handles communication with all devices
2. **Device Interface** - Common interface implemented by all smart devices
3. **Device Implementations** - Specific implementations for different device types
4. **gRPC Communication Layer** - Middleware for client-server communication

## Device Types

The project implements various smart home devices:

- Lights
- Thermostats
- (With extension points for other devices like Coils, Blinds, Motion Sensors, etc.)

## Running the Simulation

To run the complete simulation with multiple devices:

```bash
mvn clean package
java -cp target/smart-home-simulation-1.0.0.jar com.smarthome.SimulationMain
```

To run just the device manager:

```bash
java -cp target/smart-home-simulation-1.0.0.jar com.smarthome.DeviceManagerMain [PORT]
```

To run individual devices (which will connect to a running device manager):

```bash
# Run a light device
java -cp target/smart-home-simulation-1.0.0.jar com.smarthome.devices.light.Light [HOST] [PORT]

# Run a thermostat device
java -cp target/smart-home-simulation-1.0.0.jar com.smarthome.devices.thermostat.Thermostat [HOST] [PORT]
```

## Adding New Device Types

To add a new device type:

1. Create a new package in `com.smarthome.devices`
2. Create a new class that extends `AbstractDevice`
3. Implement the required methods including property initialization and validation

## Design Decisions

- **Common Interface** - All devices implement the `Device` interface, allowing for polymorphic handling
- **Modular Structure** - Each device type is in its own package with its own implementation
- **gRPC Communication** - Using protocol buffers for efficient serialization/deserialization
- **Distributed Execution** - Each device runs in its own thread/process