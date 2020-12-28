package tasks;

import com.hazelcast.map.AbstractEntryProcessor;
import data.City;
import data.CityKey;

import java.util.Calendar;
import java.util.Map;

public class CityGrowthEntryProcessor extends AbstractEntryProcessor<CityKey, City> {

  double growthPerYear;

  public CityGrowthEntryProcessor(double growthPerYear) {
    this.growthPerYear = growthPerYear;
  }

  @Override
  public Object process(Map.Entry<CityKey, City> entry) {

    City city = entry.getValue();

    int yearNow = Calendar.getInstance().get(Calendar.YEAR);
    int yearEntry = city.getYear();

    if (yearEntry < yearNow) {
      city.setPopulation((int)(city.getPopulation() * (1+ growthPerYear)));
      city.setYear(yearEntry +1);
      entry.setValue(city);
    }

    return city;
  }
}
