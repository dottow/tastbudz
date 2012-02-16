package com.tastbudz.dao;

import java.util.List;

import com.tastbudz.model.Cuisine;
import com.tastbudz.model.ID;

public interface CuisineDAO extends GenericDAO<Cuisine, ID> {
	public List<Cuisine> getAllCuisines();
	public List<Cuisine> getMatchingCuisines(Cuisine cuisine);
}
