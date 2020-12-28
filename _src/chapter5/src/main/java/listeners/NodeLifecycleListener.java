package listeners;

import com.hazelcast.core.LifecycleEvent;
import com.hazelcast.core.LifecycleListener;

public class NodeLifecycleListener implements LifecycleListener {

  @Override
  public void stateChanged(LifecycleEvent event) {
    System.err.println(event);
  }
}
