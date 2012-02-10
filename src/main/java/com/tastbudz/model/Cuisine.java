package com.tastbudz.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="tstbdz_cuisine")
public final class Cuisine extends PersistentEntity {
	private static final long serialVersionUID = -7985869638297854281L;
	@Column(name = "name")
	private String name;

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
}
