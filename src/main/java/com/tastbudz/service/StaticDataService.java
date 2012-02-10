package com.tastbudz.service;

import java.util.List;

import com.tastbudz.model.Cuisine;

public interface StaticDataService {
    public Cuisine saveCuisine(Cuisine cuisine);
    public void removeCuisine(Cuisine cuisine);
    public List<Cuisine> getCuisines();
}
