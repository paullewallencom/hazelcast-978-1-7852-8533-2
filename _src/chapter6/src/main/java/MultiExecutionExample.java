import com.hazelcast.core.*;
import tasks.TimeInstanceAwareCallable;

import com.hazelcast.config.Config;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class MultiExecutionExample {
  public static void main(String[] args) throws Exception {
    Config conf = new Config();
    HazelcastInstance hz = Hazelcast.newHazelcastInstance(conf);

    IExecutorService exec = hz.getExecutorService("exec");

    Callable<String> timeTask = new TimeInstanceAwareCallable();
    MultiExecutionCallback callback =
      new MultiExecutionCallback() {

        @Override
        public void onResponse(Member member, Object theTime) {
          System.err.println("Received: " + theTime);
        }

        @Override
        public void onComplete(Map<Member, Object> theTimes) {
          for (Object theTime : theTimes.values()) {
            System.err.println("Complete: " + theTime);
          }
        }
      };

    while (true) {
      Set<Member> clusterMembers = hz.getCluster().getMembers();

      exec.submitToMembers(
        timeTask, clusterMembers, callback);

      Thread.sleep(1000);
    }
  }
}

