package com.tastbudz.json;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

import com.tastbudz.model.PropertyList;

public class PropertyListSerializer extends JsonSerializer<PropertyList> {
	public void serialize(PropertyList entity, JsonGenerator jgen, SerializerProvider provider)
				throws IOException, JsonProcessingException {

		JsonUtil.serialize(entity, jgen, provider);
	}
}