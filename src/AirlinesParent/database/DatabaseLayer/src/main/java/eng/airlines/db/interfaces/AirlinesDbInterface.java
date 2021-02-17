package eng.airlines.db.interfaces;

import eng.airlines.model.interfaces.AirlineModelInterface;

public interface AirlinesDbInterface extends BaseDbInterface<AirlineModelInterface> {

	AirlineModelInterface findByName(String name);
}
