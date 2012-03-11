package com.tastbudz.controller;

import javax.inject.Inject;

import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/signin/connect")
public class SpringSocialController extends ConnectController {
	private final static String REDIRECT_VIEW = "/";
	/**
	 * Constructs a ConnectController.
	 * @param connectionFactoryLocator the locator for {@link ConnectionFactory} instances needed to establish connections
	 * @param connectionRepository the current user's {@link ConnectionRepository} needed to persist connections; must be a proxy to a request-scoped bean
	 */
	@Inject
	public SpringSocialController(ConnectionFactoryLocator connectionFactoryLocator, ConnectionRepository connectionRepository) {
		super(connectionFactoryLocator, connectionRepository);
	}

	/**
	 * Returns the view name of a general connection status page, typically displaying the user's connection status for all providers.
	 * Defaults to "/connect/status". May be overridden to return a custom view name.
	 */
	@Override
	protected String connectView() {
		return REDIRECT_VIEW;
	}
	
	/**
	 * Returns the view name of a page to display for a provider when the user is not connected to the provider.
	 * Typically this page would offer the user an opportunity to create a connection with the provider.
	 * Defaults to "connect/{providerId}Connect". May be overridden to return a custom view name.
	 * @param providerId the ID of the provider to display the connection status for.
	 */
	@Override
	protected String connectView(String providerId) {
		return REDIRECT_VIEW;		
	}

	/**
	 * Returns the view name of a page to display for a provider when the user is connected to the provider.
	 * Typically this page would allow the user to disconnect from the provider.
	 * Defaults to "connect/{providerId}Connected". May be overridden to return a custom view name.
	 * @param providerId the ID of the provider to display the connection status for.
	 */
	@Override
	protected String connectedView(String providerId) {
		return REDIRECT_VIEW;		
	}

	/**
	 * Returns a RedirectView with the URL to redirect to after a connection is created or deleted.
	 * Defaults to "/connect/{providerId}" relative to DispatcherServlet's path. 
	 * May be overridden to handle custom redirection needs.
	 * @param providerId the ID of the provider for which a connection was created or deleted.
	 * @param request the NativeWebRequest used to access the servlet path when constructing the redirect path.
	 */
	@Override
	protected RedirectView connectionStatusRedirect(String providerId, NativeWebRequest request) {
		return new RedirectView(REDIRECT_VIEW, true);
	}
}