import data.City;
import data.CityKey;
import tasks.AverageCityPopulationCallable;

import com.hazelcast.config.Config;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.MapIndexConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class AverageCityPopulationCallableExample {
  public static void main(String[] args) throws Exception {
    Config conf = new Config();
    MapConfig citiesConf = conf.getMapConfig("cities");
    citiesConf.addMapIndexConfig(new MapIndexConfig("country", false));

    HazelcastInstance hz = Hazelcast.newHazelcastInstance(conf);

    IMap<CityKey, City> cities = hz.getMap("cities");

    if (cities.isEmpty()) {
      cities.put(new CityKey("London", "GB"),
        new City("London", "GB", 7322403, 2001));
      cities.put(new CityKey("Southampton", "GB"),
        new City("Southampton", "GB", 226698, 2006));
      cities.put(new CityKey("Plymouth", "GB"),
        new City("Plymouth", "GB", 244037, 2004));
      cities.put(new CityKey("York", "GB"),
        new City("York", "GB", 195070, 2010));

      cities.put(new CityKey("Paris", "FR"),
        new City("Paris", "FR", 2268265, 2013));
    }

    ExecutorService exec = hz.getExecutorService("exec");

    Future<Integer> avgTask = exec.submit(new AverageCityPopulationCallable("GB"));

    Integer avgPop = avgTask.get();
    System.err.println("Average GB city population: " + avgPop);
  }
}
