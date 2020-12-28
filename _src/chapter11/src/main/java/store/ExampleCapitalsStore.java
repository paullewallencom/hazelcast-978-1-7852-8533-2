package store;

import com.hazelcast.core.MapStore;
import data.City;

import java.sql.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ExampleCapitalsStore
  implements MapStore<String, City> {

  Connection conn;
  PreparedStatement[] stmts = new PreparedStatement[10];

  public ExampleCapitalsStore() throws Exception {
    Class.forName("org.h2.Driver");
    conn = DriverManager.getConnection("jdbc:h2:test-capitals");
    conn.createStatement()
      .execute("CREATE TABLE IF NOT EXISTS capitals (" +
        "id VARCHAR(2) PRIMARY KEY, name VARCHAR(255), " +
        "country VARCHAR(2), population INT);");

    stmts[0] = conn.prepareStatement(
      "INSERT INTO capitals VALUES (?,?,?,?)");
    stmts[1] = conn.prepareStatement(
      "DELETE FROM capitals WHERE id = ?");
    stmts[2] = conn.prepareStatement(
      "SELECT * FROM capitals WHERE id = ?");
  }

  @Override
  public synchronized void store(String key, City value) {
    try {
      stmts[0].setString(1, key);
      stmts[0].setString(2, value.getName());
      stmts[0].setString(3, value.getCountry());
      stmts[0].setInt(4, value.getPopulation());
      stmts[0].executeUpdate();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void storeAll(Map<String, City> map) {
    for (Map.Entry<String, City> entry : map.entrySet()) {
      store(entry.getKey(), entry.getValue());
    }
  }

  @Override
  public synchronized void delete(String key) {
    try {
      stmts[1].setString(1, key);
      stmts[1].executeUpdate();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void deleteAll(Collection<String> keys) {
    for (String key : keys) {
      delete(key);
    }
  }

  @Override
  public synchronized City load(String key) {
    try {
      stmts[2].setString(1, key);
      ResultSet rs = stmts[2].executeQuery();
      try {
        if (!rs.next()) {
          return null;
        }
        return new City(
          rs.getString(2), rs.getString(3), rs.getInt(4));
      } finally {
        rs.close();
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public Map<String, City> loadAll(Collection<String> keys) {
    Map<String, City> ret = new ConcurrentHashMap<String, City>();
    for (String key : keys) {
      City value = load(key);
      if (value != null) {
        ret.put(key, value);
      }
    }
    return ret;
  }

  @Override
  public Iterable<String> loadAllKeys() {
    return Arrays.asList("GB");
  }
}

