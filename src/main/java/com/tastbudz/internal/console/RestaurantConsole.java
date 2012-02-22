package com.tastbudz.internal.console;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.tastbudz.model.Restaurant;
import com.tastbudz.service.RestaurantService;

public class RestaurantConsole extends AbstractCRUDConsole<Restaurant> {
	@Autowired
	private RestaurantService service;
	
	public void create() {
		Restaurant restaurant = new Restaurant();
		buildAndSave(restaurant);
	}
	
	public void read() {
		List<Restaurant> restaurants = RestaurantUtil.getRestaurants(service, console);
		for (Restaurant r : restaurants) {
			console.printf("%s\n\n", r);
		}
	}

	public void update() {
		Restaurant restaurant = RestaurantUtil.getRestaurant(service, console);
		if (restaurant == null) return;
		
		buildAndSave(restaurant);
	}
	
	public void delete() {
		Restaurant restaurant = RestaurantUtil.getRestaurant(service, console);
		if (restaurant == null) return;
		
		service.removeRestaurant(restaurant.getId());
	}
	
	private void buildAndSave(Restaurant restaurant) {
		ConsoleUtil.buildEntity(console, restaurant);
		
		restaurant = service.saveRestaurant(restaurant);
	}

}
