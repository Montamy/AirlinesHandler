package eng.airlines.model.implement;

import eng.airlines.model.interfaces.DbStoredModelInterface;

public class BaseDbModel implements DbStoredModelInterface {

	private Long id;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}

}
