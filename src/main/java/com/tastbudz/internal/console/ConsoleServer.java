package com.tastbudz.internal.console;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tastbudz.service.ServiceLocator;


public class ConsoleServer {
	public static void main(String[] args) {
        Console console = new Console();
		String environment = System.getProperty("environment");
		if (environment == null) {
			console.printf("environment property must be specified on command line!\n");
			System.exit(-1);
		}
		console.printf("Starting Tastbudz Console on environment: %s\n", environment);
        String configName = "spring-tastbudz.xml";
        ApplicationContext context = new ClassPathXmlApplicationContext(configName);
        ServiceLocator.initialize(context);

        String input="";
        while (true) {
        	console.printf("[R]estaurant\n");
        	console.printf("[M]enu\n");
        	console.printf("[C]uisine\n");
        	console.printf("[Q]uit\n");
        	console.printf("\n");
        	console.printf("Choice:  ");
        	input = console.readLine();
        
        	Runnable runnable = null;
        	if ("R".equalsIgnoreCase(input)) {
        		runnable = new RestaurantConsole(console);
        	}
        	else if ("C".equalsIgnoreCase(input)) {
        		runnable = new CuisineConsole(console);
        	}
        	else if ("M".equalsIgnoreCase(input)) {
        		runnable = new MenuConsole(console);
        	}
        	else if ("Q".equalsIgnoreCase(input)) {
        		System.exit(0);
        	}
        	if (runnable != null) {
        		Thread t = new Thread(runnable);
        		t.start();
        		try {
        			t.join();
        		} 
        		catch (InterruptedException e) {
        			// TODO Auto-generated catch block
        			e.printStackTrace();
        		}
        	}
        }
	}
}
