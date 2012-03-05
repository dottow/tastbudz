package com.tastbudz.social.connect;

import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.OAuth2Version;
import org.springframework.social.support.ClientHttpRequestFactorySelector;

public class TastbudzTemplate extends AbstractOAuth2ApiBinding implements Tastbudz {
	public TastbudzTemplate() {
		initialize();
	}
	
	public TastbudzTemplate(String accessToken) {
		super(accessToken);
		initialize();
	}

    public void setRequestFactory(ClientHttpRequestFactory requestFactory)
    {
        super.setRequestFactory(ClientHttpRequestFactorySelector.bufferRequests(requestFactory));
    }

    protected OAuth2Version getOAuth2Version() {
        return OAuth2Version.DRAFT_10;
    }
    
    private void initialize()
    {
        super.setRequestFactory(ClientHttpRequestFactorySelector.bufferRequests(getRestTemplate().getRequestFactory()));
    }
}
