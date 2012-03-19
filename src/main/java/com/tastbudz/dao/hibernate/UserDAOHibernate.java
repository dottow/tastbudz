package com.tastbudz.dao.hibernate;

import java.util.List;

import junit.framework.Assert;

import org.hibernate.Criteria;
import org.hibernate.criterion.Example;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tastbudz.dao.UserDAO;
import com.tastbudz.model.ID;
import com.tastbudz.model.User;

@Repository("userDAO")
@Transactional( propagation = Propagation.MANDATORY )
public class UserDAOHibernate extends GenericHibernateDAO<User, ID> implements
		UserDAO {

	@Override
	public User getUser(String name) {
		User user = new User();
		user = (User)user.makeQueryCriteria();
		user.setUsername(name);
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(getPersistentClass());
		Example example = Example.create(user);
		crit.add(example);
		List<User> users = crit.list();
		if (users == null || users.size() == 0) {
			return null;
		}
		
		Assert.assertEquals(1, users.size());
		return users.get(0);
	}
}
