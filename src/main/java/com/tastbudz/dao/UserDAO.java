package com.tastbudz.dao;

import com.tastbudz.model.ID;
import com.tastbudz.model.User;

public interface UserDAO extends GenericDAO<User, ID> {

	public User getUser(String name);
}
