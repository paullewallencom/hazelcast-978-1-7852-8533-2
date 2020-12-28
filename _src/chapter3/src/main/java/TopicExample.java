import listeners.TopicListener;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.ITopic;

import java.util.Date;

public class TopicExample {
  public static void main(String[] args) throws Exception {
    HazelcastInstance hz = Hazelcast.newHazelcastInstance();

    ITopic<String> textTopic = hz.getTopic("broadcast");
    textTopic.addMessageListener(new TopicListener());

    while (true) {
      textTopic.publish(
        new Date() + " - " + hz.getCluster().getLocalMember() + " says hello");

      Thread.sleep(1000);
    }
  }
}
