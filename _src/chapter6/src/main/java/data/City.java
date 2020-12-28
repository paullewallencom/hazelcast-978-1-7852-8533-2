package data;

import com.hazelcast.core.PartitionAware;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class City implements Serializable {
  private String name;
  private String country;
  private int population;
  private int year;

  public City(String name, String country, int population) {
    this(name, country, population, Calendar.getInstance().get(Calendar.YEAR));
  }

  public City(String name, String country, int population, int year) {
    this.name = name;
    this.country = country;
    this.population = population;
    this.year = year;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public int getPopulation() {
    return population;
  }

  public void setPopulation(int population) {
    this.population = population;
  }

  public int getYear() { return year; }

  public void setYear(int year) { this.year = year; }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    City other = (City) o;
    if (!this.country.equals(other.country)) return false;
    if (!this.name.equals(other.name)) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = name.hashCode();
    result = 31 * result + country.hashCode();
    return result;
  }

  @Override
  public String toString() {
    return String.format(
      "City{name='%s', country='%s', population=%d, year=%d}",
      name, country, population, year);
  }
}
