package com.tastbudz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tastbudz.dao.RestaurantDAO;
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
	public void removeRestaurant(Restaurant restaurant) {
		restaurantDAO.delete(restaurant);
	}

	@Transactional(readOnly=true)
	public List<Restaurant> getRestaurants(Restaurant criteria) {
		return null; //TODO
//		List<Restaurant> restaurants = restaurantDAO.findByExample(criteria);
//		for (Restaurant restaurant : restaurants) {
//			restaurant.toString();
//		}
//		return restaurants;
	}

}
