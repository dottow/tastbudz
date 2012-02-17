package com.tastbudz.model;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring-test.xml")
public class TestID extends TestCase {
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
