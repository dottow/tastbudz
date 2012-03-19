package com.tastbudz.dao.hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tastbudz.dao.UserConnectionDAO;
import com.tastbudz.model.ID;
import com.tastbudz.model.UserConnection;


@Repository("userConnectionDAO")
@Transactional( propagation = Propagation.MANDATORY )
public class UserConnectionHibernateDAO extends GenericHibernateDAO<UserConnection, ID> implements UserConnectionDAO {
	public List<UserConnection> getConnections(String username) {
		UserConnection connection = new UserConnection();
		connection.setUsername(username);
		return getByExample(connection);
	}

	public List<UserConnection> getConnections(String username, String providerId) {
		UserConnection connection = new UserConnection();
		connection.setUsername(username);
		connection.setProviderId(providerId);
		return getByExample(connection);
	}

	public UserConnection getConnection(String username, String providerId, String providerUserId) {
		UserConnection connection = new UserConnection();
		connection.setUsername(username);
		connection.setProviderId(providerId);
		connection.setProviderUserId(providerUserId);
		
		List<UserConnection> connections = getByExample(connection);
		if (connections == null || connections.size() == 0) {
			return null;
		}
		
		return connections.get(0);
	}

	public UserConnection saveConnection(UserConnection connection) {
		sessionFactory.getCurrentSession().merge(connection);
		return connection;
	}
	
	public void removeConnections(String username, String providerId) {
		List<UserConnection> connections = getConnections(username, providerId);
		if (connections != null) {
			for (UserConnection connection : connections) {
		    	sessionFactory.getCurrentSession().delete(connection);
			}
		}
	}
	
	public void removeConnection(String username, String providerId, String providerUserId) {
		UserConnection connection = getConnection(username, providerId, providerUserId);
		if (connection != null) {
			sessionFactory.getCurrentSession().delete(connection);
		}
	}

	@Override
	public List<String> getUsernamesWithConnection(String providerId, String providerUserId) {
		UserConnection connection = new UserConnection();
		connection.setProviderId(providerId);
		connection.setProviderUserId(providerUserId);
		List<UserConnection> connections = getByExample(connection);
		List<String> usernames = new ArrayList<String>();
		if (connections != null) {
			for (UserConnection conn : connections) {
				usernames.add(conn.getUsername());
			}
		}
		return usernames;
	}

	@Override
	public Set<String> getUsernamesConnectedTo(String providerId, Set<String> providerUserIds) {

		/**
		 * public Set<String> findUserIdsConnectedTo(String providerId, Set<String> providerUserIds) {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("providerId", providerId);
			parameters.addValue("providerUserIds", providerUserIds);
			final Set<String> localUserIds = new HashSet<String>();
			return new NamedParameterJdbcTemplate(jdbcTemplate).query("select userId from " + tablePrefix + "UserConnection where providerId = :providerId and providerUserId in (:providerUserIds)", parameters,
				new ResultSetExtractor<Set<String>>() {
					public Set<String> extractData(ResultSet rs) throws SQLException, DataAccessException {
						while (rs.next()) {
							localUserIds.add(rs.getString("userId"));
						}
						return localUserIds;
					}
				});
		}

		 */

		return null;
	}

}
