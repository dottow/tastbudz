package com.tastbudz.internal.data;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.tastbudz.model.Restaurant;
import com.tastbudz.service.RestaurantService;
import com.tastbudz.service.ServiceLocator;

public class RestaurantConsole implements Runnable {
	private Console console;
	
	public RestaurantConsole(Console console) {
		this.console = console;
	}
	
	public void run() {
		console.printf("Restaurant console\n\n");
		
		String input="";
		while (!(input.equals("quit"))) {
        	console.printf("[C]reate\n");
        	console.printf("[R]ead\n");
        	console.printf("[U]pdate\n");
        	console.printf("[D]elete\n");
        	console.printf("\n");
        	console.printf("Choice:  ");
        	input = console.readLine();

			input = input.trim().toLowerCase();
			if (input.startsWith("c")) {
				create();
			} 
			if (input.startsWith("r")) {
				read();
			}
			else if (input.startsWith("u")) {
				update();
			} 
			else if (input.startsWith("d")) {
				delete();
			} 
		}
	}

	private void create() {
		Restaurant restaurant = createRestaurant();
		
		RestaurantService service = ServiceLocator.getInstance()
				.getRestaurantService();

		restaurant = service.saveRestaurant(restaurant);
	}

	private void read() {
		List<Restaurant> restaurants = getRestaurants();
		for (Restaurant r : restaurants) {
			System.out.println(r);
		}
	}

	private void update() {
		Restaurant restaurant = getRestaurant();
		if (restaurant == null) return;
		
		//TODO
	}
	
	private void delete() {
		Restaurant restaurant = getRestaurant();
		if (restaurant == null) return;
		
		//TODO
	}
	
	private Restaurant getRestaurant() {
		List<Restaurant> restaurants = getRestaurants();
		if (restaurants.size() == 0) {
			console.printf("No match!");
			return null;
		}
		if (restaurants.size() == 1) {
			return restaurants.get(0);
		}
		
		//TODO
		return null;
	}

	private List<Restaurant> getRestaurants() {
		Restaurant restaurant = createRestaurant();
		
		RestaurantService service = ServiceLocator.getInstance()
				.getRestaurantService();

		List<Restaurant> restaurants = service.getRestaurants(restaurant);
		return restaurants;
	}
	
	private Restaurant createRestaurant() {
		Restaurant restaurant = new Restaurant();
		buildRestaurant(restaurant);
		return restaurant;
	}
	
	private void buildRestaurant(Restaurant restaurant) {
		console.printf("Name? ");
		String name = console.readLine();
		if (!StringUtils.isBlank(name))
			restaurant.setName(name);
		
		console.printf("City? ");
		String city = console.readLine();
		if (!StringUtils.isBlank(city))
			restaurant.setCity(city);

		console.printf("State? ");
		String state = console.readLine();
		if (!StringUtils.isBlank(state))
			restaurant.setStateCode(state);

		console.printf("Country? ");
		String country = console.readLine();
		if (!StringUtils.isBlank(country))
			restaurant.setCountryCode(country);

		console.printf("URL? ");
		String url = console.readLine();
		if (!StringUtils.isBlank(url))
			restaurant.setUrl(url);

		console.printf("Phone? ");
		String phone = console.readLine();
		if (!StringUtils.isBlank(phone))
			restaurant.setPhone(phone);
		
		System.out.println(restaurant);
	}
}
