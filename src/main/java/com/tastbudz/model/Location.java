package com.tastbudz.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.tastbudz.util.Strings;




@Entity
@Table(name="tstbdz_location")
public final class Location implements Serializable, Comparable {
	private static final long serialVersionUID = 3182669251691316155L;
	@Id
	@Type(type="ID")
    @Column(name="restaurant_id", nullable=false)
    private ID restaurantId;
    @ElementCollection(fetch=FetchType.EAGER)
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
	
	public Location() {
		streetAddressList = new ArrayList<String>();
	}
	
	public ID getRestaurantId() {
		return restaurantId;
	}
	
	public void setRestaurantId(ID restaurantId) {
		this.restaurantId = restaurantId;
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
	
	@Override
	public String toString() {
		StringBuffer b = new StringBuffer();
		String address = getAddress();
		if (!Strings.isEmpty(address)) {
			b.append(address);
			b.append("\n");
		}
		b.append(city);
		b.append(", ");
		b.append(stateCode);
		if (!Strings.isEmpty(postalCode)) {
			b.append(" ");
			b.append(postalCode);
		}
		return b.toString();
	}

	public int compareTo(Object o) {
		if (o instanceof Location) {
			Location other = (Location)o;
			int i = Strings.compare(countryCode, other.countryCode);
			if (i != 0) return i;
			
			i = Strings.compare(stateCode, other.stateCode);
			if (i != 0) return i;

			i = Strings.compareWithNullMatch(postalCode, other.postalCode);
			if (i != 0) return i;
						
			i = Strings.compareWithNullMatch(crossStreet, other.crossStreet);
			if (i != 0) return i;
			
			i = streetAddressList.size() - other.streetAddressList.size();
			if (i != 0) return i;
			
			int index=0;
			for (String address : streetAddressList) {
				i = Strings.compare(address, other.streetAddressList.get(index++));
				if (i != 0) return i;
			}
			return 0;
		}
		return -1;
	}	
}
