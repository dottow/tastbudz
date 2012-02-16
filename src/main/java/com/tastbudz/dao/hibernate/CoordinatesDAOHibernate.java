package com.tastbudz.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.tastbudz.dao.CoordinatesDAO;
import com.tastbudz.model.Coordinates;
import com.tastbudz.model.ID;

@Repository
public class CoordinatesDAOHibernate extends GenericHibernateDAO<Coordinates, ID> implements
		CoordinatesDAO {
	
}
