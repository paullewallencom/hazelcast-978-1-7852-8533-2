import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

import java.util.Date;

public class MapLockingExample {
  public static void main(String[] args) {
    HazelcastInstance hz = Hazelcast.newHazelcastInstance();

    IMap<String, Date> arrivals = hz.getMap("arrivals");

    if (arrivals.tryLock("London")) {
      try {
        arrivals.put("London", new Date());
      }
      finally {
        arrivals.unlock("London");
      }
    }
    System.err.println("London: " + arrivals.get("London"));
  }
}
