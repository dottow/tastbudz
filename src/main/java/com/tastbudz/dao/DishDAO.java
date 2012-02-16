package com.tastbudz.dao;

import java.util.List;

import com.tastbudz.model.Dish;
import com.tastbudz.model.ID;
import com.tastbudz.model.Restaurant;

public interface DishDAO extends ConsumableDAO<Dish, ID> {
	List<Dish> getDishes(Restaurant restaurant);
}
