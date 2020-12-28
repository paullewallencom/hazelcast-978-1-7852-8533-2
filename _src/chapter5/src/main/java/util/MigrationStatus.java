package util;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public abstract class MigrationStatus {
  private static final Map<Integer, Boolean> MIGRATION_STATE =
    new ConcurrentHashMap<Integer, Boolean>();

  private static final AtomicLong LAST_MIGRATION_TIME =
    new AtomicLong(System.currentTimeMillis());

  public static void migrationEvent(int partitionId, boolean state) {
    MIGRATION_STATE.put(partitionId, state);
    if (!state) {
      LAST_MIGRATION_TIME.set(System.currentTimeMillis());
    }
  }

  public static boolean isMigrating() {
    Collection<Boolean> migrationStates = MIGRATION_STATE.values();
    Long lastMigrationTime = LAST_MIGRATION_TIME.get();

    // did we recently (< 10 seconds ago) complete a migration
    if (System.currentTimeMillis() < lastMigrationTime + 10000) {
      return true;
    }

    // are any partitions currently migrating
    for (Boolean partition : migrationStates) {
      if (partition) {
        return true;
      }
    }

    // otherwise we're not migrating
    return false;
  }
}
