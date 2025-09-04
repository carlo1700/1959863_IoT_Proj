package com.smarthome.logging;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/** CRUD minimale per stanze e gruppi + query dei device. */
public class RoomGroupRepository {

    private final DataSource ds;

    public RoomGroupRepository(DataSource ds) {
        this.ds = ds;
        ensureSchema(); // idempotente: ok se la tabella esiste già
    }

    private void ensureSchema() {
        final String ddl =
            "CREATE TABLE IF NOT EXISTS rooms (room_id TEXT PRIMARY KEY);" +
            "CREATE TABLE IF NOT EXISTS room_devices (" +
            "  room_id   TEXT NOT NULL REFERENCES rooms(room_id) ON DELETE CASCADE," +
            "  device_id TEXT NOT NULL," +
            "  PRIMARY KEY (room_id, device_id)" +
            ");" +
            "CREATE TABLE IF NOT EXISTS device_groups (group_id TEXT PRIMARY KEY);" +
            "CREATE TABLE IF NOT EXISTS group_devices (" +
            "  group_id  TEXT NOT NULL REFERENCES device_groups(group_id) ON DELETE CASCADE," +
            "  device_id TEXT NOT NULL," +
            "  PRIMARY KEY (group_id, device_id)" +
            ");" +
            "CREATE INDEX IF NOT EXISTS idx_room_devices_room    ON room_devices(room_id);" +
            "CREATE INDEX IF NOT EXISTS idx_room_devices_device  ON room_devices(device_id);" +
            "CREATE INDEX IF NOT EXISTS idx_group_devices_group  ON group_devices(group_id);" +
            "CREATE INDEX IF NOT EXISTS idx_group_devices_device ON group_devices(device_id);";

        try (Connection c = ds.getConnection(); Statement st = c.createStatement()) {
            for (String stmt : ddl.split(";")) {
                String s = stmt.trim();
                if (!s.isEmpty()) st.executeUpdate(s);
            }
        } catch (Exception e) {
            System.err.println("ensureSchema(Room/Group) error: " + e.getMessage());
        }
    }

    // ---- Rooms ----
    public void createRoom(String roomId) throws Exception {
        String sql = "INSERT INTO rooms(room_id) VALUES (?) ON CONFLICT(room_id) DO NOTHING";
        try (Connection c = ds.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, roomId);
            ps.executeUpdate();
        }
    }

    public void deleteRoom(String roomId) throws Exception {
        try (Connection c = ds.getConnection();
             PreparedStatement ps = c.prepareStatement("DELETE FROM rooms WHERE room_id = ?")) {
            ps.setString(1, roomId);
            ps.executeUpdate();
        }
    }

    public void addDeviceToRoom(String roomId, String deviceId) throws Exception {
        String sql = "INSERT INTO room_devices(room_id, device_id) VALUES (?, ?) ON CONFLICT DO NOTHING";
        try (Connection c = ds.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, roomId);
            ps.setString(2, deviceId);
            ps.executeUpdate();
        }
    }

    public void removeDeviceFromRoom(String roomId, String deviceId) throws Exception {
        try (Connection c = ds.getConnection();
             PreparedStatement ps = c.prepareStatement("DELETE FROM room_devices WHERE room_id = ? AND device_id = ?")) {
            ps.setString(1, roomId);
            ps.setString(2, deviceId);
            ps.executeUpdate();
        }
    }

    public List<String> listRooms() throws Exception {
        List<String> out = new ArrayList<>();
        try (Connection c = ds.getConnection();
             PreparedStatement ps = c.prepareStatement("SELECT room_id FROM rooms ORDER BY room_id");
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) out.add(rs.getString(1));
        }
        return out;
    }

    public List<String> listDevicesInRoom(String roomId) throws Exception {
        List<String> out = new ArrayList<>();
        try (Connection c = ds.getConnection();
             PreparedStatement ps = c.prepareStatement("SELECT device_id FROM room_devices WHERE room_id = ? ORDER BY device_id")) {
            ps.setString(1, roomId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) out.add(rs.getString(1));
            }
        }
        return out;
    }

    // ---- Groups ----
    public void createGroup(String groupId) throws Exception {
        String sql = "INSERT INTO device_groups(group_id) VALUES (?) ON CONFLICT(group_id) DO NOTHING";
        try (Connection c = ds.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, groupId);
            ps.executeUpdate();
        }
    }

    public void deleteGroup(String groupId) throws Exception {
        try (Connection c = ds.getConnection();
             PreparedStatement ps = c.prepareStatement("DELETE FROM device_groups WHERE group_id = ?")) {
            ps.setString(1, groupId);
            ps.executeUpdate();
        }
    }

    public void addDeviceToGroup(String groupId, String deviceId) throws Exception {
        String sql = "INSERT INTO group_devices(group_id, device_id) VALUES (?, ?) ON CONFLICT DO NOTHING";
        try (Connection c = ds.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, groupId);
            ps.setString(2, deviceId);
            ps.executeUpdate();
        }
    }

    public void removeDeviceFromGroup(String groupId, String deviceId) throws Exception {
        try (Connection c = ds.getConnection();
             PreparedStatement ps = c.prepareStatement("DELETE FROM group_devices WHERE group_id = ? AND device_id = ?")) {
            ps.setString(1, groupId);
            ps.setString(2, deviceId);
            ps.executeUpdate();
        }
    }

    public List<String> listGroups() throws Exception {
        List<String> out = new ArrayList<>();
        try (Connection c = ds.getConnection();
             PreparedStatement ps = c.prepareStatement("SELECT group_id FROM device_groups ORDER BY group_id");
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) out.add(rs.getString(1));
        }
        return out;
    }

    public List<String> listDevicesInGroup(String groupId) throws Exception {
        List<String> out = new ArrayList<>();
        try (Connection c = ds.getConnection();
             PreparedStatement ps = c.prepareStatement("SELECT device_id FROM group_devices WHERE group_id = ? ORDER BY device_id")) {
            ps.setString(1, groupId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) out.add(rs.getString(1));
            }
        }
        return out;
    }
}