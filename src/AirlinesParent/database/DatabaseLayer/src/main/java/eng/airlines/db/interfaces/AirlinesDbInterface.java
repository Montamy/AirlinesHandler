package eng.airlines.db.interfaces;

import java.util.List;

import eng.airlines.model.interfaces.AirlineModelInterface;

public interface AirlinesDbInterface {

	List<AirlineModelInterface> findAllAirline();

	AirlineModelInterface findAirlineById(Long id);

	AirlineModelInterface saveAirline(AirlineModelInterface airline);

	Boolean deleteAirlineById(Long id);
}
