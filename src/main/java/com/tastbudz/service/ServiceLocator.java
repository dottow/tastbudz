package com.tastbudz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

public final class ServiceLocator {
	private static ServiceLocator instance=null;
	@Autowired
	private StaticDataService staticDataService;
	@Autowired
	private RestaurantService restaurantService;
	
	public static void initialize(ApplicationContext context) {
		instance = context.getBean(ServiceLocator.class);
	}
	public static ServiceLocator getInstance() {
		return instance;
	}

	public StaticDataService getStaticDataService() {
		return staticDataService;
	}
	public RestaurantService getRestaurantService() {
		return restaurantService;
	}
}
