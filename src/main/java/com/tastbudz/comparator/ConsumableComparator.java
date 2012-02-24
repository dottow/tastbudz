package com.tastbudz.comparator;

import java.util.Comparator;

import com.tastbudz.model.Consumable;

public class ConsumableComparator implements Comparator {
	public int compare(Object arg0, Object arg1) {
		try {
			Consumable c1 = (Consumable)arg0;
			Consumable c2 = (Consumable)arg1;
			return c1.getName().compareTo(c2.getName());
		}
		catch (Throwable t) {
			return -1;
		}
	}
}
