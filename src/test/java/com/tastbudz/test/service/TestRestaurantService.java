package com.tastbudz.test.service;

import java.util.Locale;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.tastbudz.model.Location;
import com.tastbudz.model.Restaurant;
import com.tastbudz.service.RestaurantService;
import com.tastbudz.test.config.TestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback = true)
@Transactional
public class TestRestaurantService extends TestCase {
	@Configuration
	@Import(TestConfig.class)
	static class ContextConfig {
	}

	
	private static final String US = Locale.US.getCountry();
	private static final String CA = "CA";
	@Autowired
	private RestaurantService service;
	
	@Test
	public void testSaveRestaurant() {
		Restaurant restaurant = create54Mint();
		Restaurant retval = service.createRestaurant(restaurant);
		//the value returned by the DAO must be equal to
		//the original vale
		assertEquals("The Restaurant's returned value of saving"+
                "must be equal to original", restaurant, retval);

		Restaurant actualRestaurant = service.readRestaurant(retval.getId());

		//the actual Student object in the database must be
		//equal to the original value
		assertEquals("Actual Restaurant and service's returned "+
	                    "restaurant must be equal",
	                     restaurant, actualRestaurant);
	}

	@Test
	public void testSaveExistingRestaurant() {
		Restaurant restaurant = create54Mint();
		service.createRestaurant(restaurant);
		
		Restaurant duplicate = create54Mint();
		duplicate = service.createRestaurant(duplicate);
		
		assertEquals("Saving of duplicate restaurant should return original", restaurant, duplicate);
		
		duplicate.setCrossStreet(null);
		duplicate.setAddress("");
		duplicate.setPostalCode(null);
		
		duplicate = service.createRestaurant(duplicate);
		
		assertEquals("Saving of incomplete restaurant data should return original", restaurant, duplicate);
	}
	
	private Restaurant create54Mint() {
		Restaurant restaurant = new Restaurant();
		restaurant.setName("54 Mint Rosticceria & Formaggi");
		Location address = new Location();
		address.setAddress("785 Oak Grove Rd", "Suite 4");
		address.setCrossStreet("Treat");
		address.setCity("Concord");
		address.setStateCode(CA);
		address.setPostalCode("94518");
		address.setCountryCode(US);
		restaurant.setLocation(address);
		
		return restaurant;
	}
}
