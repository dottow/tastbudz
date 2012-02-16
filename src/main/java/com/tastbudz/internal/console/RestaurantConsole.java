package com.tastbudz.internal.console;

import java.util.List;

import com.tastbudz.model.Restaurant;
import com.tastbudz.service.RestaurantService;
import com.tastbudz.service.ServiceLocator;

public class RestaurantConsole extends AbstractCRUDConsole<Restaurant> {
	public RestaurantConsole(Console console) {
		super(console);
	}

	public void create() {
		Restaurant restaurant = new Restaurant();
		buildAndSave(restaurant);
	}
	
	public void read() {
		List<Restaurant> restaurants = RestaurantUtil.getRestaurants(console);
		for (Restaurant r : restaurants) {
			console.printf("%s\n", r);
		}
	}

	public void update() {
		Restaurant restaurant = RestaurantUtil.getRestaurant(console);
		if (restaurant == null) return;
		
		buildAndSave(restaurant);
	}
	
	public void delete() {
		Restaurant restaurant = RestaurantUtil.getRestaurant(console);
		if (restaurant == null) return;
		
		RestaurantService service = ServiceLocator.getInstance()
		.getRestaurantService();
		
		service.removeRestaurant(restaurant.getId());
	}
	
	private void buildAndSave(Restaurant restaurant) {
		ConsoleUtil.buildEntity(console, restaurant);
		
		RestaurantService service = ServiceLocator.getInstance()
				.getRestaurantService();

		restaurant = service.saveRestaurant(restaurant);
	}

}
