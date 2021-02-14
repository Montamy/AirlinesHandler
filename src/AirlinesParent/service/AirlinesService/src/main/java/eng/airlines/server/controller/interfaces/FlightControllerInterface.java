package eng.airlines.server.controller.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import eng.airlines.model.interfaces.FlightModelInterface;
import eng.airlines.server.error.PlaneServiceException;
import eng.airlines.server.model.Response;

public interface FlightControllerInterface {

	public ResponseEntity<List<FlightModelInterface>> findAllFlight() throws PlaneServiceException, Exception;

	public ResponseEntity<FlightModelInterface> findFlightById(@PathVariable("id") Long id) throws PlaneServiceException, Exception;

	public ResponseEntity<FlightModelInterface> saveFlight(@RequestBody FlightModelInterface flight) throws PlaneServiceException, Exception;

	public ResponseEntity<Response> deleteFlight(@PathVariable("id") Long id) throws PlaneServiceException, Exception;
}
