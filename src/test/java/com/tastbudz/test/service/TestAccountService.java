package com.tastbudz.test.service;

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

import com.tastbudz.exception.UserException;
import com.tastbudz.model.User;
import com.tastbudz.service.AccountService;
import com.tastbudz.test.config.TestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback = true)
@Transactional
public class TestAccountService extends TestCase {
	@Configuration
	@Import(TestConfig.class)
	static class ContextConfig {
	}
	
	@Autowired
	private AccountService service;
	
	@Test
	public void testSaveUser() {
		User user = createUser();
		User retval = service.createUser(user);
		
		assertEquals(user, retval);
		assertFalse("Password should not be same as clear-text", "mypassword".equals(retval.getPassword()));
		
		User actualUser = service.readUser(retval.getId());

		assertEquals(user, actualUser);
		
		try {
			User newUser = createUser();
			service.createUser(newUser);
			assertFalse("We cannot create duplicate user with same username!", true);
		}
		catch (Throwable t) {}
	}

	@Test
	public void testCredentials() {
		User user = createUser();
		service.createUser(user);
		
		User existing = null;
		
		try {
			existing = service.authenticate("dottow", "mypassword");
			assertEquals(user, existing);
		}
		catch (UserException ue) {
			assertFalse("We should not have an exception", true);
		}
		
		existing = null;
		try {
			existing = service.authenticate("dottow", "hacker");
			assertFalse("We should not be here.  Exception should have been thrown", true);
		}
		catch (Throwable t) {}
	}

	private User createUser() {
		User user = new User();
		user.setUsername("dottow");
		user.setPassword("mypassword");
		user.setEmail("david@ottow.net");
		return user;
	}

}
