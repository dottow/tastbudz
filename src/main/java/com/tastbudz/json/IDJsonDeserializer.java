package com.tastbudz.json;

import java.io.IOException;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.deser.std.StdDeserializer;

import com.tastbudz.model.ID;

public class IDJsonDeserializer extends StdDeserializer<ID> {

	protected IDJsonDeserializer(Class<?> vc) {
		super(vc);
	}

	@Override
	public ID deserialize(JsonParser parser, DeserializationContext context)
			throws IOException, JsonProcessingException {
		String s = parser.getText();
		return ID.fromString(s);
	}

}
