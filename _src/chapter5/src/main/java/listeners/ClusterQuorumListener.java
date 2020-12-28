package listeners;

import com.hazelcast.quorum.QuorumEvent;
import com.hazelcast.quorum.QuorumListener;

public class ClusterQuorumListener implements QuorumListener {
  @Override
  public void onChange(QuorumEvent quorumEvent) {
    System.err.println("Changed: " + quorumEvent);
  }
}
