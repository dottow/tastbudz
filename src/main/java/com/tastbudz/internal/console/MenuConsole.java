package com.tastbudz.internal.console;

import org.springframework.beans.factory.annotation.Autowired;

import com.tastbudz.model.Restaurant;
import com.tastbudz.service.RestaurantService;

public class MenuConsole implements Runnable {
	@Autowired
	private RestaurantService restaurantService;
	@Autowired
	protected Console console;
	protected Restaurant restaurant=null;

	public void run() {
		console.printf("Menu console\n\n");
		
		String input="";
		while (!(input.equals("quit"))) {
        	console.printf("[R]estaurant\n");
        	console.printf("[E]ats\n");
        	console.printf("[D]rinks\n");
        	console.printf("[Q}uit\n");
        	console.printf("\n");
    		if (restaurant == null) {
    			console.printf("No restaurant selected.\n");
    		}
    		else {
    			console.printf("Selected Restaurant: "+restaurant);
    		}
    		console.printf("\n");
        	console.printf("Choice:  ");
        	input = console.readLine();

			input = input.trim().toLowerCase();
			if (input.startsWith("r")) {
				restaurant();
			} 
			else if (input.startsWith("e")) {
				eats();
			} 
			else if (input.startsWith("d")) {
				drinks();
			} 
			else if (input.startsWith("q")) {
				return;
			}
			else {
				console.printf("Huh?!\n\n");
			}
			console.printf("\n\n\n");
		}
	}

	public void restaurant() {
		restaurant = RestaurantUtil.getRestaurant(restaurantService, console);
	}
		
	public void eats() {
		if (restaurant == null) {
			console.printf("You must select a restaurant first!");
			return;
		}

		DishConsole dishConsole = new DishConsole(restaurant);
		dishConsole.run();
	}
	
	public void drinks() {
		if (restaurant == null) {
			console.printf("You must select a restaurant first!");
			return;
		}

		DrinkConsole drinkConsole = new DrinkConsole(restaurant);
		drinkConsole.run();		
	}
}
