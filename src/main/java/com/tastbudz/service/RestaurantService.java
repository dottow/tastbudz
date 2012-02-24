package com.tastbudz.service;

import java.util.List;

import com.tastbudz.model.ID;
import com.tastbudz.model.Restaurant;

public interface RestaurantService {
    public Restaurant createRestaurant(Restaurant restaurant);
    public Restaurant readRestaurant(ID id);
    public Restaurant updateRestaurant(Restaurant restaurant);
    public void deleteRestaurant(ID id);
    public List<Restaurant> getRestaurantsByCity(String city);
    public List<Restaurant> getRestaurantsByCity(String city, String name);
	public List<Restaurant> getRestaurants(Restaurant criteria);
}
