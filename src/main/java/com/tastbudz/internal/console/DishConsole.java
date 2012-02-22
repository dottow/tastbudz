package com.tastbudz.internal.console;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.tastbudz.model.Dish;
import com.tastbudz.model.Restaurant;
import com.tastbudz.service.MenuService;

public class DishConsole extends AbstractCRUDConsole<Dish> {
	@Autowired
	private MenuService menuService;
	
	private Restaurant restaurant = null;
	
	public DishConsole(Restaurant restaurant) {
		super();
		this.restaurant = restaurant;
	}

	public void create() {
		Dish dish = new Dish();
		dish.setRestaurant(restaurant);
		buildAndSave(dish);
	}
	
	public void read() {
		List<Dish> dishes = getDishes();
		for (Dish r : dishes) {
			console.printf("%s\n", r);
		}
	}

	public void update() {
		Dish dish = getDish();
		if (dish == null) return;
		
		buildAndSave(dish);
	}
	
	public void delete() {
		Dish dish = getDish();
		if (dish == null) return;
		
		menuService.removeDish(dish);
	}
	
	private void buildAndSave(Dish dish) {
		ConsoleUtil.buildEntity(console, dish);
		
		dish = menuService.saveDish(dish);
	}
	
	public Dish getDish() {
		List<Dish> dishes = getDishes();
		return (Dish)ConsoleUtil.chooseEntity(console, dishes);
	}

	
	public List<Dish> getDishes() {
		List<Dish> dishes = menuService.getDishes(restaurant);
		return dishes;
	}
}
