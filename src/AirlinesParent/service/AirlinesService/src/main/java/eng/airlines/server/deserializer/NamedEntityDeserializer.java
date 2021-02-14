package eng.airlines.server.deserializer;

import java.util.Map;

public abstract class NamedEntityDeserializer<T> extends BaseEntityDeserializer<T> {

	protected String getNameFromPropertiesMap(Map<String, Object> requestParsingObjectMap) {
		return requestParsingObjectMap.getOrDefault("name", "").toString();
	}

}
