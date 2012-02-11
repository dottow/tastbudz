package com.tastbudz.internal.data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.tastbudz.model.Cuisine;
import com.tastbudz.service.ServiceLocator;
import com.tastbudz.service.StaticDataService;

public class CuisineConsole implements Runnable {
	private Console console;
	
	public CuisineConsole(Console console) {
		this.console = console;
	}
	
	public void run() {
		console.printf("Cuisine console\n\n");
		
		String input="";
		while (!(input.equals("quit"))) {
			console.printf("? ");
			input = console.readLine();
			input = input.trim();
			if ("list".equalsIgnoreCase(input)) {
				readCuisines();
			}
			else if ("save".equalsIgnoreCase(input)) {
				saveCuisines();
			} 
		}
	}

	private void saveCuisines() {
		StaticDataService service = ServiceLocator.getInstance()
				.getStaticDataService();
		Set<Cuisine> cuisines = createCuisines();
		for (Cuisine cuisine : cuisines) {
			cuisine = service.saveCuisine(cuisine);
		}
	}

	private void readCuisines() {
		List<Cuisine> cuisines = ServiceLocator.getInstance()
				.getStaticDataService().getCuisines();
		for (Cuisine cuisine : cuisines) {
			System.out.println(cuisine);
		}
	}

	private static Set<Cuisine> createCuisines() {
		Set<String> types = getCuisineTypes();
		Set<Cuisine> cuisines = new HashSet<Cuisine>();
		for (String type : types) {
			cuisines.add(createCuisine(type));
		}
		return cuisines;
	}

	private static Cuisine createCuisine(String s) {
		Cuisine cuisine = new Cuisine();
		cuisine.setName(s);
		return cuisine;
	}

	private static Set<String> getCuisineTypes() {
		Set<String> types = new HashSet<String>();
		types.add("Afghan");
		types.add("African");
		types.add("American (New)");
		types.add("American (Traditional)");
		types.add("Argentine");
		types.add("Asian Fusion");
		types.add("Barbeque");
		types.add("Basque");
		types.add("Belgian");
		types.add("Brasseries");
		types.add("Brazilian");
		types.add("Breakfast & Brunch");
		types.add("British");
		types.add("Buffets");
		types.add("Burgers");
		types.add("Burmese");
		types.add("Cafes");
		types.add("Cajun/Creole");
		types.add("Cambodian");
		types.add("Caribbean");
		types.add("Cheesesteaks");
		types.add("Chicken Wings");
		types.add("Chinese");
		types.add("Creperies");
		types.add("Cuban");
		types.add("Delis");
		types.add("Diners");
		types.add("Ethiopian");
		types.add("Fast Food");
		types.add("Filipino");
		types.add("Fish & Chips");
		types.add("Fondue");
		types.add("Food Stands");
		types.add("French");
		types.add("Gastropubs");
		types.add("German");
		types.add("Gluten-Free");
		types.add("Greek");
		types.add("Halal");
		types.add("Hawaiian");
		types.add("Himalayan/Nepalese");
		types.add("Hot Dogs");
		types.add("Hungarian");
		types.add("Indian");
		types.add("Indonesian");
		types.add("Irish");
		types.add("Italian");
		types.add("Japanese");
		types.add("Korean");
		types.add("Kosher");
		types.add("Latin American");
		types.add("Live/Raw Food");
		types.add("Malaysian");
		types.add("Mediterranean");
		types.add("Mexican");
		types.add("Middle Eastern");
		types.add("Modern European");
		types.add("Mongolian");
		types.add("Moroccan");
		types.add("Pakistani");
		types.add("Persian/Iranian");
		types.add("Peruvian");
		types.add("Pizza");
		types.add("Polish");
		types.add("Portuguese");
		types.add("Russian");
		types.add("Sandwiches");
		types.add("Scandinavian");
		types.add("Seafood");
		types.add("Singaporean");
		types.add("Soul Food");
		types.add("Soup");
		types.add("Southern");
		types.add("Spanish");
		types.add("Steakhouses");
		types.add("Sushi Bars");
		types.add("Taiwanese");
		types.add("Tapas Bars");
		types.add("Tapas/Small Plates");
		types.add("Tex-Mex");
		types.add("Thai");
		types.add("Turkish");
		types.add("Ukrainian");
		types.add("Vegan");
		types.add("Vegetarian");
		types.add("Vietnamese");

		return types;
	}
}
