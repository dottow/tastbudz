package com.tastbudz.json;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.SerializationConfig;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;

public class JsonHttpMessageConverter extends MappingJacksonHttpMessageConverter {
	public JsonHttpMessageConverter() {
		getObjectMapper().configure(SerializationConfig.Feature.INDENT_OUTPUT, true);
		this.setPrefixJson(false);
		List<MediaType> supportedMediaTypes = new ArrayList<MediaType>();
		supportedMediaTypes.add(MediaType.APPLICATION_JSON);
		this.setSupportedMediaTypes(supportedMediaTypes);
	}
	
	@Override
	protected void writeInternal(Object o, HttpOutputMessage outputMessage)
	        throws IOException, HttpMessageNotWritableException {

	    JsonEncoding encoding = getJsonEncoding(outputMessage.getHeaders().getContentType());
	    JsonGenerator jsonGenerator =
	            getObjectMapper().getJsonFactory().createJsonGenerator(outputMessage.getBody(), encoding);
	    try {
	        jsonGenerator.useDefaultPrettyPrinter();
	        getObjectMapper().writeValue(jsonGenerator, o);
	    }
	    catch (JsonGenerationException ex) {
	        throw new HttpMessageNotWritableException("Could not write JSON: " + ex.getMessage(), ex);
	    }
	}
}
