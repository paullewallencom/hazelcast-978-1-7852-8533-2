import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IQueue;
import com.hazelcast.core.ITopic;
import listeners.TopicListener;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class QueueExample {
  public static void main(String[] args) throws Exception {
    HazelcastInstance hz = Hazelcast.newHazelcastInstance();
    IQueue<String> queue = hz.getQueue("queue");
    Random rand = new Random();

    while (true) {
      queue.add(
        new Date() + " - " + hz.getCluster().getLocalMember() + " says hello");

      Thread.sleep(rand.nextInt(2000));
      String msg = queue.poll(5, TimeUnit.SECONDS);
      if (msg != null) {
        System.err.println(msg);
      }
    }
  }
}
