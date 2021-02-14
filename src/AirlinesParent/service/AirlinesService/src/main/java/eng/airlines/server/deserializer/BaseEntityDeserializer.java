package eng.airlines.server.deserializer;

import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class BaseEntityDeserializer<T> extends JsonDeserializer<T> {

	protected Long getIdFromMap(Map<String, Object> requestParsingObjectMap) {

		Long resultId = null;

		Object idObject = requestParsingObjectMap.getOrDefault("id", null);
		if (idObject != null) {
			resultId = Long.parseLong(idObject.toString());
		}

		return resultId;
	}

	protected Map<String, Object> createRequestObjectPropertyMap(JsonParser jsonParser) throws IOException {

		ObjectMapper mapper = new ObjectMapper();

		ObjectCodec oc = jsonParser.getCodec();
		JsonNode node = oc.readTree(jsonParser);
		
		return mapper.convertValue(node, new TypeReference<Map<String, Object>>() {});
	}
}
