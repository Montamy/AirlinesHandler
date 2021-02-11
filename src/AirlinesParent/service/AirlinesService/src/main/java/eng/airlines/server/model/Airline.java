package eng.airlines.server.model;

import eng.airlines.interfaces.AirlineModelInterface;

public class Airline implements AirlineModelInterface {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
