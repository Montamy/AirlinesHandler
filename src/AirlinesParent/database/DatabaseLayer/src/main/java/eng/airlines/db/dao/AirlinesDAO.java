package eng.airlines.db.dao;

import org.springframework.data.repository.CrudRepository;

import eng.airlines.db.dto.AirlinesDto;

public interface AirlinesDAO extends CrudRepository<AirlinesDto, Long> {

}
