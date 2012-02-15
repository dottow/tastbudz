package com.tastbudz.internal.console;

import java.util.List;

import com.tastbudz.model.Restaurant;
import com.tastbudz.service.RestaurantService;
import com.tastbudz.service.ServiceLocator;

public final class RestaurantUtil {
	public static Restaurant getRestaurant(Console console) {
		List<Restaurant> restaurants = getRestaurants(console);
		return (Restaurant)ConsoleUtil.chooseEntity(console, restaurants);
	}

	
	public static List<Restaurant> getRestaurants(Console console) {
		Restaurant restaurant = createRestaurant(console);
		
		RestaurantService service = ServiceLocator.getInstance()
				.getRestaurantService();

		List<Restaurant> restaurants = service.getRestaurants(restaurant);
		return restaurants;
	}
	
	public static Restaurant createRestaurant(Console console) {
		Restaurant restaurant = new Restaurant();
		ConsoleUtil.buildEntity(console, restaurant);
		return restaurant;
	}
	
}
