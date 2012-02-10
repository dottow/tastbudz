package com.tastbudz.comparator;

import java.util.Comparator;

import com.tastbudz.model.Cuisine;

public class CuisineComparator implements Comparator {
	public int compare(Object arg0, Object arg1) {
		try {
			Cuisine c1 = (Cuisine)arg0;
			Cuisine c2 = (Cuisine)arg1;
			return c1.getName().compareTo(c2.getName());
		}
		catch (Throwable t) {
			return -1;
		}
	}
}
