package com.tastbudz.config;

import javax.inject.Inject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.env.Environment;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.support.ConnectionFactoryRegistry;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.connect.TwitterConnectionFactory;

import com.tastbudz.model.User;
import com.tastbudz.social.connect.TastbudzUsersConnectionRepository;

@Configuration
@PropertySource("classpath:/com/tastbudz/social.properties")
public class SocialConfig {
	@Inject
	private Environment env;

	@Bean
    public ConnectionFactoryLocator connectionFactoryLocator() {
        ConnectionFactoryRegistry registry = new ConnectionFactoryRegistry();
        registry.addConnectionFactory(new FacebookConnectionFactory(env.getProperty("facebook.clientId"), env.getProperty("facebook.clientSecret")));
        registry.addConnectionFactory(new TwitterConnectionFactory(env.getProperty("twitter.consumerKey"), env.getProperty("twitter.consumerSecret")));
        return registry;
    }
	
	@Bean
	public TextEncryptor getTextEncryptor() {
		return Encryptors.noOpText();
	}
	
	@Bean
	public UsersConnectionRepository usersConnectionRepository() {
		TastbudzUsersConnectionRepository repository = new TastbudzUsersConnectionRepository();
		return repository;
	}

	@Bean
	@Scope(value="request", proxyMode=ScopedProxyMode.INTERFACES)
	public ConnectionRepository connectionRepository() {
	    User user = null; //TODO SecurityContext.getCurrentUser();
	    return usersConnectionRepository().createConnectionRepository(user.getUsername());
	}

	@Bean
	@Scope(value="request", proxyMode=ScopedProxyMode.INTERFACES)	
	public Twitter twitter() {
	    return connectionRepository().getPrimaryConnection(Twitter.class).getApi();
	}

	@Bean
	@Scope(value="request", proxyMode=ScopedProxyMode.INTERFACES)	
	public Facebook facebook() {
	    return connectionRepository().getPrimaryConnection(Facebook.class).getApi();
	}

}
