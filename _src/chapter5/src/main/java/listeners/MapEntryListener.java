package listeners;

import com.hazelcast.core.EntryEvent;
import com.hazelcast.core.EntryListener;
import com.hazelcast.core.MapEvent;

public class MapEntryListener implements EntryListener<String, String> {

  @Override
  public void entryAdded(EntryEvent<String, String> event) {
    System.err.println("Added: " + event);
  }

  @Override
  public void entryRemoved(EntryEvent<String, String> event) {
    System.err.println("Removed: " + event);
  }

  @Override
  public void entryUpdated(EntryEvent<String, String> event) {
    System.err.println("Updated: " + event);
  }

  @Override
  public void entryEvicted(EntryEvent<String, String> event) {
    System.err.println("Evicted: " + event);
  }

  @Override
  public void mapCleared(MapEvent event) {}

  @Override
  public void mapEvicted(MapEvent event) {}
}
