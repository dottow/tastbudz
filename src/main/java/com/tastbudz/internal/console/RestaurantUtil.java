package com.tastbudz.internal.console;

import java.util.List;

import com.tastbudz.model.Restaurant;
import com.tastbudz.service.RestaurantService;

public final class RestaurantUtil {
	public static Restaurant getRestaurant(RestaurantService service, Console console) {
		List<Restaurant> restaurants = getRestaurants(service, console);
		return (Restaurant)ConsoleUtil.chooseEntity(console, restaurants);
	}

	
	public static List<Restaurant> getRestaurants(RestaurantService service, Console console) {
		Restaurant restaurant = createRestaurant(console);
		
		List<Restaurant> restaurants = service.getRestaurants(restaurant);
		return restaurants;
	}
	
	public static Restaurant createRestaurant(Console console) {
		Restaurant restaurant = new Restaurant();
		ConsoleUtil.buildEntity(console, restaurant);
		return restaurant;
	}
	
}
