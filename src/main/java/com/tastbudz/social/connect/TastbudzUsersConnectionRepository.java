package com.tastbudz.social.connect;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.hibernate.SessionFactory;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.util.Assert;

import com.tastbudz.dao.UserConnectionDAO;
import com.tastbudz.model.User;
import com.tastbudz.service.AccountService;

public class TastbudzUsersConnectionRepository implements
		UsersConnectionRepository {
	@Inject
	protected SessionFactory sessionFactory;
	@Inject
	protected UserConnectionDAO userConnectionDAO;
	@Inject
	protected AccountService accountService;
    
	public ConnectionRepository createConnectionRepository(String userId) {
		Assert.notNull(userId);
		return new TastbudzConnectionRepository(userId);
	}

	public Set<String> findUserIdsConnectedTo(String providerId, Set<String> providerUserIds) {
		return userConnectionDAO.getUsernamesConnectedTo(providerId, providerUserIds);
	}

	public List<String> findUserIdsWithConnection(Connection<?> connection) {
		ConnectionKey key = connection.getKey();
		List<String> localUsernames = userConnectionDAO.getUsernamesWithConnection(key.getProviderId(), key.getProviderUserId());
		if (localUsernames.size() == 0) {
			try {
				User user = createUser(connection);
				user = accountService.createUser(user);
				String newUserId = user.getUsername();
				if (newUserId != null) {
					createConnectionRepository(newUserId).addConnection(connection);
					return Arrays.asList(newUserId);
				}
			}
			catch (Throwable t) {
				t.printStackTrace();
			}
		}
		return localUsernames;
	}
	
	private User createUser(Connection connection) {
		return null;
	}	
}
