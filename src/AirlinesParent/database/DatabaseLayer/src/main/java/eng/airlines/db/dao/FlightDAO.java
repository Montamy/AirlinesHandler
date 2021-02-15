package eng.airlines.db.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import eng.airlines.db.dto.FlightDto;
import eng.airlines.model.interfaces.FlightModelInterface;

public interface FlightDAO extends CrudRepository<FlightDto, Long> {

	@Query("select flight from #{#entityName} flight "	
			+ "join fetch flight.airline airline "
			+ "join fetch flight.sourceCity sourceCity "
			+ "join fetch flight.destinationCity destinationCity "
			+ "where airline.id= :id ")
	public List<FlightModelInterface> findByAirlineId(Long id);

	@Query("select flight from #{#entityName} flight "	
			+ "join fetch flight.airline airline "
			+ "join fetch flight.sourceCity sourceCity "
			+ "join fetch flight.destinationCity destinationCity "
			+ "where (sourceCity.id= :city1 and destinationCity.id= :city2) or (sourceCity.id= :city2 and destinationCity.id= :city1)")
	public List<FlightModelInterface> findFlightsBetweenCities(Long city1, Long city2);

}
