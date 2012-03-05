package com.tastbudz.authentication;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import com.tastbudz.model.User;
import com.tastbudz.service.AccountService;

@Service
public class TastbudzAuthenticationProvider implements AuthenticationProvider {
	@Autowired
	private AccountService accountService;
	
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {

		UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
		String name = token.getName();
		String credentials = token.getCredentials().toString();

		User user = accountService.authenticate(name,  credentials);
			
		List<GrantedAuthority> authorities = null;
		UsernamePasswordAuthenticationToken authenticated = new UsernamePasswordAuthenticationToken(user, null, authorities);
		authenticated.setDetails(authentication.getDetails());
		return authenticated;		
	}

	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.equals(authentication);
	}

}
