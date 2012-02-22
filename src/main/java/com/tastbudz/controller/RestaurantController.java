package com.tastbudz.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

import org.hsqldb.lib.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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

	
	@RequestMapping(value = "/{restaurantId}", method = RequestMethod.GET)
	public @ResponseBody Restaurant getRestaurant(@PathVariable("restaurantId")String restaurantId) {
		ID id = ID.fromString(restaurantId);
		Restaurant restaurant = restaurantService.getRestaurant(id);
		return restaurant;
	}

	@RequestMapping(value = "/city/{city}", method = RequestMethod.GET)
	public @ResponseBody List<? extends Object> getRestaurantsByCity(@PathVariable("city")String city) {
		List<Restaurant> restaurants = restaurantService.getRestaurantsByCity(city);
		return restaurants;
	}

	@RequestMapping(value = "/city/{city}/{name}", method = RequestMethod.GET)
	public @ResponseBody List<? extends Object> getRestaurantsByCity(@PathVariable("city")String city, @PathVariable("name")String name) {
		Restaurant restaurant = new Restaurant();
		restaurant.setCity(city);
		restaurant.setName(name);
		List<Restaurant> restaurants = restaurantService.getRestaurants(restaurant);
		return restaurants;
	}

	@RequestMapping(method = RequestMethod.POST, consumes="application/json")
	public @ResponseBody Restaurant createRestaurant(@RequestBody Restaurant restaurant) {
		restaurant = restaurantService.saveRestaurant(restaurant);
		return restaurant;
	}
	
	@RequestMapping(value = "/import", method = RequestMethod.POST, consumes="application/json")
	public @ResponseBody List<? extends Object> importRestaurants(@RequestBody List<LinkedHashMap> inputData) {
		List<Restaurant> restaurants = new ArrayList<Restaurant>();
		for (LinkedHashMap<String,String> map : inputData) {
			Restaurant restaurant = new Restaurant();
			String name = map.get("name");
			if (StringUtil.isEmpty(name)) {
				continue;
			}
			restaurant.setName(name);	
			String city = map.get("city");
			if (StringUtil.isEmpty(city)) {
				continue;
			}
			restaurant.setCity(city);
			String stateCode = map.get("stateCode");
			if (StringUtil.isEmpty(stateCode)) {
				continue;
			}
			restaurant.setStateCode(stateCode);
			String countryCode = map.get("countryCode");
			if (StringUtil.isEmpty(countryCode)) {
				countryCode = Locale.US.getCountry();
			}
			restaurant.setCountryCode(countryCode);
			String postalCode = map.get("postalCode");
			if (!StringUtil.isEmpty(postalCode)) {
				restaurant.setPostalCode(postalCode);
			}
			String address = map.get("address");
			if (!StringUtil.isEmpty(address)) {
				restaurant.setAddress(address);
			}
			restaurants.add(restaurantService.saveRestaurant(restaurant));
		}
		return restaurants;
	}

	@RequestMapping(value = "/{restaurantId}", method = RequestMethod.DELETE)
	public String deleteRestaurant(@PathVariable("restaurantId")String restaurantId) {
		ID id = ID.fromString(restaurantId);
		restaurantService.removeRestaurant(id);
		return "redirect:/index";
	}	
}
