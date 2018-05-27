package com.twitterapi.automation.tweets;

import org.testng.annotations.BeforeClass;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.Endpoints;
import resources.Paths;
import resources.Utilities;
import org.testng.annotations.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class GetTweetTimelineTest {	
	static RequestSpecification requestSpec;
	static ResponseSpecification responseSpec;
	
	@BeforeClass
	public void setup(){
		requestSpec = Utilities.getRequestSpecification();
		requestSpec.queryParam("user_id", "Rest Assured");
		requestSpec.basePath(Paths.STATUSES); 
		
		responseSpec = Utilities.getResponseSpecification();
	}

	@Test
	public void readTweets(){
		given()
			.spec(requestSpec)
		.when()
			.get(Endpoints.STATUSES_USER_TIMELINE)
		.then()
			//.log().all()
			.spec(responseSpec)
			.body("user.screen_name", hasItem("RestAssured19"));
			
	}
	
}
