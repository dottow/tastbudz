package com.tastbudz.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.tastbudz.internal.console.Console;
import com.tastbudz.internal.console.CuisineConsole;
import com.tastbudz.internal.console.MenuConsole;
import com.tastbudz.internal.console.RestaurantConsole;

@Configuration
@Import({AppConfig.class})
public class StandaloneConfig {
	@Bean
	public Console getConsole() {
		return new Console();
	}
	
	@Bean
	public RestaurantConsole getRestaurantConsole() {
		return new RestaurantConsole();
	}
	
	@Bean 
	public CuisineConsole getCuisineConsole() {
		return new CuisineConsole();
	}
	
	@Bean
	public MenuConsole getMenuConsole() {
		return new MenuConsole();
	}
}
