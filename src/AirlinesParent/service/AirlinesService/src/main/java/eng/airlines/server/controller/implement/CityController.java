package eng.airlines.server.controller.implement;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import eng.airlines.model.interfaces.CityModelInterface;
import eng.airlines.server.ControllerPath;
import eng.airlines.server.controller.interfaces.CityControllerInterface;
import eng.airlines.server.error.PlaneServiceErrorCodes;
import eng.airlines.server.error.PlaneServiceException;
import eng.airlines.server.model.ErrorResponse;
import eng.airlines.server.model.Response;
import eng.airlines.server.processor.interfaces.CityServiceProcessorInterface;
import eng.airlines.server.validator.PlaneServiceValidatorInterface;

@Controller
@RequestMapping(ControllerPath.BASE_PATH)
public class CityController implements CityControllerInterface {

	private static Logger logger = LogManager.getLogger(CityController.class);

	@Autowired
	private PlaneServiceValidatorInterface validator;

	@Autowired
	private CityServiceProcessorInterface processor;

	@RequestMapping(value = ControllerPath.CITY_PATH, method = RequestMethod.GET, produces = { "application/json" })
	public ResponseEntity<List<CityModelInterface>> findAllCity(

			// Header

			// Path

			) throws PlaneServiceException, Exception {

				logger.info("Call processor's findAllCity method.");
				List<CityModelInterface> resp = processor.findAllCity();

		logger.debug("Return result: " + resp);
		return new ResponseEntity<>(resp, HttpStatus.OK);

	}

	@RequestMapping(value = ControllerPath.CITY_PATH + "/{id}", method = RequestMethod.GET, produces = { "application/json" })
	public ResponseEntity<CityModelInterface> findCityById(

			// Header

			// Path
			@PathVariable("id") Long id

	) throws PlaneServiceException, Exception {

		logger.info("Call processor's findCityById method.");
		CityModelInterface resp = processor.findCityById(id);

		logger.debug("Return result: " + resp);
		return new ResponseEntity<>(resp, HttpStatus.OK);

	}

	@RequestMapping(value = ControllerPath.CITY_PATH, method = RequestMethod.POST, produces = { "application/json" })
	public ResponseEntity<CityModelInterface> saveCity(

			// Header

			// Path

			// Body
			@RequestBody CityModelInterface city

	) throws PlaneServiceException, Exception {

		logger.info("Call processor's saveCity method with city: " + city);
		CityModelInterface resp = processor.saveCity(city);

		logger.debug("Return result: " + resp);
		return new ResponseEntity<>(resp, HttpStatus.CREATED);

	}

	@RequestMapping(value = ControllerPath.CITY_PATH + "/{id}", method = RequestMethod.DELETE, produces = { "application/json" })
	public ResponseEntity<Response> deleteCity(
			// Header

			// Path
			@PathVariable("id") Long id

	) throws PlaneServiceException, Exception {

		logger.info("Call processor's deleteCityById method.");
		Boolean isSuccesDelete = processor.deleteCityById(id);

		if (!isSuccesDelete) {
			logger.error("Unhandler error under deleteCityById.");
			throw new PlaneServiceException(PlaneServiceErrorCodes.CITY_UNHNADLED_DELETE_ERROR);
		}

		logger.debug("Delete result was succes. ");
		return new ResponseEntity<Response>(createOkREsponse(), HttpStatus.OK);

	}

	@RequestMapping(value = ControllerPath.UPLOAD_PATH + ControllerPath.CITY_PATH, method = RequestMethod.POST, produces = { "application/json" },

			consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<Response> uploadCity(
			// Header

			// Path
			@RequestParam("city_file") MultipartFile city_file

	) throws PlaneServiceException, Exception {

		logger.info("Call processor's uploadcities method.");
		Boolean isSuccesUpload = processor.uploadCities(city_file);

		if (!isSuccesUpload) {
			logger.error("Unhandler error under uploadCities.");
			throw new PlaneServiceException(PlaneServiceErrorCodes.CITY_UNHNADLED_UPLOAD_ERROR);
		}


		logger.debug("Upload result was succes. ");
		return new ResponseEntity<Response>(createOkREsponse(), HttpStatus.OK);

	}

	private Response createOkREsponse() {
		Response response = new Response();

		response.setDetail("Success.");
		response.setStatus(200);
		response.setTitle("Success");

		return response;
	}

	@ExceptionHandler(PlaneServiceException.class)
	private ResponseEntity<ErrorResponse> adminApiExceptionCatcher(PlaneServiceException e) {

		logger.error("AdminApiException exception: " + e.getErrorCode() + " - " + e.getMessage());
		ErrorResponse errorResponse = new ErrorResponse(String.valueOf(e.getErrorCode()), e.getDescription(), e.getMessage());

		logger.error("Send error response.");
		return new ResponseEntity<>(errorResponse, e.getHttpStatus());
	}

	@ExceptionHandler(Exception.class)
	private ResponseEntity<ErrorResponse> adminApiExceptionCatcher(Exception e) {

		logger.error("Unhandled error. Message: ", e);
		PlaneServiceErrorCodes internalServerError = PlaneServiceErrorCodes.INTERNAL_SERVER_ERROR;

		ErrorResponse errorResponse = new ErrorResponse(String.valueOf(internalServerError.getCode()), internalServerError.getDescription(), internalServerError.getDetails());

		logger.error("Send error response.");
		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
