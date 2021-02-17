package eng.airlines.server.deserializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import eng.airlines.server.model.Airline;
import eng.airlines.server.model.City;
import eng.airlines.server.model.Flight;

public class FlightsEntityDeserializer extends BaseEntityDeserializer<Flight> {

	private static String AIRLINE_PROPERTY_NAME = "airline";
	private static String SOURCE_CITY_PROPERTY_NAME = "sourceCity";
	private static String DESTINATION_CITY_PROPERTY_NAME = "destinationCity";

	@Override
	public Flight deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException, JsonProcessingException {

		// TODO rewrite
		ObjectMapper mapper = new ObjectMapper();

		ObjectCodec oc = jsonParser.getCodec();
		JsonNode node = oc.readTree(jsonParser);

		
		Map<String, Object> requestParsingObjectMap = mapper.convertValue(node, new TypeReference<Map<String, Object>>() {});
		List<Map<String, Object>> complexPropertiesPropertyMap = new ArrayList<Map<String, Object>>();

		complexPropertiesPropertyMap.add(createPropertiesMapByName(AIRLINE_PROPERTY_NAME, node));
		complexPropertiesPropertyMap.add(createPropertiesMapByName(SOURCE_CITY_PROPERTY_NAME, node));
		complexPropertiesPropertyMap.add(createPropertiesMapByName(DESTINATION_CITY_PROPERTY_NAME, node));

		// TODO reqrite end

		Long id = getIdFromMap(requestParsingObjectMap);
		Integer scheduleMin = getIntegerFromMapByItsName("scheduleMin", requestParsingObjectMap);
		Integer distance = getIntegerFromMapByItsName("distance", requestParsingObjectMap);

		Airline airline = createAirlineByPropertyMap(complexPropertiesPropertyMap.get(0));
		City source = createCityByPropertyMap(complexPropertiesPropertyMap.get(1));
		City destination = createCityByPropertyMap(complexPropertiesPropertyMap.get(2));



		Flight flight = new Flight(id, airline, source, destination, distance, scheduleMin);

		return flight;
	}

	private City createCityByPropertyMap(Map<String, Object> map) {
		CitiesEntityDeserializer cityDeserializer = new CitiesEntityDeserializer();
		return cityDeserializer.getCityFromPropertyMap(map);
	}

	private Airline createAirlineByPropertyMap(Map<String, Object> map) {

		AirlinesEntityDeserializer airlineDeserializer = new AirlinesEntityDeserializer();
		return airlineDeserializer.getAirlineFromPropertyMap(map);
	}

	protected List<Map<String, Object>> createComplexInnerObjectsPropertyMap (JsonParser jsonParser) throws IOException {

		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

		ObjectCodec oc = jsonParser.getCodec();
		JsonNode node = oc.readTree(jsonParser);

		result.add(createPropertiesMapByName(AIRLINE_PROPERTY_NAME, node));
		result.add(createPropertiesMapByName(SOURCE_CITY_PROPERTY_NAME, node));
		result.add(createPropertiesMapByName(DESTINATION_CITY_PROPERTY_NAME, node));

		return result;

	}

	private Map<String, Object> createPropertiesMapByName(String name, JsonNode jsonNode) {
		ObjectMapper mapper = new ObjectMapper();

		if (jsonNode.get(name) == null) {
			return new HashMap<String, Object>();
		}
		return mapper.convertValue(jsonNode.get(name), new TypeReference<Map<String, Object>>() {
		});
	}


}
