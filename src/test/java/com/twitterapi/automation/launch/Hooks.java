package com.twitterapi.automation.launch;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import com.twitterapi.automation.resource.Specifications;

import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks extends Specifications {
	public static boolean beforeClassFlag = true;
	public static boolean afterClassFlag = true;

	@Before
	public void setUpClass() {
		if (beforeClassFlag) {
			beforeClassFlag = false;
			setSoftAssert();
			getRequestSpecification();
			getResponseSpecification();
		}
	}

	@After
	public void tearDownClass() {
		if(afterClassFlag){
			afterClassFlag = false;
			softAssert.assertAll();
		}
	}
}
