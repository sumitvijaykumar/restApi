package resources;

import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.authentication.AuthenticationScheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static io.restassured.RestAssured.*;

public class Utilities {
	public static String ENDPOINT;

	public static RequestSpecBuilder REQUEST_BUILDER;
	public static RequestSpecification REQUEST_SPECIFICATION;

	public static ResponseSpecBuilder RESPONSE_BUILDER;
	public static ResponseSpecification RESPONSE_SPECIFICATION;
	
	public static void setEndpoint(String endpoint){
		
	}
	
	public static RequestSpecification getRequestSpecification(){
		AuthenticationScheme authScheme = RestAssured.oauth(Creds.CONSUMER_KEY, Creds.CONSUMER_SECRET, Creds.ACCESS_TOKEN, Creds.ACCESS_SECRET);
		REQUEST_BUILDER = new RequestSpecBuilder();
		REQUEST_BUILDER.setBaseUri(Paths.BASE_URI);
		REQUEST_BUILDER.setAuth(authScheme);
		REQUEST_SPECIFICATION = REQUEST_BUILDER.build();
		return REQUEST_SPECIFICATION;
	}
	
	public static ResponseSpecification getResponseSpecification(){
		RESPONSE_BUILDER = new ResponseSpecBuilder();
		RESPONSE_BUILDER.expectStatusCode(200);
		//RESPONSE_BUILDER.expectResponseTime(lessThan(3L), TimeUnit.SECONDS);
		RESPONSE_SPECIFICATION = RESPONSE_BUILDER.build();
		return RESPONSE_SPECIFICATION;
	}
	
	public static RequestSpecification createQueryParam(RequestSpecification requestSpec, String key, String value){
		return requestSpec.queryParam(key, value);
	}

	public static RequestSpecification createQueryParam(RequestSpecification requestSpec, Map<String, String> stringMap){
		return requestSpec.queryParams(stringMap);
	}
	
	public static RequestSpecification createPathParam (RequestSpecification requestSpec, String key, String value){
		return requestSpec.pathParam(key, value);
	}
	
	public static Response getResponse(){
		return given().get(ENDPOINT);
	}

	public static Response getResponse(RequestSpecification requestSpec, String httpMethod){
		REQUEST_SPECIFICATION.spec(requestSpec);
		Response response = null;
		if(httpMethod.equalsIgnoreCase("get")){
			response = given().spec(REQUEST_SPECIFICATION).get(ENDPOINT);
		} else if(httpMethod.equalsIgnoreCase("put")){
			response = given().spec(REQUEST_SPECIFICATION).put(ENDPOINT);
		} else if(httpMethod.equalsIgnoreCase("post")){
			response = given().spec(REQUEST_SPECIFICATION).post(ENDPOINT);
		} else if(httpMethod.equalsIgnoreCase("delete")){
			response = given().spec(REQUEST_SPECIFICATION).delete(ENDPOINT);
		} else {
			System.out.println("invalid http method");
		}
		response.then().log().all();
		response.then().spec(RESPONSE_SPECIFICATION);
		return response;
	}
	
	public static JsonPath getJsonPath(Response response){
		return new JsonPath(response.asString());
	}
	
	public static XmlPath getXmlPath(Response response){
		return new XmlPath(response.asString());
	}
	
	public static void setContentType(ContentType type){
		given().contentType(type);
	}
	
}
