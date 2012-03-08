package com.tastbudz.social.connect;

import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;

import com.tastbudz.service.AccountService;
import com.tastbudz.service.ThirdPartyService;

public class TastbudzUsersConnectionRepository implements
		UsersConnectionRepository {
	@Inject
	protected AccountService accountService;
	@Inject
	protected ThirdPartyService thirdPartyService;
    
	public ConnectionRepository createConnectionRepository() {
		return createConnectionRepository(null);
	}
	
	public ConnectionRepository createConnectionRepository(String userId) {
		return new TastbudzConnectionRepository(userId);
	}

	public Set<String> findUserIdsConnectedTo(String providerId, Set<String> providerUserIds) {
		return thirdPartyService.getUsernames(providerId, providerUserIds);
	}

	public List<String> findUserIdsWithConnection(Connection<?> connection) {
		ConnectionKey key = connection.getKey();
		List<String> localUsernames = thirdPartyService.getUsernamesWithConnection(key.getProviderId(), key.getProviderUserId());
		return localUsernames;
	}
}
