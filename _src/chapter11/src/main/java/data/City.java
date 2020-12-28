package data;

import com.hazelcast.core.PartitionAware;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity(name = "city")
@Cache(region = "city", usage = CacheConcurrencyStrategy.READ_WRITE)
public class City implements PartitionAware, Serializable {

  private @Id String name;
  private String country;
  private int population;

  public City() {}

  public City(String name, String country, int population) {
    this.name = name;
    this.country = country;
    this.population = population;
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

  @Override
  public Object getPartitionKey() {
    return country;
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
      "City{name='%s', country='%s', population=%d}",
      name, country, population);
  }
}
