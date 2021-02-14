package eng.airlines.server;

import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import eng.airlines.server.model.Airline;

public class AirlinesEntityDeserializer extends JsonDeserializer<Airline> {

	@Override
	public Airline deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException, JsonProcessingException {

		Map<String, Object> requestParsingObjectMap = createRequestObjectPropertyMap(jsonParser); 

		Long id = getIdFromMap(requestParsingObjectMap);
		String name = getNameFromPropertiesMap(requestParsingObjectMap);

		Airline x = new Airline(id, name);

		return x;
	}

	private String getNameFromPropertiesMap(Map<String, Object> requestParsingObjectMap) {
		return requestParsingObjectMap.getOrDefault("name", "").toString();
	}

	private Long getIdFromMap(Map<String, Object> requestParsingObjectMap) {

		Long resultId = null;

		Object idObject = requestParsingObjectMap.getOrDefault("id", null);
		if (idObject != null) {
			resultId = Long.parseLong(idObject.toString());
		}

		return resultId;
	}

	private Map<String, Object> createRequestObjectPropertyMap(JsonParser jsonParser) throws IOException {

		ObjectMapper mapper = new ObjectMapper();

		ObjectCodec oc = jsonParser.getCodec();
		JsonNode node = oc.readTree(jsonParser);
		
		return mapper.convertValue(node, new TypeReference<Map<String, Object>>() {});
	}
}
