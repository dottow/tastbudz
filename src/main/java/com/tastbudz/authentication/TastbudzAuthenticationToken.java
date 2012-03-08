package com.tastbudz.authentication;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import com.tastbudz.model.User;

public class TastbudzAuthenticationToken extends AbstractAuthenticationToken {
	private User user;
	
	public TastbudzAuthenticationToken(User user, Collection<? extends GrantedAuthority> authorities) {
		super(authorities);
		this.user = user;
	}

	@Override
	public Object getCredentials() {
		return null;  //TODO
	}

	public Object getPrincipal() {
		return user;
	}
	
	public String getName() {
		return user.getUsername();
	}

}
