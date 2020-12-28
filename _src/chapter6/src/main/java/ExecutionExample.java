import tasks.TimeCallable;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class ExecutionExample {
  public static void main(String[] args) throws Exception {
    Config conf = new Config();
    HazelcastInstance hz = Hazelcast.newHazelcastInstance(conf);

    ExecutorService exec = hz.getExecutorService("exec");

    while(true) {
      Future<String> timeFuture = exec.submit(new TimeCallable());
      String theTime = timeFuture.get();

      System.err.println("The time is: " + theTime);

      Thread.sleep(1000);
    }
  }
}
