package com.tastbudz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tastbudz.dao.DishDAO;
import com.tastbudz.dao.DrinkDAO;
import com.tastbudz.model.Dish;
import com.tastbudz.model.Drink;
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
		return dishDAO.getDishes(restaurant);
	}

	@Transactional(readOnly=true)
	public List<Dish> getDishes(Dish criteria) {
		return dishDAO.getByExample(criteria);
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
		return drinkDAO.getDrinks(restaurant);
	}

	@Transactional(readOnly=true)
	public List<Drink> getDrinks(Drink criteria) {
		return drinkDAO.getByExample(criteria);
	}

}
