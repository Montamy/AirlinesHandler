package eng.plane.server.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import eng.plane.server.ControllerPath;
import eng.plane.server.error.ErrorResponse;
import eng.plane.server.error.PlaneServiceErrorCodes;
import eng.plane.server.error.PlaneServiceException;
import eng.plane.server.model.Airline;
import eng.plane.server.processor.PlaneServiceProcessor;
import eng.plane.server.validator.PlaneServiceValidatorInterface;

@Controller
@RequestMapping(ControllerPath.BASE_PATH)
public class AirlineController {

	public static final String AIRLINE_PATH = "/airline";

	private static Logger logger = LogManager.getLogger(AirlineController.class);

	@Autowired
	private PlaneServiceValidatorInterface validator;

	@Autowired
	private PlaneServiceProcessor processor;

	@RequestMapping(value = AIRLINE_PATH, method = RequestMethod.GET, produces = { "application/json" })
	public ResponseEntity<List<Airline>> findAllAirline(

			// Header

			// PATH

			) throws PlaneServiceException, Exception {

		logger.info("Validating findAllAirline request. ");
		// TODO validator.checkRequest();


		logger.info("Call processor's findAllAirline method.");
		List<Airline> resp = processor.findAllAirline();

		logger.debug("Return result: " + resp);

		return new ResponseEntity<>(resp, HttpStatus.OK);

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
