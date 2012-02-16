package com.tastbudz.dao.hibernate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.tastbudz.dao.CuisineDAO;
import com.tastbudz.model.Cuisine;
import com.tastbudz.model.ID;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring-test.xml")
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback = true)
@Transactional
public class TestHibernateCuisineDao extends TestCase {
	private static final String INDONESIAN = "Indonesian";
	private static final String INDIAN = "Indian";
	private static final String FRENCH = "French";
	@Autowired
	private ApplicationContext context;
	private CuisineDAO dao;

	@Before
	public void setUp() throws Exception {
		dao = (CuisineDAO) context.getBean("cuisineDAO");
	}
	
	@Test
	public void testSaveCusine() {
		Cuisine cuisine = createCuisine(INDIAN);
		Cuisine retCuisine = dao.save(cuisine);
		System.out.println(retCuisine);
		//the value returned by the DAO must be equal to
		//the original vale
		assertEquals("The DAO's returned value of saving"+
                "must be equal to original", cuisine, retCuisine);
		//look inside the database through direct Hibernate
		//the session is auto-commit, so we are sure that the
		//transaction is committed, the session is flushed
		//after each operation
		Cuisine actualCuisine = dao.read(retCuisine.getId());

		//the actual Student object in the database must be
		//equal to the original value
		assertEquals("Actual Cuisine and DAO's returned "+
	                    "Cuisine must be equal",
	                     cuisine, actualCuisine);
	}

	@Test
	public void testMultipleSaves() {
		Set<Cuisine> cuisines = createCuisines();
		for (Cuisine cuisine : cuisines) {
			cuisine = dao.save(cuisine);
			ID id = cuisine.getId();
			Cuisine dbCuisine = dao.read(id);
			assertEquals("Actual Cuisine and DAO should be equal", cuisine, dbCuisine);
		}
	}


	@Test
	public void testQuery() {
		Cuisine cuisine1 = createCuisine(FRENCH);
		Cuisine cuisine2 = createCuisine(INDIAN);
		cuisine1 = dao.save(cuisine1);
		cuisine2 = dao.save(cuisine2);

		List<Cuisine> all = dao.getAllCuisines();
		assertEquals(2, all.size());
		
		Cuisine query = new Cuisine();
		query.setName(INDIAN);
		List<Cuisine> matches = dao.getMatchingCuisines(query);
		assertEquals(1, matches.size());
	}
	
	private Set<Cuisine> createCuisines() {
		Set<String> types = getCuisineTypes();
		Set<Cuisine> cuisines = new HashSet<Cuisine>();
		for (String type : types) {
			cuisines.add(createCuisine(type));
		}
		return cuisines;
	}
	
	private Cuisine createCuisine(String s) {
		Cuisine cuisine = new Cuisine();
		cuisine.setName(s);
		return cuisine;
	}
	
	private Set<String> getCuisineTypes() {
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
