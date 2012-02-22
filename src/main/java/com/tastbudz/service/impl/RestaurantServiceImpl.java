package com.tastbudz.service.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tastbudz.comparator.ComparatorFactory;
import com.tastbudz.dao.RestaurantDAO;
import com.tastbudz.model.Cuisine;
import com.tastbudz.model.ID;
import com.tastbudz.model.Restaurant;
import com.tastbudz.service.RestaurantService;

@Service
public class RestaurantServiceImpl implements RestaurantService {
	@Autowired
	RestaurantDAO restaurantDAO;

	@Transactional
	public Restaurant saveRestaurant(Restaurant restaurant) {
		return restaurantDAO.save(restaurant);
	}

	@Transactional
	public void removeRestaurant(ID id) {
		Restaurant restaurant = restaurantDAO.read(id);
		restaurantDAO.delete(restaurant);
	}

	@Transactional(readOnly=true) 
	public Restaurant getRestaurant(ID id) {
		return restaurantDAO.read(id);
	}
	
	@Transactional(readOnly=true)
	public List<Restaurant> getRestaurantsByCity(String city) {
		Restaurant restaurant = new Restaurant();
		restaurant.setCity(city);
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
