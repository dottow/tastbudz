package com.tastbudz.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import com.tastbudz.json.RestaurantSerializer;

@Entity
@Table(name="tstbdz_restaurant")
@JsonSerialize(using=RestaurantSerializer.class, include = Inclusion.NON_NULL)
public class Restaurant extends PersistentEntity {
	private static final long serialVersionUID = -6982827718579490636L;
	@Column(name="name", nullable=false)
	private String name;
	@OneToOne(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    @PrimaryKeyJoinColumn
    private Location location;
	@Column(name="url", nullable=true)
	private String url;
	@Column(name="phone", nullable=true)
	private String phone;
	@ManyToMany(cascade = CascadeType.PERSIST, fetch=FetchType.EAGER)
	private Set<Cuisine> cuisines;
	
	public Restaurant() {
		setLocation(new Location());
	}
	
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
		if (location != null) {
			location.setRestaurantId(getId());
		}
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
	public void setAddress(String ... address) {
		location.setAddress(address);
	}
	public void setAddress(String address) {
		location.setAddress(address);
	}
	public String getAddress() {
		return location.getAddress();
	}
	public String getCrossStreet() {
		return location.getCrossStreet();
	}
	public void setCrossStreet(String crossStreet) {
		location.setCrossStreet(crossStreet);
	}
	public String getCity() {
		return location.getCity();
	}
	public void setCity(String city) {
		location.setCity(city);
	}
	public String getStateCode() {
		return location.getStateCode();
	}
	public void setStateCode(String stateCode) {
		location.setStateCode(stateCode);
	}
	public String getPostalCode() {
		return location.getPostalCode();
	}
	public void setPostalCode(String postalCode) {
		location.setPostalCode(postalCode);
	}
	public String getCountryCode() {
		return location.getCountryCode();
	}
	public void setCountryCode(String countryCode) {
		location.setCountryCode(countryCode);
	}

	@Override
	public String toString() {
		StringBuffer b = new StringBuffer();
		b.append(name);
		b.append("\n");
		if (!StringUtils.isBlank(url)) {
			b.append(url);
			b.append("\n");
		}
		if (!StringUtils.isBlank(phone)) {
			b.append(phone);
			b.append("\n");
		}
		b.append(location.toString());
		return b.toString();
	}
}
