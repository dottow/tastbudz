package com.tastbudz.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.tastbudz.dao.UserDAO;
import com.tastbudz.model.ID;
import com.tastbudz.model.User;

@Repository("userDAO")
public class UserDAOHibernate extends GenericHibernateDAO<User, ID> implements
		UserDAO {
}
