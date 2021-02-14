package eng.airlines.server.processor.interfaces;

import java.util.List;

import eng.airlines.model.interfaces.FlightModelInterface;
import eng.airlines.server.error.PlaneServiceException;

public interface FlightServiceProcessorInterface {

	public List<FlightModelInterface> findAllFlight() throws PlaneServiceException;

	public FlightModelInterface findFlightById(Long id) throws PlaneServiceException;

	public FlightModelInterface saveFlight(FlightModelInterface flight) throws PlaneServiceException;

	public Boolean deleteFlightById(Long id) throws PlaneServiceException;

}
