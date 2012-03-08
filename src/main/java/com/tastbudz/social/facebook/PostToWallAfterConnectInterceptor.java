package com.tastbudz.social.facebook;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.ApiException;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.web.ConnectInterceptor;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.tastbudz.model.User;
import com.tastbudz.model.UserConnection;
import com.tastbudz.service.ThirdPartyService;

public class PostToWallAfterConnectInterceptor implements ConnectInterceptor<Facebook> {
    private static Logger logger = Logger.getLogger(PostToWallAfterConnectInterceptor.class);
    
    public void preConnect(ConnectionFactory<Facebook> connectionFactory, MultiValueMap<String, String> parameters, WebRequest request) {
    }

    public void postConnect(Connection<Facebook> connection, WebRequest request) {
    	logger.debug("postConnect called.");
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	logger.debug("authentication = "+authentication);
		User user = (User)authentication.getPrincipal();
		logger.debug("Retrieving connections for "+user.getUsername());
	
		WebApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(((ServletWebRequest)request).getRequest().getSession().getServletContext());
		ThirdPartyService service = context.getBean(ThirdPartyService.class);
		List<UserConnection> connections = service.getConnections(user.getUsername());
		boolean firstLogin=true;
		if (connections != null) {
			firstLogin=false;
			logger.debug("Found "+connections.size()+" connections.");
			for (UserConnection c : connections) {
				logger.debug("Provider: *"+c.getProviderId()+"*");
				if ("facebook".equalsIgnoreCase(c.getProviderId())) {
					firstLogin = c.getDateCreated().equals(c.getDateUpdated());
					logger.debug("First login? "+firstLogin);
				}
			}
		}
		if (!firstLogin) return;
		
		logger.debug("Updating status...");
		try {
    		connection.updateStatus("I've just logged into TastBudz!");
    	} catch (ApiException e) {
    		// Do nothing: No need to break down if the post-connect post can't be made.
        }
    }

}
