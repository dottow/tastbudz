package com.tastbudz.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.util.Assert;

import com.tastbudz.json.PropertyListSerializer;


@Entity
@Table(name="tstbdz_user")
@JsonSerialize(using=PropertyListSerializer.class)
public final class User extends PersistentEntity {
	private static final long serialVersionUID = 2236662996587870009L;

	@Column(name="username", nullable=false, unique=true)
	private String username;
	@Column(name="email", nullable=false, unique=true)
	private String email;
	@Column(name="password", nullable=false)
	private String password;
	@Column(name="locked", nullable=false)
	private boolean locked;
	@OneToOne(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@PrimaryKeyJoinColumn
	private Profile profile;
	
	
	private static List<String> propertyOrdering;
	
	static {
		propertyOrdering = new ArrayList<String>();
		propertyOrdering.add("id");
		propertyOrdering.add("username");
		propertyOrdering.add("email");
	}

	
	public User() {
		setProfile(new Profile());
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		Assert.notNull(profile);
		this.profile = profile;
		profile.setUserId(getId());
	}

	public List<String> getPropertyNames() {
		return propertyOrdering;
	}

	@Override
	public int compareTo(Object o) {
		if (o instanceof User) {
			User other = (User)o;
			
			int i = username.compareTo(other.username);
			if (i != 0) return i;
			
			i = email.compareTo(other.email);
			if (i != 0) return i;
			
			return 0;
		}
		return -1;
	}
	
	public void merge(User user) {
		setProfile(user.getProfile());
	}
}
