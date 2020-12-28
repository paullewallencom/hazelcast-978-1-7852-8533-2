import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

public class AtomicMapExample {
  public static void main(String[] args) {
    HazelcastInstance hz = Hazelcast.newHazelcastInstance();

    IMap<String, String> capitals = hz.getMap("capitals");

    capitals.putIfAbsent("GB", "Winchester");
    System.err.println("Capital of GB (until 1066): " +
      capitals.get("GB"));

    String actualCapital = capitals.putIfAbsent("GB", "London");
    System.err.println("Failed to put as already present: " +
      capitals.get("GB") + " = " + actualCapital);

    boolean r1 = capitals.replace("GB", "Southampton", "London");
    System.err.println("Failed to replace as incorrect old value: " +
      capitals.get("GB") + "; [" + r1 + "]");

    boolean r2 = capitals.replace("GB", "Winchester", "London");
    System.err.println("Capital of GB (since 1066): " +
      capitals.get("GB") + "; [" + r2 + "]");
  }
}
