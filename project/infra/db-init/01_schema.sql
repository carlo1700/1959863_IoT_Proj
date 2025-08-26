CREATE TABLE IF NOT EXISTS device_events (
  id BIGSERIAL PRIMARY KEY,
  device_id TEXT NOT NULL,
  ts TIMESTAMPTZ NOT NULL DEFAULT now(),
  source TEXT NOT NULL,
  action TEXT NOT NULL,
  status TEXT NOT NULL,
  user_name TEXT,
  payload_json JSONB,
  error_msg TEXT
);
CREATE INDEX IF NOT EXISTS idx_device_events_device_time ON device_events(device_id, ts DESC);
CREATE INDEX IF NOT EXISTS idx_device_events_time ON device_events(ts DESC);
CREATE INDEX IF NOT EXISTS idx_device_events_status ON device_events(status);
CREATE INDEX IF NOT EXISTS idx_device_events_action ON device_events(action);
