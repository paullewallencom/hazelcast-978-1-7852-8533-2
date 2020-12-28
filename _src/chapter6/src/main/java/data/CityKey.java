package data;

import com.hazelcast.core.PartitionAware;

import java.io.Serializable;

public class CityKey implements PartitionAware, Serializable {
  private String name;
  private String country;

  public CityKey(String name, String country) {
    this.name = name;
    this.country = country;
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

  @Override
  public Object getPartitionKey() {
    return country;
  }

  @Override
  public String toString() {
    return String.format(
      "CityKey{name='%s', country='%s'}",
      name, country);
  }
}
