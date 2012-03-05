package com.tastbudz.social.connect;

import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.oauth2.OAuth2Operations;

public class TastbudzServiceProvider extends AbstractOAuth2ServiceProvider {

	public TastbudzServiceProvider(String clientId, String clientSecret) {
		super(new TastbudzOAuth2Template(clientId, clientSecret));
	}

	public Object getApi(String accessToken) {
		return new TastbudzTemplate(accessToken);
	}
}
