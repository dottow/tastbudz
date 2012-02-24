package com.tastbudz.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.tastbudz.json.PropertyListSerializer;
import com.tastbudz.util.Strings;


@Entity
@Table(name="tstbdz_dish")
@JsonSerialize(using=PropertyListSerializer.class)
public class Dish extends AbstractConsumable {
	private static final long serialVersionUID = -1571152603252394186L;

	public int compareTo(Object o) {
		if (o instanceof Dish) {
			Dish other = (Dish)o;
			int i = getRestaurant().compareTo(other.getRestaurant());
			if (i != 0) return i;
			
			return Strings.compare(getName(), other.getName());
		}
		return -1;
	}
}
