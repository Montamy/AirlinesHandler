package eng.airlines.db.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import eng.airlines.db.dto.CityDto;
import eng.airlines.model.interfaces.CityModelInterface;

public interface CityDAO extends CrudRepository<CityDto, Long> {

	@Query("select city from #{#entityName} city " + "where city.name= :name ")
	public CityModelInterface findByName(String name);
}
