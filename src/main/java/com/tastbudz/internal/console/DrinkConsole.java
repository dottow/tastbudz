package com.tastbudz.internal.console;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.tastbudz.model.Drink;
import com.tastbudz.model.Restaurant;
import com.tastbudz.service.MenuService;

public class DrinkConsole extends AbstractCRUDConsole<Drink> {
	@Autowired
	private MenuService menuService;

	private Restaurant restaurant = null;
	
	public DrinkConsole(Restaurant restaurant) {
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
		
		menuService.removeDrink(drink);
	}
	
	private void buildAndSave(Drink drink) {
		ConsoleUtil.buildEntity(console, drink);
		
		drink = menuService.saveDrink(drink);
	}
	
	public Drink getDish() {
		List<Drink> drinks = getDrinks();
		return (Drink)ConsoleUtil.chooseEntity(console, drinks);
	}

	
	public List<Drink> getDrinks() {
		List<Drink> drinks = menuService.getDrinks(restaurant);
		return drinks;
	}
}
