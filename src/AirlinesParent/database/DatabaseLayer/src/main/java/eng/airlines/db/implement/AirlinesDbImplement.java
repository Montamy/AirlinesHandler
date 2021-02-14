package eng.airlines.db.implement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import eng.airlines.db.interfaces.AirlinesDbInterface;
import eng.airlines.model.interfaces.AirlineModelInterface;

@Service
public class AirlinesDbImplement implements AirlinesDbInterface {

	private Map<Long, AirlineModelInterface> airlinesDb;

	private Long lastId;

	public AirlinesDbImplement() {
		this.airlinesDb = new HashMap<Long, AirlineModelInterface>();
		this.lastId = 0L;
	}

	@Override
	public List<AirlineModelInterface> findAllAirline() {
		return airlinesDb.values().stream().collect(Collectors.toList());
	}

	@Override
	public AirlineModelInterface findAirlineById(Long id) {
		return airlinesDb.getOrDefault(id, null);
	}

	@Override
	public AirlineModelInterface saveAirline(AirlineModelInterface airline) {
		Long id = airline.getId();

		if (id == null) {
			id = lastId;
			lastId++;
		} else if (!airlinesDb.containsKey(id)) {
			// TODO throw error???
		}

		return airlinesDb.put(id, airline);
	}
	
	/*
	 * Return true if deleted, Return false if not deleted
	 */
	@Override
	public Boolean deleteAirlineById(Long id) {
		if (airlinesDb.remove(id) == null) {
			return false;
		}
		return true;
	}

}
