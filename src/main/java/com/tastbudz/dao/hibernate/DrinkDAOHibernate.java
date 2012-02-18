package com.tastbudz.dao.hibernate;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tastbudz.dao.DrinkDAO;
import com.tastbudz.model.Drink;
import com.tastbudz.model.ID;
import com.tastbudz.model.Restaurant;

@Repository
public class DrinkDAOHibernate extends ConsumableDAOHibernate<Drink, ID> implements
		DrinkDAO {

	public List<Drink> getDrinks(Restaurant restaurant) {
		return getConsumables(restaurant);
	}

}
