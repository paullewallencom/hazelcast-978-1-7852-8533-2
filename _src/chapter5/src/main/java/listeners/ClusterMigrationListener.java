package listeners;

import com.hazelcast.core.MigrationEvent;
import com.hazelcast.core.MigrationListener;

public class ClusterMigrationListener implements MigrationListener {

  @Override
  public void migrationStarted(MigrationEvent migrationEvent) {
    System.err.println("Started: " + migrationEvent);
  }

  @Override
  public void migrationCompleted(MigrationEvent migrationEvent) {
    System.err.println("Completed: " + migrationEvent);
  }

  @Override
  public void migrationFailed(MigrationEvent migrationEvent) {
    System.err.println("Failed: " + migrationEvent);
  }
}
