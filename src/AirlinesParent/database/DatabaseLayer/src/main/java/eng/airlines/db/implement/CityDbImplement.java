package eng.airlines.db.implement;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import eng.airlines.db.dao.CityDAO;
import eng.airlines.db.dto.CityDto;
import eng.airlines.db.interfaces.CityDbInterface;
import eng.airlines.model.interfaces.CityModelInterface;

@Repository
public class CityDbImplement implements CityDbInterface {


	@Autowired
	private CityDAO citysDAO;

	DozerBeanMapper mapper;

	public CityDbImplement() {
		this.mapper = new DozerBeanMapper();
	}

	@Override
	public List<? extends CityModelInterface> findAll() {

		List<CityDto> citys = new ArrayList<CityDto>();
		citysDAO.findAll().forEach(citys::add);

		return citys;

	}

	@Override
	public CityModelInterface findById(Long id) {

		Optional<CityDto> citysObject = citysDAO.findById(id);
		if (citysObject.isPresent()) {
			return citysObject.get();
		}

		return null;
	}

	@Override
	public CityModelInterface save(CityModelInterface city) {

		CityDto dbObject = mapper.map(city, CityDto.class);
		return citysDAO.save(dbObject);

	}

	/*
	 * Return true if deleted, Return false if not deleted, Return null if error
	 */
	@Override
	public Boolean deleteById(Long id) {
		try {
			citysDAO.deleteById(id);
			return true;
		} catch (EmptyResultDataAccessException emptyException) {
			return false;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public CityModelInterface findByName(String name) {
		return citysDAO.findByName(name);
	}

}
