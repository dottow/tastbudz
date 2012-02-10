package com.tastbudz.model;

public interface Consumable extends Taggable, Viewable, Entity {
    public Restaurant getRestaurant();
	public String getName();
	public Price getPrice();
	public double getCalories();
}
