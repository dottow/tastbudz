package com.tastbudz.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tastbudz.exception.RestaurantNotFoundException;
import com.tastbudz.model.Consumable;
import com.tastbudz.model.Dish;
import com.tastbudz.model.Drink;
import com.tastbudz.model.ID;
import com.tastbudz.model.Menu;
import com.tastbudz.model.Restaurant;
import com.tastbudz.service.MenuService;
import com.tastbudz.service.RestaurantService;
import com.tastbudz.util.Strings;

@Controller
@RequestMapping("/restaurants")
public class RestaurantController {
	@Autowired
	private RestaurantService restaurantService;
	@Autowired
	private MenuService menuService;
	
	@RequestMapping(value = "/", method = RequestMethod.POST, consumes="application/json")
	public @ResponseBody Restaurant create(@RequestBody Map map) {
		Restaurant restaurant = buildRestaurant(map);
		if (restaurant != null) {
			restaurant = restaurantService.createRestaurant(restaurant);
			return restaurant;
		}
		return null;
	}

	@RequestMapping(value = "/{restaurantId}", method = RequestMethod.GET)
	public @ResponseBody Restaurant read(@PathVariable("restaurantId")String restaurantId) {
		ID id = ID.fromString(restaurantId);
		Restaurant restaurant = restaurantService.readRestaurant(id);
		return restaurant;
	}

	@RequestMapping(value = "/{restaurantId}", method = RequestMethod.PUT, consumes="application/json")
	public @ResponseBody Restaurant update(@PathVariable("restaurantId")String restaurantId, @RequestBody Map map) {
		ID id = ID.fromString(restaurantId);
		Restaurant restaurant = buildRestaurant(map);
		restaurant.setId(id);
		restaurantService.updateRestaurant(restaurant);
		return restaurant;
	}

	
	@RequestMapping(value = "/{restaurantId}", method = RequestMethod.DELETE)
	public String delete(@PathVariable("restaurantId")String restaurantId) {
		ID id = ID.fromString(restaurantId);
		restaurantService.deleteRestaurant(id);
		return "redirect:/index";
	}	

	@RequestMapping(value = "/city/{city}", method = RequestMethod.GET)
	public @ResponseBody List<? extends Object> getByCity(@PathVariable("city")String city) {
		List<Restaurant> restaurants = restaurantService.getRestaurantsByCity(city);
		return restaurants;
	}

	@RequestMapping(value = "/city/{city}/{name}", method = RequestMethod.GET)
	public @ResponseBody List<? extends Object> getByCityLikeName(@PathVariable("city")String city, @PathVariable("name")String name) {
		List<Restaurant> restaurants = restaurantService.getRestaurantsByCity(city, name);
		return restaurants;
	}
	
	@RequestMapping(value = "/import", method = RequestMethod.POST, consumes="application/json")
	public @ResponseBody List<? extends Object> importRestaurants(@RequestBody List<Map> inputData) {
		List<Restaurant> restaurants = new ArrayList<Restaurant>();
		for (Map map : inputData) {
			Restaurant restaurant = buildRestaurant(map);
			if (restaurant != null) {
				restaurants.add(restaurantService.createRestaurant(restaurant));
			}
		}
		return restaurants;
	}

	@RequestMapping(value = "/{restaurantId}/menu", method = RequestMethod.GET)
	public @ResponseBody Menu getMenu(@PathVariable("restaurantId")String restaurantId) {
		ID id = ID.fromString(restaurantId);
		Restaurant restaurant = restaurantService.readRestaurant(id);
		if (restaurant == null)
			throw new RestaurantNotFoundException(restaurant);
		
		Menu menu = menuService.getMenu(restaurant);
		return menu;
	}
	
	@RequestMapping(value = "/{restaurantId}/menu", method = RequestMethod.POST, consumes="application/json")
	public @ResponseBody List<? extends Object> importMenu(@PathVariable("restaurantId")String restaurantId, @RequestBody Map inputData) {
		ID id = ID.fromString(restaurantId);
		Restaurant restaurant = restaurantService.readRestaurant(id);
		if (restaurant == null)
			throw new RestaurantNotFoundException(restaurant);
		
		List<Consumable> menuItems = new ArrayList<Consumable>();
		List<Map> dishes = (List<Map>)inputData.get("dishes");
		if (dishes != null) {
			for (Map<String,String> dishData : dishes) {
				Dish dish = new Dish();
				String name = dishData.get("name");
				if (Strings.isEmpty(name)) {
					continue;
				}
				dish.setRestaurant(restaurant);
				dish.setName(name);
				dish = menuService.saveDish(dish);
				menuItems.add(dish);
			}			
		}
		List<Map> drinks = (List<Map>)inputData.get("drinks");
		if (drinks != null) {
			for (Map<String,String> drinkData : drinks) {
				Drink drink = new Drink();
				String name = drinkData.get("name");
				if (Strings.isEmpty(name)) {
					continue;
				}
				drink.setRestaurant(restaurant);
				drink.setName(name);
				drink = menuService.saveDrink(drink);
				menuItems.add(drink);
			}			
		}
		return menuItems;
	}	
	
	private Restaurant buildRestaurant(Map map) {
		Restaurant restaurant = new Restaurant();
		String name = (String)map.get("name");
		if (Strings.isEmpty(name)) {
			return null;
		}
		restaurant.setName(name);	
		String city = (String)map.get("city");
		if (Strings.isEmpty(city)) {
			return null;
		}
		restaurant.setCity(city);
		String stateCode = (String)map.get("stateCode");
		if (Strings.isEmpty(stateCode)) {
			return null;
		}
		restaurant.setStateCode(stateCode);
		String countryCode = (String)map.get("countryCode");
		if (Strings.isEmpty(countryCode)) {
			countryCode = Locale.US.getCountry();
		}
		restaurant.setCountryCode(countryCode);
		String postalCode = (String)map.get("postalCode");
		if (!Strings.isEmpty(postalCode)) {
			restaurant.setPostalCode(postalCode);
		}
		String address = (String)map.get("address");
		if (!Strings.isEmpty(address)) {
			restaurant.setAddress(address);
		}
		String crossStreet = (String)map.get("crossStreet");
		if (!Strings.isEmpty(crossStreet)) {
			restaurant.setCrossStreet(crossStreet);
		}
		String url = (String)map.get("url");
		if (!Strings.isEmpty(url)) {
			restaurant.setUrl(url);
		}
		String phone = (String)map.get("phone");
		if (!Strings.isEmpty(phone)) {
			restaurant.setPhone(phone);
		}
		return restaurant;
	}
}
