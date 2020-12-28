import com.hazelcast.config.EntryListenerConfig;
import listeners.MapEntryListener;

import com.hazelcast.config.Config;
import com.hazelcast.config.ListenerConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

import java.util.concurrent.TimeUnit;

public class MapEntryListeningConfigExample {
  public static void main(String[] args) {
    Config conf = new Config();
    conf.addListenerConfig(new EntryListenerConfig(new MapEntryListener(), false, true));

    HazelcastInstance hz = Hazelcast.newHazelcastInstance(conf);

    IMap<String, String> capitals = hz.getMap("capitals");

    capitals.put("GB", "Winchester");
    capitals.put("GB", "London");
    capitals.put("DE", "Berlin", 10, TimeUnit.SECONDS);
    capitals.remove("GB");
  }
}
