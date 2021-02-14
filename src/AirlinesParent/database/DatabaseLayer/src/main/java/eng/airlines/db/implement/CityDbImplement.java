package eng.airlines.db.implement;

import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eng.airlines.db.dao.AirlinesDAO;
import eng.airlines.db.interfaces.CityDbInterface;
import eng.airlines.model.interfaces.CityModelInterface;

@Service
public class CityDbImplement implements CityDbInterface {


	@Autowired
	private AirlinesDAO airlinesDAO;

	DozerBeanMapper mapper;

	public CityDbImplement() {
		this.mapper = new DozerBeanMapper();
	}

	@Override
	public List<CityModelInterface> findAllCity() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CityModelInterface findCityById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CityModelInterface saveCity(CityModelInterface airline) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean deleteCityById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
