package com.tastbudz.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import com.tastbudz.json.PropertyListSerializer;


@Entity
@Table(name="tstbdz_user")
@JsonSerialize(using=PropertyListSerializer.class)
public final class User extends PersistentEntity implements UserDetails {
	private static final long serialVersionUID = 2236662996587870009L;

	private final static String TASTBUDZ = "Tastbudz";
	
	@Column(name="username", nullable=false, unique=true)
	private String username;
	@Column(name="provider", nullable=false)
	private String provider;
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
		setProvider(TASTBUDZ);
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getProvider() {
		return provider;
	}
	
	public void setProvider(String provider) {
		this.provider = provider;
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
	
	@Override
	public PersistentEntity makeQueryCriteria() {
		User clone = (User)super.makeQueryCriteria();
		clone.setProvider(null);
		clone.setLocked(false);
		return clone;
	}
	
	public String toString() {
		return "User: "+username+" (provider="+provider+")";
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return AuthorityUtils.createAuthorityList("ROLE_USER");
	}

	public boolean isAccountNonExpired() {
		return true;
	}

	public boolean isAccountNonLocked() {
		return !locked;
	}

	public boolean isCredentialsNonExpired() {
		return true;
	}

	public boolean isEnabled() {
		return true;
	}
}
