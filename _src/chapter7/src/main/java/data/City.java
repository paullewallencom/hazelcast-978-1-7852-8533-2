package data;

import java.io.Serializable;

public class City implements Serializable, Comparable<City> {
  private String name;
  private String country;
  private int population;
  private int size;

  public City(String name, String country, int population, int size) {
    this.name = name;
    this.country = country;
    this.population = population;
    this.size = size;
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

  public int getSize() {
    return size;
  }

  public void setSize(int size) {
    this.size = size;
  }

  @Override
  public int compareTo(City o) {
    return getPopulation() - o.getPopulation();
  }

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
      "City{name='%s', country='%s', population=%d, size=%d}",
      name, country, population, size);
  }
}
