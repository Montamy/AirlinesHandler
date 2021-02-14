package eng.airlines.server.processor.interfaces;

import java.util.List;

import eng.airlines.model.interfaces.AirlineModelInterface;
import eng.airlines.server.error.PlaneServiceException;

public interface AirlineServiceProcessorInterface {

	public List<AirlineModelInterface> findAllAirline() throws PlaneServiceException;

	public AirlineModelInterface findAirlineById(Long id) throws PlaneServiceException;

	public AirlineModelInterface saveAirline(AirlineModelInterface airline) throws PlaneServiceException;

	public Boolean deleteAirlineById(Long id) throws PlaneServiceException;

}
