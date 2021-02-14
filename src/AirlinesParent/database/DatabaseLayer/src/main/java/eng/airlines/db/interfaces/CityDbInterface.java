package eng.airlines.db.interfaces;

import java.util.List;

import eng.airlines.model.interfaces.CityModelInterface;

public interface CityDbInterface {

	List<CityModelInterface> findAllCity();

	CityModelInterface findCityById(Long id);

	CityModelInterface saveCity(CityModelInterface airline);

	Boolean deleteCityById(Long id);
}
