package com.tastbudz.dao.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Example;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tastbudz.dao.RestaurantDAO;
import com.tastbudz.model.ID;
import com.tastbudz.model.Restaurant;

@Repository("restaurantDAO")
@Transactional( propagation = Propagation.MANDATORY )
public class RestaurantDAOHibernate extends GenericHibernateDAO<Restaurant, ID> implements
		RestaurantDAO {
	
	@Override
	public List<Restaurant> getByExample(Restaurant exampleInstance) {
		exampleInstance = (Restaurant)exampleInstance.makeQueryCriteria();
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(getPersistentClass());
		Example example =  Example.create(exampleInstance).enableLike();
		crit.add(example);
		crit.createCriteria("location").add(Example.create(exampleInstance.getLocation()));
		return crit.list();
	}
}
