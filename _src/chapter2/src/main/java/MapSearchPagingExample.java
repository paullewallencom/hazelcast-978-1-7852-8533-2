import data.City;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.query.EntryObject;
import com.hazelcast.query.PagingPredicate;
import com.hazelcast.query.Predicate;
import com.hazelcast.query.PredicateBuilder;
import com.hazelcast.query.Predicates;

import java.util.Collection;

public class MapSearchPagingExample {
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

    Predicate largeCityPredicate = Predicates.greaterThan("population", 1000000);
    Collection<City> largeCities = capitals.values(largeCityPredicate);

    PagingPredicate pagingPredicate = new PagingPredicate(largeCityPredicate, 1);

    System.err.println(capitals.values(pagingPredicate));
    pagingPredicate.nextPage();
    System.err.println(capitals.values(pagingPredicate));
  }
}
