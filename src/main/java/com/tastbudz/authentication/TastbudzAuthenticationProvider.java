package com.tastbudz.authentication;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tastbudz.model.User;
import com.tastbudz.service.AccountService;

@Service
public class TastbudzAuthenticationProvider implements AuthenticationProvider {
	private static Logger logger = Logger.getLogger(TastbudzAuthenticationProvider.class);
	@Autowired
	private AccountService accountService;
	
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {

		TastbudzAuthenticationToken token = (TastbudzAuthenticationToken) authentication;
		String name = token.getName();
		logger.debug("Authenticating "+name);
		String credentials = token.getCredentials() == null ? null : token.getCredentials().toString();

		User user = null;
		try {
			user = accountService.authenticate(name,  credentials);
			logger.debug("User found in system: "+user);
		}
		catch (UsernameNotFoundException e) {
			// Implicit account creation at signin
			user = (User)token.getPrincipal();
			logger.debug("Implicit account creation during sign-in of user: "+user);
			user = accountService.createUser(user);
		}

		List<GrantedAuthority> authorities = null;
		TastbudzAuthenticationToken authenticated = new TastbudzAuthenticationToken(user, authorities);
		authenticated.setAuthenticated(true);
		return authenticated;		
	}

	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.equals(authentication);
	}

}
