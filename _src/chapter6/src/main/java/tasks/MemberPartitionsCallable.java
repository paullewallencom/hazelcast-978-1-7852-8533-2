package tasks;

import com.hazelcast.core.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;

public class MemberPartitionsCallable implements
  Callable<MemberPartitionsCallable.MemberPartitions>,
    HazelcastInstanceAware, Serializable {

  private HazelcastInstance hz;

  @Override
  public void setHazelcastInstance(HazelcastInstance hz) {
    this.hz = hz;
  }

  @Override
  public MemberPartitions call() throws Exception {
    MemberPartitions mp = new MemberPartitions();
    mp.partitions = new HashSet<Integer>();
    for (Partition p : hz.getPartitionService().getPartitions()) {
      if (p.getOwner().localMember()) {
        mp.partitions.add(p.getPartitionId());
      };
    }
    return mp;
  }

  public static class MemberPartitionsCallback implements MultiExecutionCallback {

    @Override
    public void onResponse(Member member, Object value) {
      System.err.println(member + ", partitions: " +
          ((MemberPartitions) value).getPartitions().size());
    }

    @Override
    public void onComplete(Map<Member, Object> values) {}
  }

  public static class MemberPartitions implements Serializable {
    private Set<Integer> partitions;

    public Set<Integer> getPartitions() {
      return partitions;
    }
  }
}