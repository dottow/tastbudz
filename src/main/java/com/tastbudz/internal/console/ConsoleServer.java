package com.tastbudz.internal.console;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.tastbudz.config.StandaloneConfig;


public class ConsoleServer {
	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(StandaloneConfig.class);

		Console console = context.getBean(Console.class);
		String environment = System.getProperty("environment");
		if (environment == null) {
			console.printf("environment property must be specified on command line!\n");
			System.exit(-1);
		}
		console.printf("Starting Tastbudz Console on environment: %s\n", environment);



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
        		runnable = context.getBean(RestaurantConsole.class);
        	}
        	else if ("C".equalsIgnoreCase(input)) {
        		runnable = context.getBean(CuisineConsole.class);
        	}
        	else if ("M".equalsIgnoreCase(input)) {
        		runnable = context.getBean(MenuConsole.class);
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
