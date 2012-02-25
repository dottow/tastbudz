package com.tastbudz.test.dao.hibernate;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.tastbudz.dao.UserDAO;
import com.tastbudz.model.User;
import com.tastbudz.test.config.TestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback = true)
@Transactional
public class TestHibernateUserDao extends TestCase {
	@Configuration
	@Import(TestConfig.class)
	static class ContextConfig {}

	
	@Autowired
	private UserDAO dao;
	
	@Test
	public void testSaveUser() {
		User user = createUser();

		User retval = dao.save(user);
		assertEquals("The DAO's returned value of saving"+
                "must be equal to original", user, retval);
		User actualUser = dao.read(retval.getId());

		assertEquals("Actual User and DAO's returned "+
	                    "User must be equal",
	                     user, actualUser);
	}
	
	@Test
	public void testUpdateUser() {
		User user = createUser();
		user = dao.save(user);
		
		assertEquals("dottow", user.getUsername());
		
		user.setUsername("dato89");
		user = dao.save(user);
		
		assertEquals("dato89", user.getUsername());
	}
	
	private User createUser() {
		User user = new User();
		user.setEmail("david@ottow.net");
		user.setUsername("dottow");
		user.setPassword("mysecret");
		return user;
	}

}
