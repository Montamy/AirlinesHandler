package eng.airlines.server.processor.implement;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eng.airlines.db.interfaces.FlightDbInterface;
import eng.airlines.model.interfaces.FlightModelInterface;
import eng.airlines.server.error.PlaneServiceErrorCodes;
import eng.airlines.server.error.PlaneServiceException;
import eng.airlines.server.processor.interfaces.FlightServiceProcessorInterface;

@Service
public class FlightServiceProcessor implements FlightServiceProcessorInterface {

	private static Logger logger = LogManager.getLogger(FlightServiceProcessor.class);

	@Autowired
	private FlightDbInterface flightsDbInterface;

	DozerBeanMapper mapper;

	public FlightServiceProcessor() {
		this.mapper = new DozerBeanMapper();
	}

	@Override
	public List<FlightModelInterface> findAllFlight() throws PlaneServiceException {
		logger.info("Flight service processor findAllFlight method get the request.");

		logger.info("Get all flight fromdatabase layer.");
		List<FlightModelInterface> allFlight = flightsDbInterface.findAllFlight()
				.stream()
				// TODO working map .map(x -> mapper.map(x, Flight.class))
				.collect(Collectors.toList());

		logger.info("Return result flight list.");
		return allFlight;
	}

	@Override
	public FlightModelInterface findFlightById(Long id) throws PlaneServiceException {
		logger.info("Flight service processor findFlightById method get the request, request id: " + id);

		logger.info("Get flight by id.");
		FlightModelInterface dbObject = flightsDbInterface.findFlightById(id);

		if (dbObject == null) {
			logger.error("Selected flight object not exist.");
			throw new PlaneServiceException(PlaneServiceErrorCodes.AIRLINE_REQUESTED_GET_OBJECT_NOT_EXIST);
		}

		FlightModelInterface selectedFlight = dbObject; // TODO working mapper mapper.map(dbObject, Flight.class);

		logger.info("Return selected flight.");
		return selectedFlight;
	}

	@Override
	public FlightModelInterface saveFlight(FlightModelInterface flight) throws PlaneServiceException {

		try {
			logger.info("Flight service processor saveFlight method get the request, request save object: " + flight);

			logger.info("Save flight.");
			FlightModelInterface savedFlight = flightsDbInterface.saveFlight(flight);
			// TODO working mapping mapper.map(flightsDbInterface.saveFlight(flight),
			// Flight.class);

			logger.info("Return saved flight.");
			return savedFlight;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public Boolean deleteFlightById(Long id) throws PlaneServiceException {
		logger.info("Flight service processor deleteFlightById method get the request, request id: " + id);

		logger.info("Delete flight by id.");
		Boolean isSuccesDelete = flightsDbInterface.deleteFlightById(id);

		if (isSuccesDelete == null) {
			logger.error("Error under delete flight.");
			throw new PlaneServiceException(PlaneServiceErrorCodes.FLIGHT_UNKNOWN_DELETE_ERROR);
		} else if (isSuccesDelete == false) {
			logger.error("Requested delete flight object not exist.");
			throw new PlaneServiceException(PlaneServiceErrorCodes.FLIGHT_REQUESTED_DELETE_OBJECT_NOT_EXIST);
		}

		logger.info("Delete was success");
		return true;

	}

}
