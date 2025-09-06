# Smart Home gRPC Microservices

A comprehensive multi-module Maven project implementing a gRPC-based smart home system with microservices architecture.

## Project Structure

```
smart-home-root/
├── pom.xml                    # Root Maven configuration
├── docker-compose.yml         # Docker Compose configuration
├── common/                    # Shared protobuf definitions
│   ├── pom.xml
│   └── src/main/proto/        # Protocol buffer definitions
├── devicemanager/             # Device manager service
│   ├── pom.xml
│   ├── src/main/java/
│   └── Dockerfile
└── devices/                   # Device microservices
    ├── light/
    ├── oven/
    ├── thermostat/
    ├── blind/
    ├── motionsensor/
    ├── solarpanel/
    ├── airconditioner/
    ├── dishwasher/
    └── washingmachine/
```

## Services

### Device Manager (Port 50051)
- Central coordination service
- Device registration and management
- Command routing to individual devices

### Device Services
- **Light** (Port 50052): Smart lighting control with brightness and color
- **Oven** (Port 50053): Oven temperature and timer control
- **Thermostat** (Port 50054): HVAC temperature and mode control
- **Blind** (Port 50055): Motorized blind position control
- **Motion Sensor** (Port 50056): Motion detection and sensitivity settings
- **Solar Panel** (Port 50057): Energy production monitoring
- **airconditioner** (Port 50058): Heating/cooling airconditioner power control
- **Dishwasher** (Port 50059): Dishwasher program and cycle control
- **Washing Machine** (Port 50060): Washing machine program control

## Build and Run

### Prerequisites
- Java 17+
- Maven 3.6+
- Docker and Docker Compose

### Build the project
```bash
# Generate protobuf classes
mvn clean compile -pl common

# Build all modules
mvn clean package
```

### Run with Docker Compose
```bash
docker-compose up --build
```

This will start all microservices in containers with proper networking.

## gRPC Services

All device services implement at least:
- `TurnOn` - Power on the device
- `TurnOff` - Power off the device
- `GetStatus` - Retrieve current device status
- Additional device-specific methods

The Device Manager provides:
- `RegisterDevice` - Register a new device
- `UnregisterDevice` - Remove a device
- `ListDevices` - Get all registered devices
- `SendCommand` - Forward commands to devices

## Development

This is a scaffolding project providing:
- Complete Maven multi-module structure
- Protocol buffer definitions for all device types
- Basic gRPC service implementations with stub responses
- Multi-stage Docker builds for efficient containerization
- Docker Compose orchestration with proper networking

Each service includes basic implementations that can be extended with actual device integration logic.

## Container Communication

Services communicate using container names as hostnames:
- Device Manager can reach devices at `light:50052`, `oven:50053`, etc.
- All services are connected via the `smart-home-network` bridge network
- External access is available through mapped ports on localhost