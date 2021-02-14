package eng.airlines.server.processor.implement;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eng.airlines.db.interfaces.AirlinesDbInterface;
import eng.airlines.model.interfaces.AirlineModelInterface;
import eng.airlines.server.error.PlaneServiceException;
import eng.airlines.server.model.Airline;
import eng.airlines.server.processor.interfaces.AirlineServiceProcessorInterface;

@Service
public class AirlineServiceProcessor implements AirlineServiceProcessorInterface {

	private static Logger logger = LogManager.getLogger(AirlineServiceProcessor.class);

	@Autowired
	private AirlinesDbInterface airlinesDbInterface;

	@Override
	public List<AirlineModelInterface> findAllAirline() throws PlaneServiceException {
		logger.info("Airline service processor findAllAirline method get the request.");

		logger.info("Get all airline fromdatabase layer.");
		List<AirlineModelInterface> allAirline = airlinesDbInterface.findAllAirline();

		logger.info("Return result airline list.");
		return allAirline;
	}

	@Override
	public Airline findAirlineById(Long id) throws PlaneServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Airline saveAirline(Airline airline) throws PlaneServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean deleteAirlineById(Long id) throws PlaneServiceException {
		// TODO Auto-generated method stub
		return null;
	}

}
