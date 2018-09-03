package com.twitterapi.automation.stepfiles;

import static io.restassured.RestAssured.given;
import net.thucydides.core.annotations.Steps;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
//import net.thucydides.core.annotations.Steps;




import com.twitterapi.automation.pojos.UpdateStatus;
import com.twitterapi.automation.resource.Specifications;
import com.twitterapi.automation.resource.Utilities;
import com.twitterapi.automation.resource.contants.ResourcePaths;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class UpdateStatusSteps extends Specifications {
	
	private Response underTestTweetResponse;
	private Response existingTweetResponse;
	private String existingTweetId;
	
	private RequestSpecification tweetRequest;
	
	@Steps
	private UpdateStatus existingTweet;

	@Steps
	private UpdateStatus tweetUnderTest;

	@Given("^there already exists a tweet with a status \"([^\"]*)\"$")
	public void there_exists_a_tweet(String status) {
		existingTweet.setStatus(status);
		existingTweetId = existingTweet.postQuery().then().extract().path("id").toString(); 
	}

	@Given("^user wants update status with \"([^\"]*)\"$")
	public void user_wants_update_status_with(String status) {
		tweetUnderTest.setStatus(status);
	}

	@Given("^wants to reply to status with text \"([^\"]*)\"$")
	public void wants_to_reply_to(String statusText) {
		// TODO: get tweeter id where tweeter text is statusText
		tweetUnderTest.setIn_reply_to_status_id(existingTweetId);
		
	}

	@Given("^wants to attach a link$")
	public void wants_to_attach() {
		tweetUnderTest.setAttachment_url(ResourcePaths.STATUS_ATTACHMENT);
	}

	@Given("^content \"([^\"]*)\" sensitive$")
	public void content_is_sensitive(String sensitive) {
		if (sensitive.replace(" ", "").trim().equalsIgnoreCase("is")) {
			tweetUnderTest.setPossibly_sensitive("true");
		}
		else{
			tweetUnderTest.setPossibly_sensitive("false");
		}
	}

	@Given("^geographical location is of \"([^\"]*)\"$")
	public void geographical_location_is_and(String location) {
		tweetUnderTest.setLat(location);
		tweetUnderTest.setLongi(location);
	}

	@When("^user posts the status$")
	public void user_posts_the_status() {
		underTestTweetResponse = tweetUnderTest.postQuery();
	}

	@Then("^status should have expected text$")
	public void status_should_have_text() {
		softAssert.assertThat(underTestTweetResponse.body().path("text").toString()).contains(tweetUnderTest.getStatus());
	}

	@Then("^status should be replied to former status$")
	public void status_should_have_existing_status() {
		String existingTweetBody = underTestTweetResponse.body().path("in_reply_to_status_id").toString();
		softAssert.assertThat(existingTweetBody).isEqualTo(tweetUnderTest.getIn_reply_to_status_id());

	}

	@Then("^status should have expected attachment$")
	public void status_should_have_url() {
		String responseUrl = underTestTweetResponse.body().path("quoted_status.entities.media.expanded_url").toString();
		String formattedResponseUrl = responseUrl.replace("[", "").replace("]", "").replace("\\", "");
		softAssert.assertThat(formattedResponseUrl).contains(ResourcePaths.STATUS_ATTACHMENT);
	}

	@Then("^content should not be sensitive$")
	public void content_should_be_sensitive() {
		softAssert.assertThat(underTestTweetResponse.path("quoted_status.possibly_sensitive").toString()).isEqualTo(tweetUnderTest.getPossibly_sensitive());
	}

	@Then("^should have location \"([^\"]*)\"$")
	public void should_have_location(String location) {
		String coordinates = underTestTweetResponse.path("geo.coordinates").toString();
		String formattedResponse = coordinates.replace("[", "").replace("]", "").trim();
		softAssert.assertThat(formattedResponse).isEqualTo(Utilities.getCoordinates(location).toString());
	}

	@Given("^user deletes existing status$")
	public void delete_Status() {
		try{
			
			String[] allStatusIds;
			
			allStatusIds = given().spec(requestSpecification)
			.when().get(ResourcePaths.USER_TIMELINE)
			.then().statusCode(200).extract().path("id").toString().split(",");
			
			for(String statusId : allStatusIds){
				System.out.println("Deleting pre existing status");
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
