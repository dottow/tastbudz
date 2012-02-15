package com.tastbudz.dao.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.tastbudz.dao.CuisineDAO;
import com.tastbudz.model.Cuisine;
import com.tastbudz.model.id.ID;

@Repository
public class CuisineDAOHibernate extends GenericHibernateDAO<Cuisine, ID> implements
		CuisineDAO {

	@Override
	public List<Cuisine> getAllCuisines() {
		Session session = sessionFactory.getCurrentSession();
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cuisine> getMatchingCuisines(Cuisine cuisine) {
		// TODO Auto-generated method stub
		return null;
	}	
}
