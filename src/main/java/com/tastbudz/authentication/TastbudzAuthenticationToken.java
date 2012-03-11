package com.tastbudz.authentication;

import org.springframework.security.authentication.AbstractAuthenticationToken;

import com.tastbudz.model.User;

public class TastbudzAuthenticationToken extends AbstractAuthenticationToken {
	private static final long serialVersionUID = 5122605653506432008L;
	private User user;
	
	public TastbudzAuthenticationToken(User user) {
		super(user.getAuthorities());
		this.user = user;
	}

	@Override
	public Object getCredentials() {
		return "";  //TODO
	}

	public Object getPrincipal() {
		return user;
	}
	
	public String getName() {
		return user.getUsername();
	}

}
