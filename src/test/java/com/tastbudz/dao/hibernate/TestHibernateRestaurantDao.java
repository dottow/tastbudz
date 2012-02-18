package com.tastbudz.dao.hibernate;

import java.util.Locale;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.tastbudz.config.TestConfig;
import com.tastbudz.dao.RestaurantDAO;
import com.tastbudz.model.Location;
import com.tastbudz.model.Restaurant;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback = true)
@Transactional
public class TestHibernateRestaurantDao extends TestCase {
	@Configuration
	@Import(TestConfig.class)
	static class ContextConfig {
		@Bean
		public RestaurantDAO getRestaurantDAO() {
			return new RestaurantDAOHibernate();
		}
	}

	
	private static final String US = Locale.US.getCountry();
	private static final String CA = "CA";
	@Autowired
	private RestaurantDAO dao;
	
	@Test
	public void testSaveLocation() {
		Restaurant restaurant = create54Mint();
		Restaurant retval = dao.save(restaurant);
		//the value returned by the DAO must be equal to
		//the original vale
		assertEquals("The DAO's returned value of saving"+
                "must be equal to original", restaurant, retval);
		//look inside the database through direct Hibernate
		//the session is auto-commit, so we are sure that the
		//transaction is committed, the session is flushed
		//after each operation
		Restaurant actualRestaurant = dao.read(retval.getId());

		//the actual Student object in the database must be
		//equal to the original value
		assertEquals("Actual Location and DAO's returned "+
	                    "Location must be equal",
	                     restaurant, actualRestaurant);
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
