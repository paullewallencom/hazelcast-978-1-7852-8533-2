import com.hazelcast.config.Config;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.QuorumConfig;
import com.hazelcast.config.QuorumListenerConfig;
import com.hazelcast.console.ConsoleApp;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.Member;
import com.hazelcast.quorum.QuorumFunction;
import com.hazelcast.quorum.QuorumType;
import listeners.ClusterQuorumListener;

import java.util.Collection;

public class QuorumExample {
  public static void main(String[] args) throws Exception {
    QuorumConfig quorumConf = new QuorumConfig();
    quorumConf.setName("atLeastTwoNodesWithMajority");
    quorumConf.setEnabled(true);
    quorumConf.setType(QuorumType.WRITE);
    quorumConf.addListenerConfig(new QuorumListenerConfig(new ClusterQuorumListener()));

    final int expectedClusterSize = 5;
    quorumConf.setQuorumFunctionImplementation(new QuorumFunction() {
      @Override
      public boolean apply(Collection<Member> members) {
        return members.size() >= 2 && members.size() > expectedClusterSize / 2;
      }
    });

    MapConfig mapConf = new MapConfig();
    mapConf.setName("default");
    mapConf.setQuorumName("atLeastTwoNodesWithMajority");

    Config conf = new Config();
    conf.addQuorumConfig(quorumConf);
    conf.addMapConfig(mapConf);

    HazelcastInstance hz = Hazelcast.newHazelcastInstance(conf);
    new ConsoleApp(hz).start(args);
  }
}
