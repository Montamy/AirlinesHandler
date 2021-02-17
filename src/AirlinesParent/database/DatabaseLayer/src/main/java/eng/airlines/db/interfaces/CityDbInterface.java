package eng.airlines.db.interfaces;

import eng.airlines.model.interfaces.CityModelInterface;

public interface CityDbInterface extends BaseDbInterface<CityModelInterface> {

	CityModelInterface findByName(String name);
}
