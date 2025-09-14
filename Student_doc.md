# SYSTEM DESCRIPTION:

The system is a **Smart Home Management Platform** that enables users to remotely monitor, control, and configure smart devices in their homes.  
It integrates different types of devices (lights, ovens, washing machines, motion sensors, thermostats, blinds, solar panels, air conditioners), supports grouping by rooms and device groups, provides notifications when alarms are triggered, and maintains a history of device events.  
Communication with devices is handled via **gRPC**, while the backend exposes **REST APIs**. Data persistence is achieved with **PostgreSQL**, and push notifications are sent through **Expo API**.

---

# USER STORIES:

- **US1**: Manage all home devices through the app (monitor and control remotely).  
- **US2**: Connect a database to the system for storing and querying relevant data.  
- **US3**: Group devices by room for structured management.  
- **US4**: Provide a user interface to interact with all functionalities.  
- **US5**: Receive notifications when a device is active while the user is away.  
- **US6**: View the history of actions performed in the app.  
- **US7**: Configure and start device programs.  
- **US8**: Set specific values (e.g., temperature) for configurable devices.  
- **US9**: Enable alarm mode with motion detection.  
- **US10**: Create and manage device groups for collective control.  

---

# CONTAINERS:

---

## CONTAINER_NAME: Device Manager API

### DESCRIPTION:
Backend container that exposes **REST APIs** for device management, grouping, logging, alarm handling, and notifications. It is the core entry point for the system’s functionalities.

### USER STORIES:
- US1, US3, US5, US6, US7, US8, US9, US10

### PORTS:
- `8080` (HTTP REST API)

### PERSISTENCE EVALUATION:
This container communicates with the **PostgreSQL database** to store:
- Rooms, Groups, Devices
- Device Aliases
- Logs of device actions
- Alarm and notification tokens

### EXTERNAL SERVICES CONNECTIONS:
- gRPC stubs for all device types (Lights, Washing Machine, Oven, Dishwasher, Thermostat, Air Conditioner, Solar Panel, Motion Sensor, Blind).  
- Expo Push Notification API (`https://exp.host/--/api/v2/push/send`).

### MICROSERVICES:

#### MICROSERVICE: DeviceApiController
- TYPE: backend  
- DESCRIPTION: Provides endpoints to manage devices, rooms, groups, and logs.  
- PORTS: 8080  
- TECHNOLOGICAL SPECIFICATION: Java 17, Spring Boot, REST controllers.  
- SERVICE ARCHITECTURE: Layered architecture with controller → service → repository.  

- ENDPOINTS:  

	| HTTP METHOD | URL | Description | User Stories |
	| ----------- | --- | ----------- | ------------ |
	| POST | /api/devices/register | Register a new device | US1 |
	| GET | /api/devices/status/{id} | Get device status | US1 |
	| POST | /api/devices/turnOnDevice/{id} | Turn on device | US1 |
	| POST | /api/devices/turnOffDevice/{id} | Turn off device | US1 |
	| PATCH | /api/devices/rooms/{roomId}/rename | Rename a room | US3 |
	| GET | /api/devices/rooms | List all rooms | US3 |
	| POST | /api/devices/groups | Create a new group | US10 |
	| GET | /api/devices/groups | List all groups | US10 |
	| GET | /api/devices/logs/{id} | View logs for a device | US6 |

---

#### MICROSERVICE: AlarmApiController
- TYPE: backend  
- DESCRIPTION: Handles alarm activation, manual trigger, status retrieval, and token registration for notifications.  
- PORTS: 8080  
- TECHNOLOGICAL SPECIFICATION: Java 17, Spring Boot, REST controllers.  
- SERVICE ARCHITECTURE: Controller that delegates to `DeviceManagerServiceImpl`.  

- ENDPOINTS:  

	| HTTP METHOD | URL | Description | User Stories |
	| ----------- | --- | ----------- | ------------ |
	| POST | /api/alarm/activate?enable={bool} | Activate/deactivate alarm | US9 |
	| GET | /api/alarm/status | Get alarm status | US9 |
	| POST | /api/alarm/register-token | Register push token | US5 |
	| POST | /api/alarm/trigger | Manually trigger alarm | US9 |

---

## CONTAINER_NAME: PostgreSQL Database

### DESCRIPTION:
Persistence container for storing system data.  

### USER STORIES:
- US2, US3, US6, US10

### PORTS:
- `5432`

### PERSISTENCE EVALUATION:
Stores structured information regarding devices, groups, rooms, aliases, and logs.  

### EXTERNAL SERVICES CONNECTIONS:
- Accessed only by Device Manager API.  

### MICROSERVICES:

#### MICROSERVICE: PostgreSQL DB
- TYPE: database  
- DESCRIPTION: Stores entities and relationships of the smart home.  
- PORTS: 5432  
- TECHNOLOGICAL SPECIFICATION: PostgreSQL 14+  
- SERVICE ARCHITECTURE: Relational DB with tables for devices, rooms, groups, aliases, and logs.  

- DB STRUCTURE:  

	**_rooms_**: | room_id |  
	**_groups_**: | group_id |  
	**_room_devices_**: | room_id | device_id |  
	**_group_devices_**: | group_id | device_id |  
	**_device_aliases_**: | device_id | display_name |  
	**_device_events_**: | event_id | device_id | action | status | user | payload_json | error_msg | timestamp |

---

## CONTAINER_NAME: gRPC Device Simulators

### DESCRIPTION:
Simulated device containers providing gRPC services (e.g., LightService, OvenService, MotionSensorService).  

### USER STORIES:
- US1, US7, US8, US9

### PORTS:
- Range `50051+` (per device type, e.g., 50051 Light, 50052 WashingMachine, etc.)  

### PERSISTENCE EVALUATION:
No persistence; they respond to gRPC calls with simulated behavior.  

### EXTERNAL SERVICES CONNECTIONS:
Connected only to the Device Manager API.  

### MICROSERVICES:

#### MICROSERVICE: LightServiceGrpc (example)
- TYPE: backend  
- DESCRIPTION: Simulates smart light device with gRPC endpoints for turnOn, turnOff, getStatus.  
- PORTS: 50051  
- TECHNOLOGICAL SPECIFICATION: gRPC with Protobuf definitions.  
- SERVICE ARCHITECTURE: Standalone microservice exposing blocking stubs.  

- ENDPOINTS: gRPC methods (not HTTP):  
  - `turnOn(TurnOnRequest)`  
  - `turnOff(TurnOffRequest)`  
  - `getStatus(GetStatusRequest)`  

(Similar structure applies for OvenService, DishwasherService, MotionSensorService, etc.)

---

## CONTAINER_NAME: Frontend (Future UI)

### DESCRIPTION:
A React-based web/mobile app frontend (planned, not yet deployed) to allow users to interact with the system easily.  

### USER STORIES:
- US1, US3, US4, US5, US6, US7, US8, US9, US10

### PORTS:
- `3000`

### PERSISTENCE EVALUATION:
No direct persistence; it consumes REST APIs from the backend.  

### EXTERNAL SERVICES CONNECTIONS:
- Calls Device Manager API via REST.  
- Receives push notifications from Expo.  

### MICROSERVICES:

#### MICROSERVICE: React Frontend
- TYPE: frontend  
- DESCRIPTION: Provides the user interface for controlling devices, viewing logs, and managing alarms.  
- PORTS: 3000  
- TECHNOLOGICAL SPECIFICATION: React + Tailwind CSS + REST API integration.  
- SERVICE ARCHITECTURE: Single-page app interacting with backend APIs.  

- PAGES:  

	| Name | Description | Related Microservice | User Stories |
	| ---- | ----------- | -------------------- | ------------ |
	| Dashboard | Device overview and quick actions | DeviceApiController | US1 |
	| Rooms & Groups | Structured device grouping and control | DeviceApiController | US3, US10 |
	| Logs | Display historical logs | DeviceApiController | US6 |
	| Alarm | Enable/disable alarm, view status | AlarmApiController | US5, US9 |
