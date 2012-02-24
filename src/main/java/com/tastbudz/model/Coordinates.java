package com.tastbudz.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="tstbdz_coordinates")
public final class Coordinates extends PersistentEntity {
	private static final long serialVersionUID = -5757547628780512331L;
	@Column(name="longitude", nullable=false)
	private double longitude;
	@Column(name="latitude", nullable=false)
	private double latitude;
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="location_id", nullable=false)
	private Location location;
			
	private static List<String> propertyOrdering;
	
	static {
		propertyOrdering = new ArrayList<String>();
		propertyOrdering.add("longitude");
		propertyOrdering.add("latitude");
	}
	
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double d) {
		this.latitude = d;
	}
	
	public Location getLocation() {
		return location;
	}
	
	public void setLocation(Location location) {
		this.location = location;
	}
	
	@Override
	public String toString() {
		StringBuffer b = new StringBuffer();
		b.append(latitude);
		b.append(", ");
		b.append(longitude);
		return b.toString();
	}
	
	public int compareTo(Object o) {
		if (o instanceof Coordinates) {
			Coordinates other = (Coordinates)o;
			return location.compareTo(other.location);
		}
		return -1;
	}
	
	public List<String> getPropertyNames() {
		return propertyOrdering;
	}
}
