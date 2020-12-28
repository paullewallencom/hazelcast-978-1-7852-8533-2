import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IExecutorService;
import com.hazelcast.core.Member;
import tasks.TimeInstanceAwareCallable;

import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ExecutionOnMemberExample {
  public static void main(String[] args) throws Exception {
    Config conf = new Config();
    HazelcastInstance hz = Hazelcast.newHazelcastInstance(conf);

    Member thisMember = hz.getCluster().getLocalMember();
    IExecutorService exec = hz.getExecutorService("exec");

    Callable<String> timeTask = new TimeInstanceAwareCallable();

    Member member = thisMember;

    Future<String> specificTask =
        exec.submitToMember(timeTask, member);

    String timeFromSpecificMember =
        specificTask.get(10, TimeUnit.SECONDS);

    System.err.println(timeFromSpecificMember);
  }
}
