package com.tastbudz.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tastbudz.comparator.ComparatorFactory;
import com.tastbudz.dao.RestaurantDAO;
import com.tastbudz.exception.RestaurantNotFoundException;
import com.tastbudz.model.ID;
import com.tastbudz.model.Restaurant;
import com.tastbudz.service.RestaurantService;

@Service
@Transactional( propagation = Propagation.REQUIRES_NEW )
public class RestaurantServiceImpl implements RestaurantService {
	@Autowired
	RestaurantDAO restaurantDAO;

	public Restaurant createRestaurant(Restaurant restaurant) {
		Restaurant query = new Restaurant();
		query.setName(restaurant.getName());
		query.setCity(restaurant.getCity());
		query.setCountryCode(restaurant.getCountryCode());
		if (Locale.US.getCountry().equals(query.getCountryCode())) {
			query.setStateCode(restaurant.getStateCode());
		}
		List<Restaurant> candidates = restaurantDAO.getByExample(query);
		if (candidates != null && candidates.size() > 0) {
			for (Restaurant candidate : candidates) {
				if (candidate.compareTo(restaurant) == 0) {
					return candidate;
				}
			}
		}
		return restaurantDAO.save(restaurant);
	}

	public Restaurant updateRestaurant(Restaurant restaurant) {
		Restaurant existing = restaurantDAO.read(restaurant.getId());
		if (existing == null) {
			throw new RestaurantNotFoundException(restaurant);
		}
		existing.merge(restaurant);
		return restaurantDAO.save(existing);
	}
	
	public void deleteRestaurant(ID id) {
		Restaurant restaurant = restaurantDAO.read(id);
		restaurantDAO.delete(restaurant);
	}

	@Transactional(readOnly=true) 
	public Restaurant readRestaurant(ID id) {
		Restaurant restaurant = restaurantDAO.read(id);
		return restaurant;
	}
	
	@Transactional(readOnly=true)
	public List<Restaurant> getRestaurantsByCity(String city) {
		Restaurant restaurant = new Restaurant();
		restaurant.setCity(city);
		restaurant.setCountryCode(Locale.US.getCountry());
		List<Restaurant> restaurants = restaurantDAO.getByExample(restaurant);
		Collections.sort(restaurants, ComparatorFactory.getComparator(Restaurant.class));
		return restaurants;
	}

	@Transactional(readOnly=true)
	public List<Restaurant> getRestaurantsByCity(String city, String name) {
		Restaurant restaurant = new Restaurant();
		restaurant.setCity(city);
		restaurant.setCountryCode(Locale.US.getCountry());
		restaurant.setName("%"+name+"%");
		List<Restaurant> restaurants = restaurantDAO.getByExample(restaurant);
		Collections.sort(restaurants, ComparatorFactory.getComparator(Restaurant.class));
		return restaurants;
	}

	@Transactional(readOnly=true)
	public List<Restaurant> getRestaurants(Restaurant restaurant) {
		List<Restaurant> restaurants = restaurantDAO.getByExample(restaurant);
		Collections.sort(restaurants, ComparatorFactory.getComparator(Restaurant.class));
		return restaurants;
	}
}
