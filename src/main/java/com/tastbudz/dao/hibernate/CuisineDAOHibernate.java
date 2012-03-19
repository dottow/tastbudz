package com.tastbudz.dao.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tastbudz.dao.CuisineDAO;
import com.tastbudz.model.Cuisine;
import com.tastbudz.model.ID;

@Repository("cuisineDAO")
@Transactional( propagation = Propagation.MANDATORY )
public class CuisineDAOHibernate extends GenericHibernateDAO<Cuisine, ID> implements
		CuisineDAO {

	@Override
	public List<Cuisine> getAllCuisines() {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(getPersistentClass());
		return crit.list();
	}

	@Override
	public List<Cuisine> getMatchingCuisines(Cuisine cuisine) {
		return getByExample(cuisine);
	}	
}
