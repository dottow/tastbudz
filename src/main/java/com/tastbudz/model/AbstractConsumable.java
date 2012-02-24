package com.tastbudz.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.Columns;
import org.hibernate.annotations.Type;


@MappedSuperclass
public abstract class AbstractConsumable extends PersistentEntity implements Consumable {
	private static final long serialVersionUID = -5383254921855270579L;
	@ManyToOne
	@JoinColumn(name="restaurant_id", referencedColumnName="id", nullable=false)
	private Restaurant restaurant;
	@Column(name="name", nullable=false)
	private String name;
	@Type(type="Price")
	@Columns(columns = { @Column(name = "amount"),@Column(name = "currency") })
	private Price price;
	@Column(name="calories", nullable=true)
	private double calories;
		
	private static List<String> propertyOrdering;
	
	static {
		propertyOrdering = new ArrayList<String>();
		propertyOrdering.add("id");
		propertyOrdering.add("restaurantId");
		propertyOrdering.add("name");
		propertyOrdering.add("price");
		propertyOrdering.add("calories");
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}
	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	public ID getRestaurantId() {
		return restaurant.getId();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Price getPrice() {
		return price;
	}
	public void setPrice(Price price) {
		this.price = price;
	}
	public double getCalories() {
		return calories;
	}
	public void setCalories(double calories) {
		this.calories = calories;
	}
	
	@Override
	public String toString() {
		return getName();
	}
	
	public List<String> getPropertyNames() {
		return propertyOrdering;
	}

}
