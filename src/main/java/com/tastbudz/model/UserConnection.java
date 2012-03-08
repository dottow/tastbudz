package com.tastbudz.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Entity
@Table(name="tstbdz_user_connection", uniqueConstraints = { @UniqueConstraint( columnNames = {"username", "provider_id", "provider_user_id"})})
public final class UserConnection extends PersistentEntity {
	private static final long serialVersionUID = -1099075070870944851L;
	@Column(name="username", nullable=false)
	private String username;
	@Column(name="provider_id", nullable=false)
	private String providerId;
	@Column(name="provider_user_id", nullable=false)
	private String providerUserId;
	@Column(name="display_name")
	private String displayName;
	@Column(name="profile_url")
	private String profileUrl;
	@Column(name="image_url")
	private String imageUrl;
	@Column(name="access_token", nullable=false)
	private String accessToken;
	@Column(name="secret")
	private String secret;
	@Column(name="refresh_token")
	private String refreshToken;
	@Column(name="expire_time")
	private long expireTime;
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getProviderId() {
		return providerId;
	}
	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}
	public String getProviderUserId() {
		return providerUserId;
	}
	public void setProviderUserId(String providerUserId) {
		this.providerUserId = providerUserId;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getProfileUrl() {
		return profileUrl;
	}
	public void setProfileUrl(String profileUrl) {
		this.profileUrl = profileUrl;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getSecret() {
		return secret;
	}
	public void setSecret(String secret) {
		this.secret = secret;
	}
	public String getRefreshToken() {
		return refreshToken;
	}
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	public long getExpireTime() {
		return expireTime;
	}
	public void setExpireTime(long expireTime) {
		this.expireTime = expireTime;
	}
	
	public String toString() {
		return "UserConnection: "+username+"/"+providerId+"/"+providerUserId+"  "+displayName+","+accessToken;
	}
	
	public List<String> getPropertyNames() {
		return null;
	}

	public void merge(UserConnection connection) {
		setAccessToken(connection.getAccessToken());
		setDisplayName(connection.getDisplayName());
		setExpireTime(connection.getExpireTime());
		setImageUrl(connection.getImageUrl());
		setProfileUrl(connection.getProfileUrl());
		setRefreshToken(connection.getRefreshToken());
		setSecret(connection.getSecret());
	}

	public int compareTo(Object o) {
		if (o instanceof UserConnection) {
			UserConnection other = (UserConnection)o;
			int i = username.compareTo(other.username);
			if (i != 0) return i;
			i = providerId.compareTo(other.providerId);
			if (i != 0) return i;
			i = providerUserId.compareTo(other.providerUserId);
			if (i != 0) return i;
			return 0;
		}
		return -1;
	}
}
