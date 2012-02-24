package com.tastbudz.test.model;

import java.util.List;
import java.util.Locale;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.tastbudz.model.Cuisine;
import com.tastbudz.model.Restaurant;
import com.tastbudz.model.util.ModelUtil;
import com.tastbudz.model.util.NameValuePair;
import com.tastbudz.test.config.TestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
public class TestRestaurant extends TestCase {
	@Configuration
	@Import(TestConfig.class)
	static class ContextConfig {
	}

	@Test
	public void testRestaurant() {
		Restaurant restaurant = new Restaurant();
		restaurant.setName("Picasso");
		restaurant.setCountryCode(Locale.US.getCountry());
		restaurant.setStateCode("NV");
		restaurant.setCity("Las Vegas");
		restaurant.getCuisines().add(new Cuisine("Spanish"));
		restaurant.getCuisines().add(new Cuisine("Tapas"));
		
		List<NameValuePair> nameValuePairs = ModelUtil.getNameValuePairs(restaurant);
		for (NameValuePair item : nameValuePairs) {
			System.out.println(item);
		}
	}
}
