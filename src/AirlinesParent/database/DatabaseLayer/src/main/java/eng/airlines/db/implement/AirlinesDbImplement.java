package eng.airlines.db.implement;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import eng.airlines.db.dao.AirlinesDAO;
import eng.airlines.db.dto.AirlinesDto;
import eng.airlines.db.interfaces.AirlinesDbInterface;
import eng.airlines.model.interfaces.AirlineModelInterface;

@Repository
public class AirlinesDbImplement implements AirlinesDbInterface {


	@Autowired
	private AirlinesDAO airlinesDAO;

	DozerBeanMapper mapper;

	public AirlinesDbImplement() {
		this.mapper = new DozerBeanMapper();
	}

	@Override
	public List<? extends AirlineModelInterface> findAll() {

		// TODO itt interfacet hasznalni
		List<AirlinesDto> airlines = new ArrayList<AirlinesDto>();
		airlinesDAO.findAll().forEach(airlines::add);

		return airlines;

	}

	@Override
	public AirlineModelInterface findById(Long id) {

		Optional<AirlinesDto> airlinesObject = airlinesDAO.findById(id);
		if (airlinesObject.isPresent()) {
			return airlinesObject.get();
		}

		return null;
	}

	@Override
	public AirlineModelInterface save(AirlineModelInterface airline) {

		AirlinesDto dbObject = mapper.map(airline, AirlinesDto.class);
		return airlinesDAO.save(dbObject);

	}
	
	/*
	 * Return true if deleted, Return false if not deleted, Return null if error
	 */
	@Override
	public Boolean deleteById(Long id) {
		try {
			airlinesDAO.deleteById(id);
			return true;
		} catch (EmptyResultDataAccessException emptyException) {
			return false;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public AirlineModelInterface findByName(String name) {
		return airlinesDAO.findByName(name);
	}

}
