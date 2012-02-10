package com.tastbudz.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name="tstbdz_location")
public final class Location extends PersistentEntity {
	private static final long serialVersionUID = 3182669251691316155L;
	@ElementCollection
	@Column(name="street_address")
	private List<String> streetAddressList;
	@Column(name="cross_street")
	private String crossStreet;
	@Column(name="city", nullable=false)
	private String city;
	@Column(name="state_code", nullable=false)
	private String stateCode;
	@Column(name="postal_code")
	private String postalCode;
	@Column(name="country_code", nullable=false)
	private String countryCode;
	@OneToMany(mappedBy = "location", cascade = {CascadeType.ALL})    
	private Set<Coordinates> coordinates;
	
	public Location() {
		streetAddressList = new ArrayList<String>();
		coordinates = new HashSet<Coordinates>();
	}
	
	public void setAddress(String ... address) {
		streetAddressList.clear();
		for (String s : address) {
			streetAddressList.add(s);
		}
	}
	
	public String getAddress() {
		StringBuffer b = new StringBuffer();
		for (String s : streetAddressList) {
			if (b.length() > 0) b.append("\n");
			b.append(s);
		}
		return b.toString();
	}
	
	public List<String> getStreetAddressList() {
		return streetAddressList;
	}
	public void setStreetAddressList(List<String> streetAddressList) {
		this.streetAddressList = streetAddressList;
	}
	public String getCrossStreet() {
		return crossStreet;
	}
	public void setCrossStreet(String crossStreet) {
		this.crossStreet = crossStreet;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getStateCode() {
		return stateCode;
	}
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	
	public Set<Coordinates> getCoordinates() {
		return coordinates;
	}
	public void setCoordinates(Set<Coordinates> coordinates) {
		this.coordinates = coordinates;
	}

	@Override
	public String toString() {
		StringBuffer b = new StringBuffer();
		b.append(super.toString());
		b.append("\n");
		b.append(getAddress());
		b.append("\n");
		b.append(city);
		b.append(", ");
		b.append(stateCode);
		b.append(" ");
		b.append(postalCode);
		if (coordinates.size() > 0) {
			b.append("\n");
			b.append("Coordinates:\n");
			for (Coordinates c : coordinates) {
				b.append(c.toString());
			}
		}
		return b.toString();
	}	
}
