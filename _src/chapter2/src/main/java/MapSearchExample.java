import data.City;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.query.SqlPredicate;

import java.util.Collection;

public class MapSearchExample {
  public static void main(String[] args) {
    HazelcastInstance hz = Hazelcast.newHazelcastInstance();

    IMap<String, City> capitals = hz.getMap("capitals");
    capitals.addIndex("name", false);
    capitals.addIndex("population", true);

    capitals.put("GB",
      new City("London", "GB", 8174100));
    capitals.put("FR",
      new City("Paris", "FR", 2268265));
    capitals.put("US",
      new City("Washington DC", "US", 601723));
    capitals.put("AU",
      new City("Canberra", "AU", 354644));

    Collection<City> possibleLondons = capitals.values(new SqlPredicate("name = 'London'"));
    System.err.println(possibleLondons);

    Collection<City> largeCities = capitals.values(new SqlPredicate("population > 1000000"));
    System.err.println(largeCities);
  }
}
