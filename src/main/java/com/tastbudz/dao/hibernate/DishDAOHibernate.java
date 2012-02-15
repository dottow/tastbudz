package com.tastbudz.dao.hibernate;

import java.util.List;

import com.tastbudz.dao.DishDAO;
import com.tastbudz.model.Dish;
import com.tastbudz.model.Restaurant;
import com.tastbudz.model.id.ID;

public class DishDAOHibernate extends ConsumableDAOHibernate<Dish, ID> implements
		DishDAO {

	public List<Dish> getDishes(Restaurant restaurant) {
		return getConsumables(restaurant);
	}
}
