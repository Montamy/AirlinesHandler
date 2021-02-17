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
		return getLongFromMapByItsName("id", requestParsingObjectMap);
	}

	protected String getStringFromPropertiesMapByPropertyName(String propertyName, Map<String, Object> requestParsingObjectMap) {
		return requestParsingObjectMap.getOrDefault(propertyName, "").toString();
	}

	protected Long getLongFromMapByItsName(String propertyName, Map<String, Object> requestParsingObjectMap) {

		Long result = null;

		Object idObject = requestParsingObjectMap.getOrDefault(propertyName, null);
		if (idObject != null) {
			result = Long.parseLong(idObject.toString());
		}

		return result;
	}

	protected Integer getIntegerFromMapByItsName(String propertyName, Map<String, Object> requestParsingObjectMap) {

		Integer result = null;

		Object idObject = requestParsingObjectMap.getOrDefault(propertyName, null);
		if (idObject != null) {
			result = Integer.parseInt(idObject.toString());
		}

		return result;
	}

	protected Map<String, Object> createRequestObjectPropertyMap(JsonParser jsonParser) throws IOException {

		ObjectMapper mapper = new ObjectMapper();

		ObjectCodec oc = jsonParser.getCodec();
		JsonNode node = oc.readTree(jsonParser);

		return mapper.convertValue(node, new TypeReference<Map<String, Object>>() {
		});
	}

}
