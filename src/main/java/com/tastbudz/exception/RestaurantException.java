package com.tastbudz.exception;

import com.tastbudz.model.Restaurant;

public class RestaurantException extends TastbudzException {
	private static final long serialVersionUID = 5082698053689066722L;

	private Restaurant restaurant;
	
	public RestaurantException() {
		this(null);
	}
	
	public RestaurantException(Restaurant restaurant) {
		setRestaurant(restaurant);
	}
	
	public Restaurant getRestaurant() {
		return restaurant;
	}
	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
}
