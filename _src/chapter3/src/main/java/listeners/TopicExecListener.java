package listeners;

import com.hazelcast.core.Message;
import com.hazelcast.core.MessageListener;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TopicExecListener implements MessageListener<String> {

  private ExecutorService exec = Executors.newFixedThreadPool(10);

  @Override
  public void onMessage(final Message<String> message) {
    exec.execute(new Runnable() {
      @Override
      public void run() {
        System.err.println("Received message: " + message.getMessageObject());
      }
    });
  }
}
