package com.tastbudz.comparator;

import java.util.Comparator;

import com.tastbudz.model.Restaurant;

public class RestaurantComparator implements Comparator {
	public int compare(Object arg0, Object arg1) {
		try {
			Restaurant r1 = (Restaurant)arg0;
			Restaurant r2 = (Restaurant)arg1;
			return r1.getName().compareTo(r2.getName());
		}
		catch (Throwable t) {
			return -1;
		}
	}
}
