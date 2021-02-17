package eng.airlines.db.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import eng.airlines.db.dto.AirlinesDto;
import eng.airlines.model.interfaces.AirlineModelInterface;

public interface AirlinesDAO extends CrudRepository<AirlinesDto, Long> {

	@Query("select airline from #{#entityName} airline " + "where airline.name= :name ")
	public AirlineModelInterface findByName(String name);

}
