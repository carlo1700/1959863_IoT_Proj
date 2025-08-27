package com.smarthome.logging;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.Instant;

public class DeviceEventRepository {
  private final DataSource ds;
  public DeviceEventRepository(DataSource ds) { this.ds = ds; }

  public void logEvent(String deviceId, String source, String action, String status,
                       String userName, String payloadJson, String errorMsg) throws Exception {
    String sql = "INSERT INTO device_events " +
        "(device_id, ts, source, action, status, user_name, payload_json, error_msg) " +
        "VALUES (?, ?, ?, ?, ?, ?, CAST(? AS jsonb), ?)";
    try (Connection c = ds.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
      ps.setString(1, deviceId);
      ps.setTimestamp(2, Timestamp.from(Instant.now()));
      ps.setString(3, source);
      ps.setString(4, action);
      ps.setString(5, status);
      ps.setString(6, userName);
      ps.setString(7, payloadJson);
      ps.setString(8, errorMsg);
      ps.executeUpdate();
    }
  }
}
