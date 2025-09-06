package com.smarthome.logging;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DeviceEventRepository {

    private final DataSource ds;

    public DeviceEventRepository(DataSource ds) {
        this.ds = ds;
        ensureSchema();
    }

    /** Crea tabella e indici se non esistono (idempotente). */
    private void ensureSchema() {
        final String ddlTable =
            "CREATE TABLE IF NOT EXISTS device_events (" +
            "  id            BIGSERIAL PRIMARY KEY," +
            "  device_id     TEXT NOT NULL," +
            "  ts            TIMESTAMPTZ NOT NULL DEFAULT now()," +
            "  source        TEXT NOT NULL," +
            "  action        TEXT NOT NULL," +
            "  status        TEXT NOT NULL," +
            "  user_name     TEXT," +
            "  payload_json  JSONB," +
            "  error_msg     TEXT" +
            ");";

        final String idx1 = "CREATE INDEX IF NOT EXISTS idx_device_events_device_time ON device_events(device_id, ts DESC);";
        final String idx2 = "CREATE INDEX IF NOT EXISTS idx_device_events_time ON device_events(ts DESC);";
        final String idx3 = "CREATE INDEX IF NOT EXISTS idx_device_events_status ON device_events(status);";
        final String idx4 = "CREATE INDEX IF NOT EXISTS idx_device_events_action ON device_events(action);";

        try (Connection c = ds.getConnection(); Statement st = c.createStatement()) {
            st.executeUpdate(ddlTable);
            st.executeUpdate(idx1);
            st.executeUpdate(idx2);
            st.executeUpdate(idx3);
            st.executeUpdate(idx4);
        } catch (Exception e) {
            // Non bloccare l'app per un problema di bootstrap schema
            System.err.println("ensureSchema error: " + e.getMessage());
        }
    }

    /** Inserisce una riga nel log. payloadJson può essere null. */
    public void insertEvent(String deviceId,
                            String source,
                            String action,
                            String status,
                            String userName,
                            String payloadJson,
                            String errorMsg) throws Exception {
        final String sql =
            "INSERT INTO device_events " +
            " (device_id, source, action, status, user_name, payload_json, error_msg) " +
            " VALUES (?, ?, ?, ?, ?, CAST(? AS JSONB), ?);";
        try (Connection c = ds.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, deviceId);
            ps.setString(2, source);
            ps.setString(3, action);
            ps.setString(4, status);
            ps.setString(5, userName);
            ps.setString(6, payloadJson); 
            ps.setString(7, errorMsg);
            ps.executeUpdate();
        }
    }

    public static final class DeviceEvent {
        public long   id;
        public String ts;           // String invece di OffsetDateTime
        public String deviceId;
        public String source;
        public String action;
        public String status;
        public String userName;
        public String payloadJson;  // assicurati sia String, non PGobject
        public String errorMsg;
    }

    private DeviceEvent mapRow(ResultSet rs) throws Exception {
        DeviceEvent e = new DeviceEvent();
        e.id         = rs.getLong("id");

        // Leggi TIMESTAMPTZ come OffsetDateTime e poi toString() (ISO-8601)
        java.time.OffsetDateTime odt = rs.getObject("ts", java.time.OffsetDateTime.class);
        e.ts         = odt != null ? odt.toString() : null;

        e.deviceId   = rs.getString("device_id");
        e.source     = rs.getString("source");
        e.action     = rs.getString("action");
        e.status     = rs.getString("status");
        e.userName   = rs.getString("user_name");

        // jsonb come String, NON PGobject
        e.payloadJson = rs.getString("payload_json");
        e.errorMsg    = rs.getString("error_msg");
        return e;
    }

    /** Ultimi N eventi globali */
    public List<DeviceEvent> listRecent(int limit) throws Exception {
        String sql = "SELECT id, ts, device_id, source, action, status, user_name, payload_json, error_msg " +
                    "FROM device_events ORDER BY ts DESC LIMIT ?";
        try (Connection c = ds.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, limit);
            try (ResultSet rs = ps.executeQuery()) {
                List<DeviceEvent> out = new ArrayList<>();
                while (rs.next()) out.add(mapRow(rs));
                return out;
            }
        }
    }

    public List<DeviceEvent> listByDevice(String deviceId, int limit) throws Exception {
        String sql = "SELECT id, ts, device_id, source, action, status, user_name, payload_json, error_msg " +
                    "FROM device_events WHERE device_id = ? ORDER BY ts DESC LIMIT ?";
        try (Connection c = ds.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, deviceId);
            ps.setInt(2, limit);
            try (ResultSet rs = ps.executeQuery()) {
                List<DeviceEvent> out = new ArrayList<>();
                while (rs.next()) out.add(mapRow(rs));
                return out;
            }
        }
    }

    /** Ricerca semplice per azione/stato (opzionale) */
    public List<DeviceEvent> search(String actionLike, String statusEq, int limit) throws Exception {
        final String sql =
            "SELECT id, ts, device_id, source, action, status, user_name, payload_json::text, error_msg " +
            "FROM device_events " +
            "WHERE (? IS NULL OR action ILIKE ?) AND (? IS NULL OR status = ?) " +
            "ORDER BY ts DESC LIMIT ?";
        try (Connection c = ds.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, actionLike == null ? null : actionLike);
            ps.setString(2, actionLike == null ? null : "%" + actionLike + "%");
            ps.setString(3, statusEq);
            ps.setString(4, statusEq);
            ps.setInt(5, Math.max(1, limit));
            try (ResultSet rs = ps.executeQuery()) {
                List<DeviceEvent> out = new ArrayList<>();
                while (rs.next()) out.add(mapRow(rs));
                return out;
            }
        }
    }
}
