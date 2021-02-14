package eng.airlines.server.controller.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import eng.airlines.server.error.PlaneServiceException;
import eng.airlines.server.model.Airline;
import eng.airlines.server.model.Response;

public interface AirlineControllerInterface {

	public ResponseEntity<List<Airline>> findAllAirline() throws PlaneServiceException, Exception;

	public ResponseEntity<Airline> findAirlineById(@PathVariable("id") Long id) throws PlaneServiceException, Exception;

	public ResponseEntity<Airline> saveAirline(@RequestBody Airline airline) throws PlaneServiceException, Exception;

	public ResponseEntity<Response> deleteAirline(@PathVariable("id") Long id) throws PlaneServiceException, Exception;
}
