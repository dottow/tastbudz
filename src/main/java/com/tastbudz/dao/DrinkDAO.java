package com.tastbudz.dao;

import java.util.List;

import com.tastbudz.model.Drink;
import com.tastbudz.model.ID;
import com.tastbudz.model.Restaurant;

public interface DrinkDAO extends ConsumableDAO<Drink, ID> {
	List<Drink> getDrinks(Restaurant restaurant);

}
