package com.tastbudz.internal.console;

import java.util.List;

import com.tastbudz.model.Dish;
import com.tastbudz.model.Restaurant;
import com.tastbudz.service.MenuService;
import com.tastbudz.service.ServiceLocator;

public class DishConsole extends AbstractCRUDConsole<Dish> {
	private Restaurant restaurant = null;
	
	public DishConsole(Console console, Restaurant restaurant) {
		super(console);
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
		
		MenuService service = ServiceLocator.getInstance()
		.getMenuService();
		
		service.removeDish(dish);
	}
	
	private void buildAndSave(Dish dish) {
		ConsoleUtil.buildEntity(console, dish);
		
		MenuService service = ServiceLocator.getInstance()
				.getMenuService();

		dish = service.saveDish(dish);
	}
	
	public Dish getDish() {
		List<Dish> dishes = getDishes();
		return (Dish)ConsoleUtil.chooseEntity(console, dishes);
	}

	
	public List<Dish> getDishes() {
		MenuService service = ServiceLocator.getInstance()
		.getMenuService();
		
		List<Dish> dishes = service.getDishes(restaurant);
		return dishes;
	}
}
