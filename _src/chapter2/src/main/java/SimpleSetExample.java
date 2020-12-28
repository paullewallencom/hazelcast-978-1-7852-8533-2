import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class SimpleSetExample {
  public static void main(String[] args) {
    HazelcastInstance hz = Hazelcast.newHazelcastInstance();

    Map<String, String> captials = hz.getMap("capitals");
    captials.put("GB", "London");
    captials.put("FR", "Paris");
    captials.put("US", "Washington DC");
    captials.put("AU", "Canberra");

    Set<String> cities = hz.getSet("cities");
    cities.addAll(captials.values());
    cities.add("London");
    cities.add("Rome");
    cities.add("New York");

    List<String> countries = hz.getList("countries");
    countries.addAll(captials.keySet());
    countries.add("CA");
    countries.add("DE");
    countries.add("GB");
  }
}
