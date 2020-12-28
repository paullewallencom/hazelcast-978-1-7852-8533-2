package listeners;

import com.hazelcast.core.Message;
import com.hazelcast.core.MessageListener;

public class TopicListener implements MessageListener<String> {

  @Override
  public void onMessage(Message<String> message) {
    System.err.println("Received message: " + message.getMessageObject());
  }
}
