package eng.airlines.server.controller.implement;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import eng.airlines.model.interfaces.FlightModelInterface;
import eng.airlines.server.ControllerPath;
import eng.airlines.server.controller.interfaces.FlightControllerInterface;
import eng.airlines.server.error.PlaneServiceErrorCodes;
import eng.airlines.server.error.PlaneServiceException;
import eng.airlines.server.model.ErrorResponse;
import eng.airlines.server.model.Response;
import eng.airlines.server.processor.interfaces.FlightServiceProcessorInterface;
import eng.airlines.server.validator.PlaneServiceValidatorInterface;

@Controller
@RequestMapping(ControllerPath.BASE_PATH)
public class FlightController implements FlightControllerInterface {

	public static final String FLIGHT_PATH = "/flight";

	private static Logger logger = LogManager.getLogger(FlightController.class);

	@Autowired
	private PlaneServiceValidatorInterface validator;

	@Autowired
	private FlightServiceProcessorInterface processor;

	@RequestMapping(value = FLIGHT_PATH, method = RequestMethod.GET, produces = { "application/json" })
	public ResponseEntity<List<FlightModelInterface>> findAllFlight(

			// Header

			// Path

			) throws PlaneServiceException, Exception {

				logger.info("Call processor's findAllFlight method.");
				List<FlightModelInterface> resp = processor.findAllFlight();

		logger.debug("Return result: " + resp);
		return new ResponseEntity<>(resp, HttpStatus.OK);

	}

	@RequestMapping(value = FLIGHT_PATH + "/{id}", method = RequestMethod.GET, produces = { "application/json" })
	public ResponseEntity<FlightModelInterface> findFlightById(

			// Header

			// Path
			@PathVariable("id") Long id

	) throws PlaneServiceException, Exception {

		logger.info("Call processor's findFlightById method.");
		FlightModelInterface resp = processor.findFlightById(id);

		logger.debug("Return result: " + resp);
		return new ResponseEntity<>(resp, HttpStatus.OK);

	}

	@RequestMapping(value = FLIGHT_PATH, method = RequestMethod.POST, produces = { "application/json" })
	public ResponseEntity<FlightModelInterface> saveFlight(

			// Header

			// Path

			// Body
			@RequestBody FlightModelInterface flight

	) throws PlaneServiceException, Exception {

		logger.info("Call processor's saveFlight method with flight: " + flight);
		FlightModelInterface resp = processor.saveFlight(flight);

		logger.debug("Return result: " + resp);
		return new ResponseEntity<>(resp, HttpStatus.OK);

	}

	@RequestMapping(value = FLIGHT_PATH + "/{id}", method = RequestMethod.DELETE, produces = { "application/json" })
	public ResponseEntity<Response> deleteFlight(
			// Header

			// Path
			@PathVariable("id") Long id

	) throws PlaneServiceException, Exception {

		logger.info("Call processor's deleteFlightById method.");
		Boolean isSuccesDelete = processor.deleteFlightById(id);

		if (!isSuccesDelete) {
			logger.error("Unhandler error under deleteFlightById.");
			throw new PlaneServiceException(PlaneServiceErrorCodes.FLIGHT_UNHNADLED_DELETE_ERROR);
		}

		logger.debug("Delete result was succes. ");
		return new ResponseEntity<Response>(createOkREsponse(), HttpStatus.OK); // TODO

	}

	private Response createOkREsponse() {
		Response response = new Response();

		response.setDetail("Success delete.");
		response.setStatus(200);
		response.setTitle("Success delete.");

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
