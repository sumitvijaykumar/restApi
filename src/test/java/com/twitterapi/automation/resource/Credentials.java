package com.twitterapi.automation.resource;

public class Credentials {
	
	private static String api = System.getenv("Keys");
	private static String[] arrayApi = api.split(";");
	
	public static String CONSUMER_KEY = arrayApi[0];
	public static String CONSUMER_SECRET = arrayApi[1];
	public static String ACCESS_TOKEN = arrayApi[2];
	public static String ACCESS_SECRET= arrayApi[3];


}
