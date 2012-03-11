package com.tastbudz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.FacebookProfile;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.tastbudz.dao.UserDAO;
import com.tastbudz.exception.DuplicateUserException;
import com.tastbudz.exception.UserNotFoundException;
import com.tastbudz.model.ID;
import com.tastbudz.model.User;
import com.tastbudz.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {
	@Autowired
	UserDAO userDAO;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Transactional
	public User signUp(String email) {
		Assert.notNull(email);
		User query = new User();
		query.setEmail(email);
		List<User> candidates = userDAO.getByExample(query);
		if (candidates != null && candidates.size() > 0) {
			throw new DuplicateUserException(email);
		}
		//
		return null;
		
	}
	
	@Transactional
	public User createUser(User user) {
		User query = new User();
		query.setUsername(user.getUsername());
		List<User> candidates = userDAO.getByExample(query);
		if (candidates != null && candidates.size() > 0) {
			for (User candidate : candidates) {
				if (candidate.compareTo(user) == 0) {
					throw new DuplicateUserException(user);
				}
			}
		}
		// Encrypt password so original password does not get saved in database
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		
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
	
	@Transactional(readOnly=true)
	public User authenticate(String username, String password) {
		User user = userDAO.getUser(username);
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
		//TODO
//		if (!passwordEncoder.matches(password, user.getPassword())) {
//			throw new BadCredentialsException("Invalid password");
//		}
		return user;
	}	
	
	@Transactional(readOnly=true)
	public User getUser(String username) {
		return userDAO.getUser(username);
	}
}
