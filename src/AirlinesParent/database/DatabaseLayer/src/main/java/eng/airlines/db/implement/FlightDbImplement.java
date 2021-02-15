package eng.airlines.db.implement;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import eng.airlines.db.dao.FlightDAO;
import eng.airlines.db.dto.FlightDto;
import eng.airlines.db.interfaces.FlightDbInterface;
import eng.airlines.model.interfaces.FlightModelInterface;

@Service
public class FlightDbImplement implements FlightDbInterface {


	@Autowired
	private FlightDAO flightsDAO;

	DozerBeanMapper mapper;

	public FlightDbImplement() {
		this.mapper = new DozerBeanMapper();
	}

	@Override
	public List<? extends FlightModelInterface> findAll() {

		List<FlightDto> flights = new ArrayList<FlightDto>();
		flightsDAO.findAll().forEach(flights::add);

		return flights;

	}

	@Override
	public FlightModelInterface findById(Long id) {

		Optional<FlightDto> flightsObject = flightsDAO.findById(id);
		if (flightsObject.isPresent()) {
			return flightsObject.get();
		}

		return null;
	}

	@Override
	public FlightModelInterface save(FlightModelInterface flight) {

		try {
			FlightDto dbObject = mapper.map(flight, FlightDto.class);
			return flightsDAO.save(dbObject);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

	/*
	 * Return true if deleted, Return false if not deleted, Return null if error
	 */
	@Override
	public Boolean deleteById(Long id) {
		try {
			flightsDAO.deleteById(id);
			return true;
		} catch (EmptyResultDataAccessException emptyException) {
			return false;
		} catch (Exception e) {
			return null;
		}
	}

}
