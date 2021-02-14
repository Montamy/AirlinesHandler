package eng.airlines.server.model;

import eng.airlines.model.implement.BaseDbModel;
import eng.airlines.model.interfaces.CityModelInterface;

public class City extends BaseDbModel implements CityModelInterface {

	private String name;
	private Long population;

	public City(Long id, String name, Long population) {
		super(id);
		this.name = name;
		this.population = population;
	}

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

	@Override
	public String toString() {
		return "[ id: " + id + " ,name: " + name + " ,population: " + population + " ]";
	}


}
