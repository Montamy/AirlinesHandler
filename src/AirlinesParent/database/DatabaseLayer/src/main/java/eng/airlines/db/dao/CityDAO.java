package eng.airlines.db.dao;

import org.springframework.data.repository.CrudRepository;

import eng.airlines.db.dto.CityDto;

public interface CityDAO extends CrudRepository<CityDto, Long> {

}
