package com.tastbudz.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tastbudz.dao.UserConnectionDAO;
import com.tastbudz.model.UserConnection;
import com.tastbudz.service.ThirdPartyService;

@Service
public class ThirdPartyServiceImpl implements ThirdPartyService {
	@Autowired
	UserConnectionDAO userConnectionDAO;

	@Transactional(readOnly=true)
	public Set<String> getUsernames(String providerId, Set<String> providerUserIds) {
		return userConnectionDAO.getUsernamesConnectedTo(providerId, providerUserIds);
	}

	@Transactional(readOnly=true)
	public List<String> getUsernamesWithConnection(String providerId,  String providerUserId) {
		return userConnectionDAO.getUsernamesWithConnection(providerId, providerUserId);
	}

	@Transactional(readOnly=true)
	public List<UserConnection> getConnections(String userId) {
		return userConnectionDAO.getConnections(userId);
	}

	@Transactional(readOnly=true)
	public List<UserConnection> getConnections(String userId, String providerId) {
		return userConnectionDAO.getConnections(userId, providerId);
	}

	@Transactional(readOnly=true)
	public UserConnection getConnection(String userId, String providerId, String providerUserId) {
		return userConnectionDAO.getConnection(userId, providerId, providerUserId);
	}

	@Transactional(readOnly=false)
	public UserConnection saveConnection(UserConnection userConnection) {
		Date updateDate = new Date();
		UserConnection existing = getConnection(userConnection.getUsername(), userConnection.getProviderId(), userConnection.getProviderUserId());
		if (existing != null) {
			existing.merge(userConnection);
			userConnection = existing;
		}
		else {
			// For new connection, we want creation and update time to be the same
			userConnection.setDateCreated(updateDate);
		}
		userConnection.setDateUpdated(updateDate);
		return userConnectionDAO.saveConnection(userConnection);
	}

	@Transactional(readOnly=false)
	public void removeConnections(String userId, String providerId) {
		userConnectionDAO.removeConnections(userId, providerId);
	}

	@Transactional(readOnly=false)
	public void removeConnection(String userId, String providerId,
			String providerUserId) {
		userConnectionDAO.removeConnection(userId, providerId, providerUserId);
	}

}
