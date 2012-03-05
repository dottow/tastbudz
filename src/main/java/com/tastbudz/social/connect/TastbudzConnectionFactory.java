package com.tastbudz.social.connect;

import org.springframework.social.connect.support.OAuth2ConnectionFactory;

public class TastbudzConnectionFactory extends OAuth2ConnectionFactory {
    public TastbudzConnectionFactory(String clientId, String clientSecret)
    {
        super("tastbudz", new TastbudzServiceProvider(clientId, clientSecret), new TastbudzAdapter());
    }

}
