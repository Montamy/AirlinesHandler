package eng.airlines.db.dao;

import org.springframework.data.repository.CrudRepository;

import eng.airlines.db.dto.FlightDto;

public interface FlightDAO extends CrudRepository<FlightDto, Long> {

}
