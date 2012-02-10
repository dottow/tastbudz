package com.tastbudz.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

//@Entity
//@Table(name="tstbdz_restaurant")
public class Restaurant extends PersistentEntity {
	@Column(name="name", nullable=false)
	private String name;
	private Location location;
	@Column(name="url", nullable=true)
	private String url;
	@Column(name="phone", nullable=true)
	private String phone;
	private Set<Cuisine> cuisines;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Set<Cuisine> getCuisines() {
		return cuisines;
	}
	public void setCuisines(Set<Cuisine> cuisines) {
		this.cuisines = cuisines;
	}
}
