import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IdGenerator;

public class IdGeneratorExample {
  public static void main(String[] args) throws Exception {
    HazelcastInstance hz = Hazelcast.newHazelcastInstance();

    IdGenerator idGen = hz.getIdGenerator("newId");

    while (true) {
      Long id = idGen.newId();
      System.err.println("New Id: " + id);
    }
  }
}
