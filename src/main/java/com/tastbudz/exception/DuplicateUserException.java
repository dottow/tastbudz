package com.tastbudz.exception;

import com.tastbudz.model.User;

public class DuplicateUserException extends UserException {
	private static final long serialVersionUID = -5532067562424062355L;
	private String email;
	
	public DuplicateUserException(String email) {
		this.email = email;
	}
	
	public DuplicateUserException(User user) {
		super(user);
	}
	
	public String getEmail() {
		if (email != null) {
			return email;
		}
		if (user != null) {
			return user.getEmail();
		}
		return null;
	}
}
