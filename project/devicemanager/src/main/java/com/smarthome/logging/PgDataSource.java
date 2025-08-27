package com.smarthome.logging;

import javax.sql.DataSource;
import org.postgresql.ds.PGSimpleDataSource;

public final class PgDataSource {
  private static PGSimpleDataSource ds;

  public static synchronized DataSource get() {
    if (ds == null) {
      String url  = System.getenv().getOrDefault("DB_URL",  "jdbc:postgresql://localhost:5432/logs");
      String user = System.getenv().getOrDefault("DB_USER", "smarthome");
      String pass = System.getenv().getOrDefault("DB_PASS", "smarthome");

      PGSimpleDataSource p = new PGSimpleDataSource();
      p.setURL(url);       // accetta direttamente la JDBC URL
      p.setUser(user);
      p.setPassword(pass);

      ds = p;
    }
    return ds;
  }
}
