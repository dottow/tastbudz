package com.tastbudz.dao;

import java.util.List;

import com.tastbudz.model.Dish;
import com.tastbudz.model.Restaurant;
import com.tastbudz.model.id.ID;

public interface DishDAO extends ConsumableDAO<Dish, ID> {
	List<Dish> getDishes(Restaurant restaurant);
}
