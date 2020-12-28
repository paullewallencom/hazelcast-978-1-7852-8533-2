package listeners;

import com.hazelcast.core.DistributedObjectEvent;
import com.hazelcast.core.DistributedObjectListener;

public class ClusterObjectListener implements DistributedObjectListener {

  @Override
  public void distributedObjectCreated(DistributedObjectEvent event) {
    System.err.println("Object Created: " + event);
  }

  @Override
  public void distributedObjectDestroyed(DistributedObjectEvent event) {
    System.err.println("Object Destroyed: " + event);
  }
}
