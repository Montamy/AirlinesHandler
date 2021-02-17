package eng.airlines.server.processor.interfaces;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import eng.airlines.model.interfaces.FlightModelInterface;
import eng.airlines.server.error.PlaneServiceException;

public interface FlightServiceProcessorInterface {

	public List<FlightModelInterface> findAllFlight() throws PlaneServiceException;

	public FlightModelInterface findFlightById(Long id) throws PlaneServiceException;

	public FlightModelInterface saveFlight(FlightModelInterface flight) throws PlaneServiceException;

	public Boolean deleteFlightById(Long id) throws PlaneServiceException;

	public Boolean uploadFlights(MultipartFile flight_file) throws PlaneServiceException;

	public List<FlightModelInterface> findFlightsBetweenCities(Long city1, Long city2) throws PlaneServiceException;

}
