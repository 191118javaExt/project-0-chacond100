package com.revature;

import static org.junit.Assert.*;

import org.junit.Test;

import com.revature.Actions.CustomerOptions;

public class CustomerTest {

	@Test
	public void testUsername() {
		CustomerOptions customer = new CustomerOptions();
		String username = customer.username("*");
		assertEquals("*",username);
	}
}
