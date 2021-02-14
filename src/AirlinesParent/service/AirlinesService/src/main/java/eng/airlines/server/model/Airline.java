package eng.airlines.server.model;

import eng.airlines.model.implement.BaseDbModel;
import eng.airlines.model.interfaces.AirlineModelInterface;

public class Airline extends BaseDbModel implements AirlineModelInterface {

	private String name;

	public Airline() {

	}

	public Airline(Long id, String name) {
		super(id);
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "[ id: " + id + " ,name: " + name + " ]";
	}

}
