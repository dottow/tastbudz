package com.tastbudz.controller;

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
	public @ResponseBody List<Restaurant> getRestaurantsByCity(@PathVariable("city")String city) {
		List<Restaurant> restaurants = restaurantService.getRestaurantsByCity(city);
		for (Restaurant restaurant : restaurants) {
			System.out.println(restaurant);
		}
		return restaurants;
	}
	
	@RequestMapping("/index")
	public String listRestaurants(Map<String, Object> map) {
		return "restaurant";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String createRestaurant(@ModelAttribute("restaurant")Restaurant restaurant) {
		restaurantService.saveRestaurant(restaurant);
		return "redirect:/index";
	}
	
	@RequestMapping(value = "/{restaurantId}", method = RequestMethod.DELETE)
	public String deleteRestaurant(@PathVariable("restaurantId")String restaurantId) {
		ID id = ID.fromString(restaurantId);
		restaurantService.removeRestaurant(id);
		return "redirect:/index";
	}
	
	/*
	 * 	@Transactional
	public Restaurant saveRestaurant(Restaurant restaurant) {
		return restaurantDAO.save(restaurant);
	}

	@Transactional
	public void removeRestaurant(Restaurant restaurant) {
		restaurantDAO.delete(restaurant);
	}

	@Transactional(readOnly=true)
	public List<Restaurant> getRestaurants(Restaurant criteria) {
		List<Restaurant> restaurants = restaurantDAO.getByExample(criteria);
		return restaurants;
	}

	 */
}
