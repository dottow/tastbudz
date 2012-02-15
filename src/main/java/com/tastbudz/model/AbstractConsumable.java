package com.tastbudz.model;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.Columns;
import org.hibernate.annotations.Type;


@MappedSuperclass
public class AbstractConsumable extends PersistentEntity implements Consumable {
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
		
	public Restaurant getRestaurant() {
		return restaurant;
	}
	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
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
}
