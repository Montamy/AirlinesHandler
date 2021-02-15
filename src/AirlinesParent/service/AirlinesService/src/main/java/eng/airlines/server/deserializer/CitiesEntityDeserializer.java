package eng.airlines.server.deserializer;

import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;

import eng.airlines.server.model.City;

public class CitiesEntityDeserializer extends BaseEntityDeserializer<City> {

	@Override
	public City deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException, JsonProcessingException {

		Map<String, Object> requestParsingObjectMap = createRequestObjectPropertyMap(jsonParser); 

		return getCityFromPropertyMap(requestParsingObjectMap);
	}

	private Long getPopulationFromProperties(Map<String, Object> requestParsingObjectMap) {
		Long resultPopulation = null;

		Object idObject = requestParsingObjectMap.getOrDefault("population", null);
		if (idObject != null) {
			resultPopulation = Long.parseLong(idObject.toString());
		}

		return resultPopulation;
	}

	public City getCityFromPropertyMap(Map<String, Object> requestParsingObjectMap) {
		Long id = getIdFromMap(requestParsingObjectMap);
		String name = getStringFromPropertiesMapByPropertyName("name", requestParsingObjectMap);
		Long population = getPopulationFromProperties(requestParsingObjectMap);

		City city = new City(id, name, population);

		return city;
	}
}
