package com.tastbudz.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.tastbudz.util.Strings;

@Entity
@Table(name="tstbdz_cuisine")
public final class Cuisine extends PersistentEntity {
	private static final long serialVersionUID = -7985869638297854281L;
	private static final String UNKNOWN = "Unknown";
	@Column(name = "name")
	private String name;

	private static List<String> propertyOrdering;
	
	static {
		propertyOrdering = new ArrayList<String>();
		propertyOrdering.add("id");
		propertyOrdering.add("name");
	}
	
	public Cuisine() {
		setName(UNKNOWN);
	}
	
	public Cuisine(String name) {
		setName(name);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		StringBuffer b = new StringBuffer();
		b.append(super.toString());
		b.append("\n");
		b.append(name);
		return b.toString();
	}

	public int compareTo(Object o) {
		if (o instanceof Cuisine) {
			Cuisine other = (Cuisine)o;
			return Strings.compare(name, other.name);
		}
		return -1;
	}
	
	
	public List<String> getPropertyNames() {
		return propertyOrdering;
	}

}
