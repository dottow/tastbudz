package com.tastbudz.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import com.tastbudz.json.PropertyListSerializer;
import com.tastbudz.util.Strings;

@Entity
@Table(name="tstbdz_drink")
@JsonSerialize(using=PropertyListSerializer.class, include = Inclusion.NON_NULL)
public class Drink extends AbstractConsumable {
	private static final long serialVersionUID = -1250636411261888003L;

	public int compareTo(Object o) {
		if (o instanceof Drink) {
			Drink other = (Drink)o;
			int i = getRestaurant().compareTo(other.getRestaurant());
			if (i != 0) return i;
			
			return Strings.compare(getName(), other.getName());
		}
		return -1;
	}
}
