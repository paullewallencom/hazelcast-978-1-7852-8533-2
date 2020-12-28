package listeners;

import com.hazelcast.core.MembershipEvent;
import com.hazelcast.core.MembershipListener;
import com.hazelcast.core.MemberAttributeEvent;

public class ClusterMembershipListener implements MembershipListener {

  @Override
  public void memberAdded(MembershipEvent membershipEvent) {
    System.err.println("Added: " + membershipEvent);
  }

  @Override
  public void memberRemoved(MembershipEvent membershipEvent) {
    System.err.println("Removed: " + membershipEvent);
  }

  @Override
  public void memberAttributeChanged(MemberAttributeEvent memberAttributeEvent) {
    System.err.println("Changed: " + memberAttributeEvent);
  }
}
