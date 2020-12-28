import listeners.MapEntryListener;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

import java.util.concurrent.TimeUnit;

public class MapEntryListeningExample {
  public static void main(String[] args) {
    HazelcastInstance hz = Hazelcast.newHazelcastInstance();

    IMap<String, String> capitals = hz.getMap("capitals");
    capitals.addEntryListener(new MapEntryListener(), true);

    capitals.put("GB", "Winchester");
    capitals.put("GB", "London");
    capitals.put("DE", "Berlin", 10, TimeUnit.SECONDS);
    capitals.remove("GB");
  }
}
