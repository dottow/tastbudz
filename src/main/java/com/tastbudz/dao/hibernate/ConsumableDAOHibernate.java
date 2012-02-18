package com.tastbudz.dao.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Example;

import com.tastbudz.dao.ConsumableDAO;
import com.tastbudz.model.Consumable;
import com.tastbudz.model.ID;
import com.tastbudz.model.Restaurant;

public abstract class ConsumableDAOHibernate<T extends Consumable, PK extends ID> extends GenericHibernateDAO<T, PK>
		implements ConsumableDAO<T, PK> {
	
	public List<T> getConsumables(Restaurant restaurant) {
		T consumable;
		try {
			consumable = getPersistentClass().newInstance();
			consumable.setRestaurant(restaurant);
			return getByExample(consumable);
		} 
		catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<T> getByExample(T exampleInstance) {
		exampleInstance = (T)exampleInstance.makeQueryCriteria();
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(getPersistentClass());
		Example example =  Example.create(exampleInstance).enableLike();
		crit.add(example);
		crit.createCriteria("restaurant").add(Example.create(exampleInstance.getRestaurant()));
		return crit.list();
	}


}
