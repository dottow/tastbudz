package com.tastbudz.dao;

import java.util.List;

import com.tastbudz.model.Consumable;
import com.tastbudz.model.Restaurant;

public interface ConsumableDAO<T extends Consumable, ID> extends GenericDAO<T, ID> {
	public List<T> getConsumables(Restaurant restaurant);
}
