package com.tastbudz.service;

import java.util.List;

import com.tastbudz.model.Restaurant;

public interface RestaurantService {
    public Restaurant saveRestaurant(Restaurant restaurant);
    public void removeRestaurant(Restaurant restaurant);
	public List<Restaurant> getRestaurants(Restaurant criteria);
}
