import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

import java.util.Map;

public class SimpleMapExample {
  public static void main(String[] args) {
    HazelcastInstance hz = Hazelcast.newHazelcastInstance();

    Map<String, String> captials = hz.getMap("capitals");
    captials.put("GB", "London");
    captials.put("FR", "Paris");
    captials.put("US", "Washington DC");
    captials.put("AU", "Canberra");

    System.err.println(
      "Known capital cities: " + captials.size());

    System.err.println(
      "Capital city of GB: " + captials.get("GB"));
  }
}
