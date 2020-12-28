package mapreduce.mapper;

import com.hazelcast.mapreduce.Context;
import com.hazelcast.mapreduce.Mapper;
import data.City;
import data.CityKey;

public class CountryCityDensityMapper implements Mapper<CityKey, City, String, Integer> {

  @Override
  public void map(CityKey k, City v, Context<String, Integer> c) {
    c.emit(v.getCountry(), v.getPopulation() / v.getSize());
  }
}
