package eng.airlines.server.processor;

import java.util.List;

import eng.airlines.server.error.PlaneServiceException;
import eng.airlines.server.model.Airline;

public interface PlaneServiceProcessor {

	public List<Airline> findAllAirline() throws PlaneServiceException;

	public Airline findAirlineById(Long id) throws PlaneServiceException;

	public Airline saveAirline(Airline airline) throws PlaneServiceException;

	public Boolean deleteAirlineById(Long id) throws PlaneServiceException;

}
