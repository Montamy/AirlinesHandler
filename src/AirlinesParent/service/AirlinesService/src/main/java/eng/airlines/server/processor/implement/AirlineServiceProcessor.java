package eng.airlines.server.processor.implement;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eng.airlines.db.interfaces.AirlinesDbInterface;
import eng.airlines.model.interfaces.AirlineModelInterface;
import eng.airlines.server.error.PlaneServiceErrorCodes;
import eng.airlines.server.error.PlaneServiceException;
import eng.airlines.server.model.Airline;
import eng.airlines.server.processor.interfaces.AirlineServiceProcessorInterface;

@Service
public class AirlineServiceProcessor implements AirlineServiceProcessorInterface {

	private static Logger logger = LogManager.getLogger(AirlineServiceProcessor.class);

	@Autowired
	private AirlinesDbInterface airlinesDbInterface;

	DozerBeanMapper mapper;

	public AirlineServiceProcessor() {
		this.mapper = new DozerBeanMapper();
	}

	@Override
	public List<AirlineModelInterface> findAllAirline() throws PlaneServiceException {
		logger.info("Airline service processor findAllAirline method get the request.");

		logger.info("Get all airline fromdatabase layer.");
		List<AirlineModelInterface> allAirline = airlinesDbInterface
				.findAllAirline()
				.stream()
				.map(x-> mapper.map(x, Airline.class))
				.collect(Collectors.toList());

		logger.info("Return result airline list.");
		return allAirline;
	}

	@Override
	public AirlineModelInterface findAirlineById(Long id) throws PlaneServiceException {
		logger.info("Airline service processor findAirlineById method get the request, request id: " + id);

		logger.info("Get airline by id.");
		AirlineModelInterface dbObject = airlinesDbInterface.findAirlineById(id);

		if (dbObject == null) {
			logger.error("Selected airline object not exist.");
			throw new PlaneServiceException(PlaneServiceErrorCodes.AIRLINE_REQUESTED_GET_OBJECT_NOT_EXIST);
		}

		AirlineModelInterface selectedAirline = mapper.map(dbObject, Airline.class);

		logger.info("Return selected airline.");
		return selectedAirline;
	}

	@Override
	public AirlineModelInterface saveAirline(AirlineModelInterface airline) throws PlaneServiceException {
		logger.info("Airline service processor saveAirline method get the request, request save object: " + airline);

		logger.info("Save airline.");
		AirlineModelInterface savedAirline = mapper.map(airlinesDbInterface.saveAirline(airline), Airline.class);

		logger.info("Return saved airline.");
		return savedAirline;
	}

	@Override
	public Boolean deleteAirlineById(Long id) throws PlaneServiceException {
		logger.info("Airline service processor deleteAirlineById method get the request, request id: " + id);

		logger.info("Delete airline by id.");
		Boolean isSuccesDelete = airlinesDbInterface.deleteAirlineById(id);

		if (isSuccesDelete == null) {
			logger.error("Error under delete airline.");
			throw new PlaneServiceException(PlaneServiceErrorCodes.AIRLINE_UNKNOWN_DELETE_ERROR);
		} else if (isSuccesDelete == false) {
			logger.error("Requested delete airline object not exist.");
			throw new PlaneServiceException(PlaneServiceErrorCodes.AIRLINE_REQUESTED_DELETE_OBJECT_NOT_EXIST);
		}

		logger.info("Delete was success");
		return true;
	}

}
