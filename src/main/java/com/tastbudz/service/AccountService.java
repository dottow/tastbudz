package com.tastbudz.service;

import com.tastbudz.model.ID;
import com.tastbudz.model.User;


public interface AccountService {
	public User createUser(User user);
    public User readUser(ID id);
    public User updateUser(User user);
    public void deleteUser(ID id);
}
