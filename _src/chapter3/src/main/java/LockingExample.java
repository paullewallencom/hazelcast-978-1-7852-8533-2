import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

public class LockingExample {
  public static void main(String[] args) throws Exception {
    HazelcastInstance hz = Hazelcast.newHazelcastInstance();

    Lock lock = hz.getLock("theTime");

    while(true) {
      if (lock.tryLock(30, TimeUnit.SECONDS)) {
        try {
          while(true) {
            System.err.println(new Date());
            Thread.sleep(1000);
          }
        }
        finally {
          lock.unlock();
        }
      }
    }
  }
}
