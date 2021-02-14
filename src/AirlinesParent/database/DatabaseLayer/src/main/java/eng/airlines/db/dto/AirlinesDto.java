package eng.airlines.db.dto;

import eng.airlines.model.interfaces.AirlineModelInterface;

public class AirlinesDto implements AirlineModelInterface {

	private Long id;
	private String name;

	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}



}
