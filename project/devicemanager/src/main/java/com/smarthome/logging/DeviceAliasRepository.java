package com.smarthome.logging;

import javax.sql.DataSource;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class DeviceAliasRepository {
    private final DataSource ds;

    public DeviceAliasRepository(DataSource ds) {
        this.ds = ds;
        ensureSchema();
    }

    private void ensureSchema() {
        final String ddl = "CREATE TABLE IF NOT EXISTS device_aliases (" +
                " device_id TEXT PRIMARY KEY," +
                " display_name TEXT NOT NULL" +
                ")";
        try (Connection c = ds.getConnection(); Statement st = c.createStatement()) {
            st.executeUpdate(ddl);
        } catch (Exception e) {
            System.err.println("ensureSchema(device_aliases) error: " + e.getMessage());
        }
    }

    public void upsertAlias(String deviceId, String displayName) throws Exception {
        final String sql = "INSERT INTO device_aliases(device_id, display_name) VALUES (?,?) " +
                           "ON CONFLICT (device_id) DO UPDATE SET display_name=EXCLUDED.display_name";
        try (Connection c = ds.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, deviceId);
            ps.setString(2, displayName);
            ps.executeUpdate();
        }
    }

    public void deleteAlias(String deviceId) throws Exception {
        try (Connection c = ds.getConnection();
             PreparedStatement ps = c.prepareStatement("DELETE FROM device_aliases WHERE device_id=?")) {
            ps.setString(1, deviceId);
            ps.executeUpdate();
        }
    }

    public String getAlias(String deviceId) throws Exception {
        try (Connection c = ds.getConnection();
             PreparedStatement ps = c.prepareStatement("SELECT display_name FROM device_aliases WHERE device_id=?")) {
            ps.setString(1, deviceId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getString(1);
                return null;
            }
        }
    }

    public Map<String,String> loadAll() throws Exception {
        Map<String,String> out = new HashMap<>();
        try (Connection c = ds.getConnection();
             PreparedStatement ps = c.prepareStatement("SELECT device_id, display_name FROM device_aliases");
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                out.put(rs.getString(1), rs.getString(2));
            }
        }
        return out;
    }
}
