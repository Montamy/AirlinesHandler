package eng.airlines.server.deserializer;

import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;

import eng.airlines.server.model.Airline;

public class AirlinesEntityDeserializer extends NamedEntityDeserializer<Airline> {

	@Override
	public Airline deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException, JsonProcessingException {

		Map<String, Object> requestParsingObjectMap = createRequestObjectPropertyMap(jsonParser); 

		Long id = getIdFromMap(requestParsingObjectMap);
		String name = getNameFromPropertiesMap(requestParsingObjectMap);

		Airline x = new Airline(id, name);

		return x;
	}


}
