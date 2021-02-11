package eng.plane.server.processor;

import java.util.List;

import eng.plane.server.error.PlaneServiceException;
import eng.plane.server.model.Airline;

public interface PlaneServiceProcessor {

	public List<Airline> findAllAirline() throws PlaneServiceException;

}
