package com.tastbudz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tastbudz.service.MenuService;
import com.tastbudz.service.RestaurantService;

@Controller
@RequestMapping("/")
public class MainController {
	@Autowired
	private RestaurantService restaurantService;
	@Autowired
	private MenuService menuService;
	
	
	@RequestMapping(method = RequestMethod.GET)
	public String home() {
		return "home";
	}

	
}
