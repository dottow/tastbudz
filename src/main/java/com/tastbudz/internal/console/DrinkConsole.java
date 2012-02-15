package com.tastbudz.internal.console;

import java.util.List;

import com.tastbudz.model.Drink;
import com.tastbudz.model.Restaurant;
import com.tastbudz.service.MenuService;
import com.tastbudz.service.ServiceLocator;

public class DrinkConsole extends AbstractCRUDConsole<Drink> {
	private Restaurant restaurant = null;
	
	public DrinkConsole(Console console, Restaurant restaurant) {
		super(console);
		this.restaurant = restaurant;
	}

	public void create() {
		Drink drink = new Drink();
		drink.setRestaurant(restaurant);
		buildAndSave(drink);
	}
	
	public void read() {
		List<Drink> drinks = getDrinks();
		for (Drink r : drinks) {
			console.printf("%s\n", r);
		}
	}

	public void update() {
		Drink drink = getDish();
		if (drink == null) return;
		
		buildAndSave(drink);
	}
	
	public void delete() {
		Drink drink = getDish();
		if (drink == null) return;
		
		MenuService service = ServiceLocator.getInstance()
		.getMenuService();
		
		service.removeDrink(drink);
	}
	
	private void buildAndSave(Drink drink) {
		ConsoleUtil.buildEntity(console, drink);
		
		MenuService service = ServiceLocator.getInstance()
				.getMenuService();

		drink = service.saveDrink(drink);
	}
	
	public Drink getDish() {
		List<Drink> drinks = getDrinks();
		return (Drink)ConsoleUtil.chooseEntity(console, drinks);
	}

	
	public List<Drink> getDrinks() {
		MenuService service = ServiceLocator.getInstance()
		.getMenuService();
		
		List<Drink> drinks = service.getDrinks(restaurant);
		return drinks;
	}
}
