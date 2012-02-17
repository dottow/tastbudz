package com.tastbudz.json;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

import com.tastbudz.model.ID;

public class IDJsonSerializer extends JsonSerializer<ID> {

	@Override
	public void serialize(ID id, JsonGenerator jgen, SerializerProvider provider)
			throws IOException, JsonProcessingException {

		jgen.writeString(id.toString());
	}

}
