package eng.airlines.db.interfaces;

import java.util.List;

import eng.airlines.model.interfaces.FlightModelInterface;

public interface FlightDbInterface extends BaseDbInterface<FlightModelInterface> {

	List<FlightModelInterface> findAirlinesFlightsByAirlineId(Long id);

	List<FlightModelInterface> findFlightsBetweenCities(Long city1, Long city2);

}
