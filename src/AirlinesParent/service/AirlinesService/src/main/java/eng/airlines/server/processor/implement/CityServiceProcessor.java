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

import eng.airlines.db.interfaces.CityDbInterface;
import eng.airlines.model.interfaces.CityModelInterface;
import eng.airlines.server.error.PlaneServiceErrorCodes;
import eng.airlines.server.error.PlaneServiceException;
import eng.airlines.server.model.City;
import eng.airlines.server.processor.interfaces.CityServiceProcessorInterface;

@Service
public class CityServiceProcessor implements CityServiceProcessorInterface {

	private static Logger logger = LogManager.getLogger(CityServiceProcessor.class);

	private static String FILE_CITY_PROPERTY_SEPARATOR = ";";

	@Autowired
	private CityDbInterface citysDbInterface;

	DozerBeanMapper mapper;

	public CityServiceProcessor() {
		this.mapper = new DozerBeanMapper();
	}

	@Override
	public List<CityModelInterface> findAllCity() throws PlaneServiceException {
		logger.info("City service processor findAllCity method get the request.");

		logger.info("Get all city fromdatabase layer.");
		List<CityModelInterface> allCity = citysDbInterface.findAllCity()
				.stream()
				.map(x -> mapper.map(x, City.class))
				.collect(Collectors.toList());

		logger.info("Return result city list.");
		return allCity;
	}

	@Override
	public CityModelInterface findCityById(Long id) throws PlaneServiceException {
		logger.info("City service processor findCityById method get the request, request id: " + id);

		logger.info("Get city by id.");
		CityModelInterface dbObject = citysDbInterface.findCityById(id);

		if (dbObject == null) {
			logger.error("Selected city object not exist.");
			throw new PlaneServiceException(PlaneServiceErrorCodes.AIRLINE_REQUESTED_GET_OBJECT_NOT_EXIST);
		}

		CityModelInterface selectedCity = mapper.map(dbObject, City.class);

		logger.info("Return selected city.");
		return selectedCity;
	}

	@Override
	public CityModelInterface saveCity(CityModelInterface city) throws PlaneServiceException {

		try {
			logger.info("City service processor saveCity method get the request, request save object: " + city);

			logger.info("Save city.");
			CityModelInterface savedCity = mapper.map(citysDbInterface.saveCity(city), City.class);

			logger.info("Return saved city.");
			return savedCity;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public Boolean deleteCityById(Long id) throws PlaneServiceException {
		logger.info("City service processor deleteCityById method get the request, request id: " + id);

		logger.info("Delete city by id.");
		Boolean isSuccesDelete = citysDbInterface.deleteCityById(id);

		if (isSuccesDelete == null) {
			logger.error("Error under delete city.");
			throw new PlaneServiceException(PlaneServiceErrorCodes.CITY_UNKNOWN_DELETE_ERROR);
		} else if (isSuccesDelete == false) {
			logger.error("Requested delete city object not exist.");
			throw new PlaneServiceException(PlaneServiceErrorCodes.CITY_REQUESTED_DELETE_OBJECT_NOT_EXIST);
		}

		logger.info("Delete was success");
		return true;

	}

	@Transactional
	@Override
	public Boolean uploadCities(MultipartFile city_file) throws PlaneServiceException {
		try {
			ByteArrayInputStream bis = new ByteArrayInputStream(city_file.getBytes());
			String line = null;

			try (BufferedReader br = new BufferedReader(new InputStreamReader(bis))) {
				while ((line = br.readLine()) != null) {
					saveCity(createCityFromLine(line)); // szebb lenne csinálni egy city listet és egybe menteni, idő hiányábane zt most
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

	private CityModelInterface createCityFromLine(String line) {
		String[] splitedLine = line.split(FILE_CITY_PROPERTY_SEPARATOR);
		return new City(null, splitedLine[0], Long.parseLong(splitedLine[1]));
	}

}
