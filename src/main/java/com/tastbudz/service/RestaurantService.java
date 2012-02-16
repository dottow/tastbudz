package com.tastbudz.service;

import java.util.List;

import com.tastbudz.model.ID;
import com.tastbudz.model.Restaurant;

public interface RestaurantService {
    public Restaurant saveRestaurant(Restaurant restaurant);
    public void removeRestaurant(ID id);
    public Restaurant getRestaurant(ID id);
    public List<Restaurant> getRestaurantsByCity(String city);
	public List<Restaurant> getRestaurants(Restaurant criteria);
}
