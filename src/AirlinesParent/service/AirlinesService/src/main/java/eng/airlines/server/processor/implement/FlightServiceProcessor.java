package eng.airlines.server.processor.implement;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import eng.airlines.db.interfaces.AirlinesDbInterface;
import eng.airlines.db.interfaces.CityDbInterface;
import eng.airlines.db.interfaces.FlightDbInterface;
import eng.airlines.model.interfaces.AirlineModelInterface;
import eng.airlines.model.interfaces.CityModelInterface;
import eng.airlines.model.interfaces.FlightModelInterface;
import eng.airlines.server.error.PlaneServiceErrorCodes;
import eng.airlines.server.error.PlaneServiceException;
import eng.airlines.server.model.Airline;
import eng.airlines.server.model.City;
import eng.airlines.server.model.Flight;
import eng.airlines.server.processor.interfaces.FlightServiceProcessorInterface;

@Service
public class FlightServiceProcessor implements FlightServiceProcessorInterface {

	private static Logger logger = LogManager.getLogger(FlightServiceProcessor.class);

	private static String FILE_FLIGHT_PROPERTY_SEPARATOR = ";";

	private static Long FILE_FLIGHT_UPLOAD_NOT_EXIST_CITY_POPULATION = -1L;

	@Autowired
	private FlightDbInterface flightsDbInterface;

	@Autowired
	private CityDbInterface citysDbInterface;

	@Autowired
	private AirlinesDbInterface airlinesDbInterface;

	DozerBeanMapper mapper;

	public FlightServiceProcessor() {
		this.mapper = new DozerBeanMapper();
	}

	@Override
	public List<FlightModelInterface> findAllFlight() throws PlaneServiceException {
		logger.info("Flight service processor findAllFlight method get the request.");

		logger.info("Get all flight fromdatabase layer.");
		List<FlightModelInterface> allFlight = flightsDbInterface.findAll()
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
		FlightModelInterface dbObject = flightsDbInterface.findById(id);

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
			FlightModelInterface savedFlight = flightsDbInterface.save(flight);
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
		Boolean isSuccesDelete = flightsDbInterface.deleteById(id);

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

	@Transactional
	@Override
	public Boolean uploadFlights(MultipartFile flights_file) throws PlaneServiceException {
		logger.info("Processor upload flights get request.");
		try {
			ByteArrayInputStream bis = new ByteArrayInputStream(flights_file.getBytes());
			String line = null;

			try (BufferedReader br = new BufferedReader(new InputStreamReader(bis))) {
				while ((line = br.readLine()) != null) {
					logger.info("Save flight with read line: " + line);
					saveFlight(createFlightFromLine(line)); // szebb lenne csinálni egy city listet és egybe menteni, idő hiányábane zt most
														// nem teszem bele
				}
			} catch (Exception e) {
				logger.error("Error under read input file.");
				throw new PlaneServiceException(PlaneServiceErrorCodes.CITY_UPLOAD_ERROR_UNDER_READ_INPUT_FILE);
			}
		} catch (IOException ioe) {
			logger.error("Error under read input file.");
			throw new PlaneServiceException(PlaneServiceErrorCodes.CITY_UPLOAD_ERROR_UNDER_READ_INPUT_FILE);
		}
		return true;
	}

	private FlightModelInterface createFlightFromLine(String line) {
		String[] splitedLine = line.split(FILE_FLIGHT_PROPERTY_SEPARATOR);

		FlightModelInterface flight = new Flight(null, getAirlinebyName(splitedLine[0]), getCityByName(splitedLine[1]), getCityByName(splitedLine[2]),
				Integer.valueOf(splitedLine[3]), Integer.valueOf(splitedLine[4]));

		return flight;

	}

	private AirlineModelInterface getAirlinebyName(String name) {
		logger.info("Get or save airline with name: " + name);
		AirlineModelInterface airline = airlinesDbInterface.findByName(name);

		if (airline == null) {
			logger.info("airline not exist, save new one.");
			airline = airlinesDbInterface.save(new Airline(null, name)); // TODO itt dobhatnék hibát is, hogy nem létezik
			logger.info("Saved new airline: " + airline);
		}
		return airline;
	}

	private CityModelInterface getCityByName(String name) {
		logger.info("Get or save city with name: " + name);
		CityModelInterface city = citysDbInterface.findByName(name);

		if (city == null) {
			logger.info("City not exist, save new one.");
			city = citysDbInterface.save(new City(null, name, FILE_FLIGHT_UPLOAD_NOT_EXIST_CITY_POPULATION)); // TODO itt dobhatnék hibát is, hogy nem létezik
			logger.info("Saved new city: " + city);
		}
		return city;
	}

}
