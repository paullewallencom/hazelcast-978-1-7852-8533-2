import com.hazelcast.config.Config;
import com.hazelcast.core.*;
import tasks.MemberPartitionsCallable;
//import com.hazelcast.core.MultiTask;
//import com.hazelcast.monitor.DistributedMemberInfoCallable;
//import com.hazelcast.monitor.DistributedMemberInfoCallable.MemberInfo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

public class MemberPartitionsExample {
  public static void main(String[] args) throws Exception {
    Config conf = new Config();
    HazelcastInstance hz = Hazelcast.newHazelcastInstance(conf);
    IExecutorService exec = hz.getExecutorService("exec");

    exec.submitToAllMembers(
      new MemberPartitionsCallable(),
      new MemberPartitionsCallable.MemberPartitionsCallback());
  }
}
