package com.tastbudz.service;

import java.util.List;

import com.tastbudz.model.Dish;
import com.tastbudz.model.Drink;
import com.tastbudz.model.Restaurant;

public interface MenuService {
    public Dish saveDish(Dish dish);
    public void removeDish(Dish dish);
	public List<Dish> getDishes(Restaurant restaurant);
	public List<Dish> getDishes(Dish criteria);
    public Drink saveDrink(Drink drink);
    public void removeDrink(Drink drink);
	public List<Drink> getDrinks(Restaurant restaurant);
	public List<Drink> getDrinks(Drink criteria);
	
}
