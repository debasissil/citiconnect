package com.mastercard.excercise.citiconnect.service;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * This class holds data of connected cities
 */
public class City {
	private String name;

	private Set<City> adjacentCities = new HashSet<>();

	public City() {
	}

	public City(String name) {
		 Objects.requireNonNull(name);
		this.name = name.trim().toUpperCase();
	}

	/*public City create(String name) {
		return new City(name);
	}*/

	@Override
	public String toString() {

		return "City{" + "name='" + name + "'" + ", adjacent='" + format() + "'}";
	}

	public String format() {
		return adjacentCities.stream().map(City::getName).collect(Collectors.joining(","));
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public City addAdjacentCities(City city) {
		adjacentCities.add(city);
		return this;
	}

	public Set<City> getAdjacentCities() {
		return adjacentCities;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		City other = (City) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
