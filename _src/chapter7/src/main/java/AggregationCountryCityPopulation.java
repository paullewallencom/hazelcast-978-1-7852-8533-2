import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.mapreduce.aggregation.Aggregations;
import com.hazelcast.mapreduce.aggregation.PropertyExtractor;
import com.hazelcast.mapreduce.aggregation.Supplier;
import com.hazelcast.query.Predicate;
import data.City;
import data.CityKey;

import java.util.Map;

public class AggregationCountryCityPopulation {
  public static void main(String[] args) throws Exception {
    Config conf = new Config();
    HazelcastInstance hz = Hazelcast.newHazelcastInstance(conf);

    IMap<CityKey, City> cities = hz.getMap("cities");

    if (cities.isEmpty()) {
      cities.put(new CityKey("London", "GB"),
        new City("London", "GB", 8416535, 1572));
      cities.put(new CityKey("Southampton", "GB"),
        new City("Southampton", "GB", 242100, 51));

      cities.put(new CityKey("Chicago", "US"),
        new City("Chicago", "US", 2718782, 606));
      cities.put(new CityKey("Washington DC", "US"),
        new City("Washington DC", "US", 658893, 177));
      cities.put(new CityKey("Seattle", "US"),
        new City("Seattle", "US", 652405, 370));
    }

    Supplier<CityKey, City, Integer> gbCityPopulation6 =
        Supplier.fromPredicate(
            new Predicate<CityKey, City>() {
              @Override
              public boolean apply(Map.Entry<CityKey, City> entry) {
                return "GB".equals(entry.getValue().getCountry());
              }
            },
            Supplier.<CityKey, City, Integer>all(
                new PropertyExtractor<City, Integer>() {
                  @Override
                  public Integer extract(City city) {
                    return city.getPopulation();
                  }
                }
            )
        );
    int result6 = cities.aggregate(gbCityPopulation6, Aggregations.<CityKey, City>integerSum());
    System.err.println(result6);

    Supplier<CityKey, City, Integer> gbCityPopulation8 =
      Supplier.fromPredicate(
        entry -> "GB".equals(entry.getValue().getCountry()),
        Supplier.all(city -> city.getPopulation())
      );
    int result8 = cities.aggregate(gbCityPopulation8, Aggregations.integerSum());
    System.err.println(result8);
  }
}
