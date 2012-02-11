package com.tastbudz.dao.hibernate;

import java.util.Locale;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.tastbudz.dao.RestaurantDAO;
import com.tastbudz.model.Location;
import com.tastbudz.model.Restaurant;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring-test.xml")
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback = true)
@Transactional
public class TestHibernateRestaurantDao extends TestCase {
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

//	@Test
//	public void testMultipleSaves() {
//		Location location = createWalnutCreekLocation();
//		location = dao.save(location);
//		ID id = location.getId();
//		assertEquals("Location should be the same after save", _919_BANCROFT_RD, location.getAddress());
//		location.setAddress(_944_NATCHEZ_CT);
//		location.setCrossStreet(CHEYENNE);
//		dao.save(location);
//		location = dao.findById(id);
//		assertNotSame("Location should no longer be the same", _919_BANCROFT_RD, location);
//	}
//
//	@Test
//	public void testDifferentSaves() {
//		Location location1 = createWalnutCreekLocation();
//		Location location2 = createWalnutCreekLocation();
//		location2.setAddress(_944_NATCHEZ_CT);
//		location2.setCrossStreet(CHEYENNE);
//		location1 = dao.save(location1);
//		location2 = dao.save(location2);
//
//		Location location = dao.findById(location1.getId());
//		assertEquals(location, location1);
//		location = dao.findById(location2.getId());
//		assertEquals(location, location2);
//	}
//
//	@Test
//	public void testQuery() {
//		Location address1 = createWalnutCreekLocation();
//		Location address2 = createWalnutCreekLocation();
//		address2.setAddress(_944_NATCHEZ_CT);
//		address2.setCrossStreet(CHEYENNE);
//		address1 = dao.save(address1);
//		address2 = dao.save(address2);
//
//		List<Location> all = dao.findAll();
//		assertEquals(2, all.size());
//		for (Location a : all) {
//			assertEquals(WALNUT_CREEK, a.getCity());
//		}
//		
//		Location query = new Location();
//		query.setCity(WALNUT_CREEK);
//		query.setStateCode(CA);
//		query.setCountryCode(US);
//		List<Location> matches = dao.findByExample(query);
//		assertEquals(2, matches.size());
//	}
//
//	@Test
//	public void testDifferentLocations() {
//		Location walnutCreek = createWalnutCreekLocation();
//		Location vallejo = createVallejoLocation();
//		assertNotSame("The 2 addresses must not be the same", walnutCreek, vallejo);
//		walnutCreek = dao.save(walnutCreek);
//		vallejo = dao.save(vallejo);
//		assertNotSame("The 2 addresses must not be the same", walnutCreek, vallejo);
//	}
	
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
