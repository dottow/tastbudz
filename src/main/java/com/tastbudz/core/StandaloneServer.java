package com.tastbudz.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tastbudz.internal.data.CuisineConsole;
import com.tastbudz.service.ServiceLocator;


public class StandaloneServer {
	public static void main(String[] args) {
		String environment = System.getProperty("environment");
		if (environment == null) {
			System.out.println("D'oh!");
			System.exit(-1);
		}
		System.out.println("Starting tastbudz environment: "+environment);
        String configName = "spring-tastbudz.xml";
        ApplicationContext context = new ClassPathXmlApplicationContext(configName);
        ServiceLocator.initialize(context);

        CuisineConsole console = new CuisineConsole();
        Thread t = new Thread(console);
        t.start();
	}
}
