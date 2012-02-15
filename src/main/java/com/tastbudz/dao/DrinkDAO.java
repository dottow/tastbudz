package com.tastbudz.dao;

import java.util.List;

import com.tastbudz.model.Drink;
import com.tastbudz.model.Restaurant;
import com.tastbudz.model.id.ID;

public interface DrinkDAO extends ConsumableDAO<Drink, ID> {
	List<Drink> getDrinks(Restaurant restaurant);

}
