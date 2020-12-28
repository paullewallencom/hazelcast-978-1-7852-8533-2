import com.hazelcast.config.Config;
import com.hazelcast.config.MapConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import data.City;

public class MapStoreExample {
  public static void main(String[] args) throws Exception {
    HazelcastInstance hz = Hazelcast.newHazelcastInstance();

    IMap<String, City> capitals = hz.getMap("capitals");
    if (!capitals.containsKey("GB")) {
      System.err.println("Creating London record");
      capitals.put("GB", new City("London", "GB", 8416535));
    }
    if (!capitals.containsKey("FR")) {
      System.err.println("Creating Paris record");
      capitals.put("FR", new City("Paris", "FR", 2241346));
    }

    System.err.println("GB: " + capitals.get("GB"));
    System.err.println("FR: " + capitals.get("FR"));
  }
}
