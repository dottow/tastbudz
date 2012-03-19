package com.tastbudz.service.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tastbudz.comparator.ComparatorFactory;
import com.tastbudz.dao.CuisineDAO;
import com.tastbudz.model.Cuisine;
import com.tastbudz.service.StaticDataService;

@Service
@Transactional( propagation = Propagation.REQUIRES_NEW )
public class StaticDataServiceImpl implements StaticDataService {
	@Autowired
	CuisineDAO cuisineDAO;

	public Cuisine saveCuisine(Cuisine cuisine) {
		return cuisineDAO.save(cuisine);
	}

	public void removeCuisine(Cuisine cuisine) {
		cuisineDAO.delete(cuisine);
	}

	@Transactional(readOnly=true)
	public List<Cuisine> getCuisines() {
		List<Cuisine> cuisines = cuisineDAO.getAllCuisines();
		Collections.sort(cuisines, ComparatorFactory.getComparator(Cuisine.class));
		return cuisines;
	}
}
