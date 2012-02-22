package com.tastbudz.json;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;
import org.hsqldb.lib.StringUtil;

import com.tastbudz.model.Restaurant;

public class RestaurantSerializer extends JsonSerializer<Restaurant> {

	@Override
	public void serialize(Restaurant restaurant, JsonGenerator jgen, SerializerProvider provider)
			throws IOException, JsonProcessingException {

		jgen.writeStartObject();
		jgen.writeFieldName("id");
		jgen.writeString(restaurant.getId().toString());
		jgen.writeFieldName("name");
		jgen.writeString(restaurant.getName());
		String phone = restaurant.getPhone();
		if (!StringUtil.isEmpty(phone)) {
			jgen.writeFieldName("phone");
			jgen.writeString(phone);
		}
		String address = restaurant.getAddress();
		if (!StringUtil.isEmpty(address)) {
			jgen.writeFieldName("address");
			jgen.writeString(address);
		}
		String city = restaurant.getCity();
		if (!StringUtil.isEmpty(city)) {
			jgen.writeFieldName("city");
			jgen.writeString(city);
		}
		String stateCode = restaurant.getStateCode();
		if (!StringUtil.isEmpty(stateCode)) {
			jgen.writeFieldName("stateCode");
			jgen.writeString(stateCode);
		}
		String postalCode = restaurant.getPostalCode();
		if (!StringUtil.isEmpty(postalCode)) {
			jgen.writeFieldName("postalCode");
			jgen.writeString(postalCode);
		}
		String countryCode = restaurant.getCountryCode();
		if (!StringUtil.isEmpty(countryCode)) {
			jgen.writeFieldName("countryCode");
			jgen.writeString(countryCode);
		}
		String url = restaurant.getUrl();
		if (!StringUtil.isEmpty(url)) {
			jgen.writeFieldName("url");
			jgen.writeString(url);
		}
		jgen.writeEndObject();
	}

}
