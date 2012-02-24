package com.tastbudz.service.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tastbudz.comparator.ComparatorFactory;
import com.tastbudz.dao.DishDAO;
import com.tastbudz.dao.DrinkDAO;
import com.tastbudz.model.Dish;
import com.tastbudz.model.Drink;
import com.tastbudz.model.Menu;
import com.tastbudz.model.Restaurant;
import com.tastbudz.service.MenuService;

@Service
public class MenuServiceImpl implements MenuService {
	@Autowired
	DishDAO dishDAO;
	@Autowired
	DrinkDAO drinkDAO;

	@Transactional
	public Dish saveDish(Dish dish) {
		return dishDAO.save(dish);
	}

	@Transactional
	public void removeDish(Dish dish) {
		dishDAO.delete(dish);
	}

	@Transactional(readOnly=true)
	public List<Dish> getDishes(Restaurant restaurant) {
		List<Dish> dishes = dishDAO.getDishes(restaurant);
		Collections.sort(dishes, ComparatorFactory.getComparator(Dish.class));
		return dishes;
	}

	@Transactional(readOnly=true)
	public List<Dish> getDishes(Dish criteria) {
		List<Dish> dishes = dishDAO.getByExample(criteria);
		Collections.sort(dishes, ComparatorFactory.getComparator(Dish.class));
		return dishes;
	}

	@Transactional
	public Drink saveDrink(Drink drink) {
		return drinkDAO.save(drink);
	}

	@Transactional
	public void removeDrink(Drink drink) {
		drinkDAO.delete(drink);
	}

	@Transactional(readOnly=true)
	public List<Drink> getDrinks(Restaurant restaurant) {
		List<Drink> drinks = drinkDAO.getDrinks(restaurant);
		Collections.sort(drinks, ComparatorFactory.getComparator(Drink.class));
		return drinks;
	}

	@Transactional(readOnly=true)
	public List<Drink> getDrinks(Drink criteria) {
		List<Drink> drinks = drinkDAO.getByExample(criteria);
		Collections.sort(drinks, ComparatorFactory.getComparator(Drink.class));
		return drinks;
	}

	@Transactional(readOnly=true)
	public Menu getMenu(Restaurant restaurant) {
		Menu menu = new Menu();
		menu.setDrinks(getDrinks(restaurant));
		menu.setDishes(getDishes(restaurant));
		return menu;
	}

}
