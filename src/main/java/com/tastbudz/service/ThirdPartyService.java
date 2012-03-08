package com.tastbudz.service;

import java.util.List;
import java.util.Set;

import com.tastbudz.model.UserConnection;


public interface ThirdPartyService {
	public Set<String> getUsernames(String providerId, Set<String> providerUserIds);
	public List<String> getUsernamesWithConnection(String providerId, String providerUserId);
	public List<UserConnection> getConnections(String userId);
	public List<UserConnection> getConnections(String userId, String providerId);
	public UserConnection getConnection(String userId, String providerId, String providerUserId);
	public UserConnection saveConnection(UserConnection userConnection);
	public void removeConnections(String userId, String providerId);
	public void removeConnection(String userId, String providerId, String providerUserId);
}
