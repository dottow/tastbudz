package com.tastbudz.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tastbudz.model.ID;
import com.tastbudz.model.Restaurant;
import com.tastbudz.service.RestaurantService;

@Controller
@RequestMapping("/restaurants")
public class RestaurantController {
	@Autowired
	private RestaurantService restaurantService;
	
	@RequestMapping(value = "/city/{city}", method = RequestMethod.GET)
	public @ResponseBody Map<String, ? extends Object> getRestaurantsByCity(@PathVariable("city")String city) {
		List<Restaurant> restaurants = restaurantService.getRestaurantsByCity(city);
		return Collections.singletonMap("restaurants", restaurants);
	}
		
	@RequestMapping(value = "/{restaurantId}", method = RequestMethod.GET)
	public @ResponseBody Restaurant getRestaurant(@PathVariable("restaurantId")String restaurantId) {
		ID id = ID.fromString(restaurantId);
		Restaurant restaurant = restaurantService.getRestaurant(id);
		return restaurant;
	}

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody Restaurant createRestaurant(@ModelAttribute("restaurant")Restaurant restaurant) {
		restaurant = restaurantService.saveRestaurant(restaurant);
		return restaurant;
	}
	
	@RequestMapping(value = "/{restaurantId}", method = RequestMethod.DELETE)
	public String deleteRestaurant(@PathVariable("restaurantId")String restaurantId) {
		ID id = ID.fromString(restaurantId);
		restaurantService.removeRestaurant(id);
		return "redirect:/index";
	}	
}
