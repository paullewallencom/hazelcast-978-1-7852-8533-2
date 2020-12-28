import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.TransactionalMap;
import com.hazelcast.transaction.TransactionContext;

public class TransactionExample {
  public static void main(String[] args) throws Exception {
    HazelcastInstance hz = Hazelcast.newHazelcastInstance();

    TransactionContext tx = hz.newTransactionContext();
    tx.beginTransaction();

    TransactionalMap<String, String> testMap = tx.getMap("test");

    try {
      System.err.println(testMap.get("foo"));
      Thread.sleep(30000);

      System.err.println(testMap.get("foo"));
      testMap.put("foo", "bar");
      Thread.sleep(30000);

      tx.commitTransaction();
      System.err.println("Committed!");
    }
    catch (Exception e) {
      tx.rollbackTransaction();
      System.err.println("Rolled Back!");
    }
  }
}
