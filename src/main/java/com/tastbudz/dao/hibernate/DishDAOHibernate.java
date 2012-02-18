package com.tastbudz.dao.hibernate;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tastbudz.dao.DishDAO;
import com.tastbudz.model.Dish;
import com.tastbudz.model.ID;
import com.tastbudz.model.Restaurant;

@Repository
public class DishDAOHibernate extends ConsumableDAOHibernate<Dish, ID> implements
		DishDAO {

	public List<Dish> getDishes(Restaurant restaurant) {
		return getConsumables(restaurant);
	}
}
