package com.tastbudz.exception;

import com.tastbudz.model.Restaurant;

public class RestaurantNotFoundException extends RestaurantException {
	private static final long serialVersionUID = 7429111495225536496L;

	public RestaurantNotFoundException(Restaurant restaurant) {
		super(restaurant);
	}
}
