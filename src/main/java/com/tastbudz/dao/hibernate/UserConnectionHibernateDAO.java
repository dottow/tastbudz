package com.tastbudz.dao.hibernate;

import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Repository;

import com.tastbudz.dao.UserConnectionDAO;
import com.tastbudz.model.UserConnection;

@Repository("userConnectionDAO")
public class UserConnectionHibernateDAO implements UserConnectionDAO {
	@Autowired
	protected SessionFactory sessionFactory;
 
    @Autowired
    @Required
    public void setSessionFactory(SessionFactory factory) {
    	this.sessionFactory = factory;
    }

	public List<UserConnection> getConnections(String userId) {
		UserConnection connection = new UserConnection();
		connection.setUserId(userId);
		return getConnections(connection);
	}

	public List<UserConnection> getConnections(String userId, String providerId) {
		UserConnection connection = new UserConnection();
		connection.setUserId(userId);
		connection.setProviderId(providerId);
		return getConnections(connection);
	}

	public List<UserConnection> getConnections(String userId, String providerId, String providerUserId) {
		UserConnection connection = new UserConnection();
		connection.setUserId(userId);
		connection.setProviderId(providerId);
		connection.setProviderUserId(providerUserId);
		return getConnections(connection);
	}

	public List<UserConnection> getConnections(UserConnection connection) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(UserConnection.class);
		Example example = Example.create(connection);
		criteria.add(example);
		criteria.addOrder(Order.asc("rank"));
		return criteria.list();
	}

	public void createConnection(UserConnection connection) {
		/**
			int rank = jdbcTemplate.queryForInt("select coalesce(max(rank) + 1, 1) as rank from " + tablePrefix + "UserConnection where userId = ? and providerId = ?", userId, data.getProviderId());
			jdbcTemplate.update("insert into " + tablePrefix + "UserConnection (userId, providerId, providerUserId, rank, displayName, profileUrl, imageUrl, accessToken, secret, refreshToken, expireTime) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
					userId, data.getProviderId(), data.getProviderUserId(), rank, data.getDisplayName(), data.getProfileUrl(), data.getImageUrl(), encrypt(data.getAccessToken()), encrypt(data.getSecret()), encrypt(data.getRefreshToken()), data.getExpireTime());
		} catch (DuplicateKeyException e) {
			throw new DuplicateConnectionException(connection.getKey());
		}
		 */
	}
	
	public void updateConnection(UserConnection connection) {
		
	}
	
	public void removeConnections(String userId, String providerId) {
		
	}
	
	public void removeConnection(String userId, String providerId, String providerUserId) {
		
	}

	@Override
	public List<String> getUsernamesWithConnection(String providerId,
			String providerUserId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<String> getUsernamesConnectedTo(String providerId,
			Set<String> providerUserIds) {

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
