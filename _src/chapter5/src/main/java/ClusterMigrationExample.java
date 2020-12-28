import listeners.ClusterMembershipListener;
import listeners.ClusterMigrationListener;
import listeners.ClusterObjectListener;
import listeners.NodeLifecycleListener;
import util.MigrationStatus;

import com.hazelcast.config.Config;
import com.hazelcast.config.ListenerConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

public class ClusterMigrationExample {
  public static void main(String[] args) throws Exception {
    Config conf = new Config();
    conf.addListenerConfig(new ListenerConfig(new ClusterObjectListener()));
    conf.addListenerConfig(new ListenerConfig(new ClusterMembershipListener()));
    conf.addListenerConfig(new ListenerConfig(new ClusterMigrationListener()));
    conf.addListenerConfig(new ListenerConfig(new NodeLifecycleListener()));

    HazelcastInstance hz = Hazelcast.newHazelcastInstance(conf);

    while(true) {
      System.err.println("Is Migrating?: " + MigrationStatus.isMigrating());
      Thread.sleep(5000);
    }
  }
}
