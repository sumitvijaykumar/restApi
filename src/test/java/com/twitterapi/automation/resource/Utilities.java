package com.twitterapi.automation.resource;

import java.util.HashMap;
import java.util.Map;

public class Utilities {

	private static Map<String, String> map = new HashMap<String, String>();
	
	public static String getCoordinates(String location){
		map.put("Pune", "18.5204, 73.8567");
		String coord = map.get(location);
		return coord;
	}
}
