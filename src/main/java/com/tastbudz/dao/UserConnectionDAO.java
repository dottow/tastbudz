package com.tastbudz.dao;

import java.util.List;
import java.util.Set;

import com.tastbudz.model.UserConnection;


public interface UserConnectionDAO {
	public List<UserConnection> getConnections(String username);
	public List<UserConnection> getConnections(String username, String providerId);
	public UserConnection getConnection(String username, String providerId, String providerUserId);
	public UserConnection saveConnection(UserConnection userConnection);
	public List<String> getUsernamesWithConnection(String providerId, String providerUserId);
	public Set<String> getUsernamesConnectedTo(String providerId, Set<String> providerUserIds);
	public void removeConnections(String username, String providerId);
	public void removeConnection(String username, String providerId, String providerUserId);
}
