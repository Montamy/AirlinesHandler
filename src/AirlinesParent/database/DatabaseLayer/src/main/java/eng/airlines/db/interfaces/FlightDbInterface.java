package eng.airlines.db.interfaces;

import java.util.List;

import eng.airlines.model.interfaces.FlightModelInterface;

public interface FlightDbInterface {

	List<? extends FlightModelInterface> findAllFlight();

	FlightModelInterface findFlightById(Long id);

	FlightModelInterface saveFlight(FlightModelInterface flight);

	Boolean deleteFlightById(Long id);
}
