package com.twitterapi.automation.launch;

import static io.restassured.RestAssured.given;
import net.thucydides.core.annotations.Steps;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
//import net.thucydides.core.annotations.Steps;


import com.twitterapi.automation.resource.Specifications;
import com.twitterapi.automation.resource.Utilities;
import com.twitterapi.automation.resource.contants.ResourcePaths;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class TweetTimeline extends Specifications {
	
	private Response response;
	private RequestSpecification tweetRequest;
	
	@Steps
	private PostStatus existingTweet; 

	private long existingTweetId;

	@Given("^there already exists a tweet with a status \"([^\"]*)\"$")
	public void there_exists_a_tweet(String arg1) {
		existingTweet.setStatus(arg1);
		Response res = existingTweet.postQuery();
		
	}

	@Given("^user wants update status with \"([^\"]*)\"$")
	public void user_wants_update_status_with(String status) {
		tweetRequest = given().spec(requestSpecification).param("status",
				status);
	}

	@Given("^wants to reply to status with text \"([^\"]*)\"$")
	public void wants_to_reply_to(String arg1) {
		tweetRequest = tweetRequest.param("in_reply_to_status_id",
				existingTweetId);
	}

	@Given("^wants to attach a link$")
	public void wants_to_attach() {
		tweetRequest = tweetRequest.param("attachment_url",
				ResourcePaths.STATUS_ATTACHMENT);
	}

	@Given("^content \"([^\"]*)\" sensitive$")
	public void content_is_sensitive(String sensitive) {
		if (sensitive.replace(" ", "").trim().equalsIgnoreCase("is")) {
			tweetRequest = tweetRequest.param("possibly_sensitive", "true");
		}
	}

	@Given("^geographical location is of \"([^\"]*)\"$")
	public void geographical_location_is_and(String location) {
		
		tweetRequest = tweetRequest.param("lat",
				Float.parseFloat(Utilities.getCoordinates(location).split(",")[0]));
		tweetRequest = tweetRequest.param("long",
				Float.parseFloat(Utilities.getCoordinates(location).split(",")[1]));
	}

	@When("^user posts the status$")
	public void user_posts_the_status() {
		String currectTweetId = tweetRequest
				.when().post(ResourcePaths.STATUS_UPDATE)
				.then().extract().path("id").toString();
		
		response = given().spec(requestSpecification).param("id",currectTweetId)
					.when().get(ResourcePaths.STATUS_SHOW);
		
	}

	@Then("^status should have text \"([^\"]*)\"$")
	public void status_should_have_text(String status) {
		softAssert.assertThat(response.body().path("text").toString()).contains(status);
	}

	@Then("^status should have existing status \"([^\"]*)\"$")
	public void status_should_have_existing_status(String expectedStatus) {
		String existingTweetBody = given().spec(requestSpecification)
				.param("id", existingTweetId).when()
				.get(ResourcePaths.STATUS_SHOW).body().path("text");
		
		softAssert.assertThat(existingTweetBody).isEqualTo(expectedStatus);

	}

	@Then("^status should have expected attachment$")
	public void status_should_have_url() {
		String responseUrl = response.path("entities.urls.expanded_url").toString();
		String formattedResponseUrl = responseUrl.replace("[", "").replace("]", "");
		
		softAssert.assertThat(formattedResponseUrl).isEqualTo(ResourcePaths.STATUS_ATTACHMENT);
	}

	@Then("^content should not be sensitive$")
	public void content_should_be_sensitive() {
		softAssert.assertThat(response.path("quoted_status.possibly_sensitive").toString()).isEqualTo("false");
	}

	@Then("^should have location \"([^\"]*)\"$")
	public void should_have_location(String location) {
		String coordinates = response.path("geo.coordinates")
				.toString();
		String formattedResponse = coordinates.replace("[", "").replace("]", "");
		softAssert.assertThat(formattedResponse).isEqualTo(Utilities.getCoordinates(location).toString());
	}

	@Given("^user deletes existing status$")
	public void delete_Status() {
		try{
			
			String[] allStatusIds;
			
			allStatusIds = given().spec(requestSpecification)
			.when().get(ResourcePaths.USER_TIMELINE)
			.then().extract().path("id").toString().split(",");
			
			for(String statusId : allStatusIds){
				String formattedStatus = statusId.replace("[", "").replace("]", "").trim();
				given().spec(requestSpecification).param("id", formattedStatus)
				.when().post(ResourcePaths.STATUS_DELETE + formattedStatus + ".json")
				.then().extract().statusCode();
			}
			
		}catch(Exception e){
			System.out.println("No status found to be deleted.");
		}
		
	}

	@When("^user updates profile image$")
	public void user_updates_profile_image() {

	}

	@Then("^user should see profile image$")
	public void user_should_see_profile_image() {

	}

}
