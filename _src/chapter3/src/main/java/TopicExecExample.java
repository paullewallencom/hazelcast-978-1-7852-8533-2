import listeners.TopicExecListener;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.ITopic;

import java.util.Date;

public class TopicExecExample {
  public static void main(String[] args) throws Exception {
    HazelcastInstance hz = Hazelcast.newHazelcastInstance();

    ITopic<String> textTopic = hz.getTopic("broadcast");
    textTopic.addMessageListener(new TopicExecListener());

    while (true) {
      textTopic.publish(
        new Date() + " - " + hz.getCluster().getLocalMember() + " says hello");

      Thread.sleep(1000);
    }
  }
}
