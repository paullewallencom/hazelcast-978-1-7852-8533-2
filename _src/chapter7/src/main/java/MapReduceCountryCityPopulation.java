import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.mapreduce.*;
import data.City;
import data.CityKey;
import mapreduce.combiner.IntegerSumCombinerFactory;
import mapreduce.mapper.CountryCityPopulationMapper;
import mapreduce.reducer.IntegerSumReducerFactory;

import java.util.Map;

public class MapReduceCountryCityPopulation {
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

    JobTracker jobTracker = hz.getJobTracker("default");
    KeyValueSource<CityKey, City> sourceData = KeyValueSource.fromMap(cities);
    Job<CityKey, City> job = jobTracker.newJob(sourceData);

    JobCompletableFuture<Map<String, Integer>> future = job
      .mapper(new CountryCityPopulationMapper())
      .combiner(new IntegerSumCombinerFactory())
      .reducer(new IntegerSumReducerFactory())
      .submit();

    Map<String, Integer> result = future.get();
    System.err.println(result);
  }
}
