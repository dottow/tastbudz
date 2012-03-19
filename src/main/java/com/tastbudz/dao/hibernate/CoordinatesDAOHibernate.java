package com.tastbudz.dao.hibernate;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tastbudz.dao.CoordinatesDAO;
import com.tastbudz.model.Coordinates;
import com.tastbudz.model.ID;

@Repository("coordinatesDAO")
@Transactional( propagation = Propagation.MANDATORY )
public class CoordinatesDAOHibernate extends GenericHibernateDAO<Coordinates, ID> implements
		CoordinatesDAO {
	
}
