package com.tastbudz.dao.hibernate;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tastbudz.dao.DrinkDAO;
import com.tastbudz.model.Drink;
import com.tastbudz.model.ID;
import com.tastbudz.model.Restaurant;

@Repository("drinkDAO")
@Transactional( propagation = Propagation.MANDATORY )
public class DrinkDAOHibernate extends ConsumableDAOHibernate<Drink, ID> implements
		DrinkDAO {

	public List<Drink> getDrinks(Restaurant restaurant) {
		return getConsumables(restaurant);
	}

}
