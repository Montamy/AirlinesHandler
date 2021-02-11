package eng.airlines.server.processor;

import java.util.List;

import eng.airlines.server.error.PlaneServiceException;
import eng.airlines.server.model.Airline;

public interface PlaneServiceProcessor {

	public List<Airline> findAllAirline() throws PlaneServiceException;

}
