package eng.airlines.server.processor.interfaces;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import eng.airlines.model.interfaces.CityModelInterface;
import eng.airlines.server.error.PlaneServiceException;

public interface CityServiceProcessorInterface {

	public List<CityModelInterface> findAllCity() throws PlaneServiceException;

	public CityModelInterface findCityById(Long id) throws PlaneServiceException;

	public CityModelInterface saveCity(CityModelInterface city) throws PlaneServiceException;

	public Boolean deleteCityById(Long id) throws PlaneServiceException;

	public Boolean uploadCities(MultipartFile city_file) throws PlaneServiceException;

}
