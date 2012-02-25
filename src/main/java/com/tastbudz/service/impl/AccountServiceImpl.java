package com.tastbudz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tastbudz.dao.UserDAO;
import com.tastbudz.exception.UserNotFoundException;
import com.tastbudz.model.ID;
import com.tastbudz.model.User;
import com.tastbudz.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {
	@Autowired
	UserDAO userDAO;

	@Transactional
	public User createUser(User user) {
		User query = new User();
		query.setUsername(user.getUsername());
		List<User> candidates = userDAO.getByExample(query);
		if (candidates != null && candidates.size() > 0) {
			for (User candidate : candidates) {
				if (candidate.compareTo(user) == 0) {
					return candidate;
				}
			}
		}
		return userDAO.save(user);
	}

	@Transactional
	public User updateUser(User user) {
		User existing = userDAO.read(user.getId());
		if (existing == null) {
			throw new UserNotFoundException(user);
		}
		existing.merge(user);
		return userDAO.save(existing);
	}
	
	@Transactional
	public void deleteUser(ID id) {
		User restaurant = userDAO.read(id);
		userDAO.delete(restaurant);
	}

	@Transactional(readOnly=true) 
	public User readUser(ID id) {
		User restaurant = userDAO.read(id);
		return restaurant;
	}
}
