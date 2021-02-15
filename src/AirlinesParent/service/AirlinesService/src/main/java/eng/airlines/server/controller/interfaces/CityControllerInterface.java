package eng.airlines.server.controller.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import eng.airlines.model.interfaces.CityModelInterface;
import eng.airlines.server.error.PlaneServiceException;
import eng.airlines.server.model.Response;

public interface CityControllerInterface {

	public ResponseEntity<List<CityModelInterface>> findAllCity() throws PlaneServiceException, Exception;

	public ResponseEntity<CityModelInterface> findCityById(@PathVariable("id") Long id) throws PlaneServiceException, Exception;

	public ResponseEntity<CityModelInterface> saveCity(@RequestBody CityModelInterface city) throws PlaneServiceException, Exception;

	public ResponseEntity<Response> deleteCity(@PathVariable("id") Long id) throws PlaneServiceException, Exception;

	public ResponseEntity<Response> uploadCity(@RequestParam("city_file") MultipartFile city_file) throws PlaneServiceException, Exception;

}
