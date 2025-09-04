package com.smarthome.logging;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

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
}
