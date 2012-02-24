package com.tastbudz.json;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.SerializerProvider;

import com.tastbudz.model.Entity;
import com.tastbudz.model.PropertyList;
import com.tastbudz.model.util.ModelUtil;
import com.tastbudz.model.util.NameValuePair;

public abstract class JsonUtil {
	public static void serialize(PropertyList entity, JsonGenerator jgen, SerializerProvider provider)
			throws IOException, JsonProcessingException {
		jgen.writeStartObject();
		List<NameValuePair> nameValues = ModelUtil.getNameValuePairs(entity);
		for (NameValuePair nameValue : nameValues) {
			String name = nameValue.getName();
			Object value = nameValue.getValue();
			if (value != null) {
				jgen.writeFieldName(name);
				if (value instanceof String) {
					jgen.writeString(value.toString());
				}
				else if (value instanceof Collection) {
					Collection items = (Collection)value;
					jgen.writeStartArray();
					for (Object item : items) {
						if (item instanceof Entity) {
							serialize((Entity)item, jgen, provider);
						}
						else {
							System.out.println(item);
						}
					}
					jgen.writeEndArray();
				}
			}
		}
		jgen.writeEndObject();
	}

}
