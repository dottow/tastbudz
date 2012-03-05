package com.tastbudz.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="tstbdz_user_connection")
public final class UserConnection implements Serializable {
	private static final long serialVersionUID = -1099075070870944851L;
	@Id
	@Column(name="user_id", nullable=false)
	private String username;
	@Id
	@Column(name="provide_id", nullable=false)
	private String providerId;
	@Id
	@Column(name="provider_user_id")
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
	
	
	public String getUserId() {
		return username;
	}
	public void setUserId(String username) {
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
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof UserConnection) {
			UserConnection other = (UserConnection)o;
			if (!username.equals(other.username)) return false;
			if (!providerId.equals(other.providerId)) return false;
			if (!providerUserId.equals(other.providerUserId)) return false;
			return true;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		int hashCode = username.hashCode() ^ providerId.hashCode();
		return providerUserId == null ? hashCode : hashCode ^ providerUserId.hashCode();
	}
}
