package eng.airlines.server.model;

import eng.airlines.interfaces.CityModelInterface;

public class City implements CityModelInterface {

	private String name;
	private Long population;

	public String getName() {
		return name;
	}

	public Long getPopulation() {
		return population;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPopulation(Long population) {
		this.population = population;
	}


}
