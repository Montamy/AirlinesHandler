package eng.airlines.server.controller.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import eng.airlines.model.interfaces.FlightModelInterface;
import eng.airlines.server.error.PlaneServiceException;
import eng.airlines.server.model.Response;

public interface FlightControllerInterface {

	public ResponseEntity<List<FlightModelInterface>> findAllFlight() throws PlaneServiceException, Exception;

	public ResponseEntity<FlightModelInterface> findFlightById(@PathVariable("id") Long id) throws PlaneServiceException, Exception;

	public ResponseEntity<FlightModelInterface> saveFlight(@RequestBody FlightModelInterface flight) throws PlaneServiceException, Exception;

	public ResponseEntity<Response> deleteFlight(@PathVariable("id") Long id) throws PlaneServiceException, Exception;

	public ResponseEntity<Response> uploadFlight(@RequestParam("flight_file") MultipartFile flight_file) throws PlaneServiceException, Exception;
	
	public ResponseEntity<List<FlightModelInterface>> findFlightsBetweenCities(@RequestParam(value = "cityId1", required = true) Long city1, @RequestParam(value = "cityId2", required = true) Long city2) throws PlaneServiceException, Exception;

	public ResponseEntity<List<FlightModelInterface>> shortestPathWithDijkstra(@RequestParam(value = "cityId1", required = true) Long cityId1,@RequestParam(value = "cityId2", required = true) Long cityId2) throws PlaneServiceException, Exception;
}
