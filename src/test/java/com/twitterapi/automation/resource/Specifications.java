package com.twitterapi.automation.resource;

import io.restassured.RestAssured;
import io.restassured.authentication.AuthenticationScheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import org.assertj.core.api.SoftAssertions;

import com.twitterapi.automation.contants.ResourcePaths;

public class Specifications {
	protected static SoftAssertions softAssert;
	protected static RequestSpecification requestSpecification;
	protected static ResponseSpecification responseSpecification;
	
	public static AuthenticationScheme getAuthScheme(){
		AuthenticationScheme authScheme;
		authScheme = RestAssured.oauth(Credentials.CONSUMER_KEY, Credentials.CONSUMER_SECRET, Credentials.ACCESS_TOKEN, Credentials.ACCESS_SECRET);
		return authScheme;
	}
	
	public static void getRequestSpecification(){
		RequestSpecBuilder requestBuilder = new RequestSpecBuilder();
		requestBuilder.setBaseUri(ResourcePaths.BASE_URI);
		requestBuilder.setAuth(getAuthScheme());
		requestSpecification = requestBuilder.build();
	}	
	
	public static void getResponseSpecification(){
		ResponseSpecBuilder responseBuilder = new ResponseSpecBuilder();
		responseBuilder.expectStatusCode(200);
		responseSpecification = responseBuilder.build();
	}
	
	public static void setSoftAssert(){
		softAssert = new SoftAssertions();
	}
	
	
}
