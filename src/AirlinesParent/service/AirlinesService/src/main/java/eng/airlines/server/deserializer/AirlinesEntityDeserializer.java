package eng.airlines.server.deserializer;

import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;

import eng.airlines.server.model.Airline;

public class AirlinesEntityDeserializer extends BaseEntityDeserializer<Airline> {

	@Override
	public Airline deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException, JsonProcessingException {

		Map<String, Object> requestParsingObjectMap = createRequestObjectPropertyMap(jsonParser); 

		return getAirlineFromPropertyMap(requestParsingObjectMap);
	}

	public Airline getAirlineFromPropertyMap(Map<String, Object> requestParsingObjectMap) {
		Long id = getIdFromMap(requestParsingObjectMap);
		String name = getStringFromPropertiesMapByPropertyName("name", requestParsingObjectMap);

		Airline x = new Airline(id, name);

		return x;
	}


}
