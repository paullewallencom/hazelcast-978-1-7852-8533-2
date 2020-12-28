package tasks;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.HazelcastInstanceAware;

import java.io.Serializable;
import java.util.Date;
import java.util.concurrent.Callable;

public class TimeInstanceAwareCallable implements Callable<String>, HazelcastInstanceAware, Serializable {

  private HazelcastInstance hz;

  @Override
  public void setHazelcastInstance(HazelcastInstance hz) {
    this.hz = hz;
  }

  @Override
  public String call() throws Exception {
    return hz.getCluster().getLocalMember().toString() + " - " + new Date().toString();
  }
}
