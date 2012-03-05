package com.tastbudz.social.connect;

import org.springframework.social.oauth2.OAuth2Template;

public class TastbudzOAuth2Template extends OAuth2Template {
	private final static String AUTHORIZED_URL = "https://dev.tastbudz.com/oauth/authorize";
	private final static String ACCESS_TOKEN_URL = "https://dev.tastbudz.com/oauth/access_token";
	
	
    public TastbudzOAuth2Template(String clientId, String clientSecret) {
        super(clientId, clientSecret, AUTHORIZED_URL, ACCESS_TOKEN_URL);
    }
}
