package com.tastbudz.model;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.tastbudz.json.PropertyListSerializer;

@JsonSerialize(using=PropertyListSerializer.class)
public class Menu implements PropertyList {
	private List<Drink> drinks;
	private List<Dish> dishes;
	
	private static List<String> propertyList;
	
	static {
		propertyList = new ArrayList<String>();
		propertyList.add("drinks");
		propertyList.add("dishes");
	}
	
	public Menu() {
		drinks = new ArrayList<Drink>();
		dishes = new ArrayList<Dish>();
	}
	
	public void setDrinks(List<Drink> drinks) {
		this.drinks = drinks;
	}
	public List<Drink> getDrinks() {
		return drinks;
	}
	public void setDishes(List<Dish> dishes) {
		this.dishes = dishes;
	}
	public List<Dish> getDishes() {
		return dishes;
	}

	@Override
	public List<String> getPropertyNames() {
		return propertyList;
	}
	
}
