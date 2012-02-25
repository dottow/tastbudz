package com.tastbudz.exception;

import com.tastbudz.model.User;

public class UserException extends TastbudzException {
	private static final long serialVersionUID = 5082698053689066722L;

	private User user;
	
	public UserException() {
		this(null);
	}
	
	public UserException(User restaurant) {
		setUser(restaurant);
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
