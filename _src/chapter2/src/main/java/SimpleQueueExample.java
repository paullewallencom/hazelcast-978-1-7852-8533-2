import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

import java.util.concurrent.BlockingQueue;

public class SimpleQueueExample {
  public static void main(String[] args) throws Exception {
    HazelcastInstance hz = Hazelcast.newHazelcastInstance();

    BlockingQueue<String> arrivals = hz.getQueue("arrivals");

    while (true) {
      String arrival = arrivals.take();
      System.err.println(
        "New arrival from: " + arrival);

    }
  }
}
