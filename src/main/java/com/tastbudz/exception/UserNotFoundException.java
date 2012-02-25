package com.tastbudz.exception;

import com.tastbudz.model.User;

public class UserNotFoundException extends UserException {
	private static final long serialVersionUID = -6384639823869560762L;

	public UserNotFoundException() {
		super();
	}
	
	public UserNotFoundException(User user) {
		super(user);
	}
}
