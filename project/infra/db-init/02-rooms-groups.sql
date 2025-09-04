-- Rooms
CREATE TABLE IF NOT EXISTS rooms (
  room_id TEXT PRIMARY KEY
);

CREATE TABLE IF NOT EXISTS room_devices (
  room_id   TEXT NOT NULL REFERENCES rooms(room_id) ON DELETE CASCADE,
  device_id TEXT NOT NULL,
  PRIMARY KEY (room_id, device_id)
);

-- Groups
CREATE TABLE IF NOT EXISTS device_groups (
  group_id TEXT PRIMARY KEY
);

CREATE TABLE IF NOT EXISTS group_devices (
  group_id  TEXT NOT NULL REFERENCES device_groups(group_id) ON DELETE CASCADE,
  device_id TEXT NOT NULL,
  PRIMARY KEY (group_id, device_id)
);
