package com.tastbudz.test.model;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.tastbudz.model.ID;
import com.tastbudz.test.config.TestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
public class TestID extends TestCase {
	@Configuration
	@Import(TestConfig.class)
	static class ContextConfig {
	}

	@Test
	public void testSaveCusine() {
		ID id = new ID();
		String s = id.toString();
		System.out.println("ID: "+id.getDetails());
		ID other = ID.fromString(s);
		System.out.println("OTHER: "+other.getDetails());
		assertEquals("IDs should be the same!", id, other);
	}
}
