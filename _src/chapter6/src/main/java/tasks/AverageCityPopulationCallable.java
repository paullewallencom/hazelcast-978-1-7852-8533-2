package tasks;

import data.City;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.HazelcastInstanceAware;
import com.hazelcast.core.IMap;
import com.hazelcast.core.PartitionAware;
import com.hazelcast.query.Predicate;
import com.hazelcast.query.Predicates;
import data.CityKey;

import java.io.Serializable;
import java.util.Collection;
import java.util.concurrent.Callable;

public class AverageCityPopulationCallable implements Callable<Integer>, HazelcastInstanceAware, PartitionAware, Serializable {

  private String country;
  private HazelcastInstance hz;

  public AverageCityPopulationCallable(String country) {
    this.country = country;
  }

  @Override
  public void setHazelcastInstance(HazelcastInstance hz) {
    this.hz = hz;
  }

  @Override
  public Object getPartitionKey() {
    return country;
  }

  @Override
  public Integer call() throws Exception {
    System.err.println("Running task on: " + hz.getCluster().getLocalMember().toString());
    IMap<CityKey, City> cities = hz.getMap("cities");
    Predicate countryCityPredicate = Predicates.equal("country", country);
    Collection<City> countryCities = cities.values(countryCityPredicate);

    int totalPopulation = 0;
    for (City countryCity : countryCities) {
      totalPopulation += countryCity.getPopulation();
    }

    return totalPopulation / countryCities.size();
  }
}
