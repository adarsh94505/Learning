package com.wsg.tests;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.wsg.testComponents.BaseTest;

public class Sample1 extends BaseTest {

	@Test
	public void adminLogin() throws IOException {

		driver.get("https://wsg-admin-test.flentisprouat.com/login.aspx");
		Assert.assertTrue(false);

	}

}