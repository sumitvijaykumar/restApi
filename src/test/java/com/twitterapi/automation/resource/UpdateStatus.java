package com.twitterapi.automation.resource;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import com.twitterapi.automation.contants.ResourcePaths;

public class UpdateStatus extends Specifications {

	private RequestSpecification reqSpecification;
	private Response response;

	private String status;
	private String in_reply_to_status_id;
	private String auto_populate_reply_metadata;
	private String exclude_reply_user_ids;
	private String attachment_url;
	private String media_ids;
	private String possibly_sensitive;
	private String lat;
	private String longi;
	private String place_id;
	private String display_coordinates;
	private String trim_user;
	private String enable_dm_commands;
	private String fail_dm_commands;
	private String card_uri;

	public UpdateStatus() {
		this.reqSpecification = given().spec(requestSpecification);
	}
	

	//
	public Response postQuery() {
		
		this.response = this.reqSpecification.when().post(
				ResourcePaths.STATUS_UPDATE);
		return this.response;
	}

	//
	public Integer getStatusId() {
		return this.response.then().extract().path("id");
	}

	//
	public String getStatus() {
		return status;
	}

	//
	public void setStatus(String status) {
		this.status = status;
		this.reqSpecification.param("status", status);
	}

	//
	public String getIn_reply_to_status_id() {
		return in_reply_to_status_id;
	}

	//
	public void setIn_reply_to_status_id(String in_reply_to_status_id) {
		this.in_reply_to_status_id = in_reply_to_status_id;
		this.reqSpecification.param("in_reply_to_status_id", in_reply_to_status_id);
	}

	//
	public String getAuto_populate_reply_metadata() {
		return auto_populate_reply_metadata;
	}

	//
	public void setAuto_populate_reply_metadata(
			String auto_populate_reply_metadata) {
		this.auto_populate_reply_metadata = auto_populate_reply_metadata;
	}

	//
	public String getExclude_reply_user_ids() {
		return exclude_reply_user_ids;
	}

	//
	public void setExclude_reply_user_ids(String exclude_reply_user_ids) {
		this.exclude_reply_user_ids = exclude_reply_user_ids;
	}

	//
	public String getAttachment_url() {
		return attachment_url;
	}

	//
	public void setAttachment_url(String attachment_url) {
		this.attachment_url = attachment_url;
		this.reqSpecification.param("attachment_url", attachment_url);
	}

	//
	public String getMedia_ids() {
		return media_ids;
	}

	//
	public void setMedia_ids(String media_ids) {
		this.media_ids = media_ids;
	}

	//
	public String getPossibly_sensitive() {
		return possibly_sensitive;
	}

	//
	public void setPossibly_sensitive(String possibly_sensitive) {
			this.possibly_sensitive = possibly_sensitive;
			this.reqSpecification.param("possibly_sensitive", possibly_sensitive);
	}

	//
	public String getLat() {
		return lat;
	}

	//
	public void setLat(String location) {
		this.lat = Utilities.getCoordinates(location).split(",")[0].trim();
		this.reqSpecification.param("lat",this.lat);
		
	}

	//
	public String getLongi() {
		return longi;
	}

	//
	public void setLongi(String location) {
		this.longi = Utilities.getCoordinates(location).split(",")[1].trim();
		this.reqSpecification.param("long", this.longi);
				
	}

	//
	public String getPlace_id() {
		return place_id;
	}

	//
	public void setPlace_id(String place_id) {
		this.place_id = place_id;
	}

	//
	public String getDisplay_coordinates() {
		return display_coordinates;
	}

	//
	public void setDisplay_coordinates(String display_coordinates) {
		this.display_coordinates = display_coordinates;
	}

	//
	public String getTrim_user() {
		return trim_user;
	}

	//
	public void setTrim_user(String trim_user) {
		this.trim_user = trim_user;
	}

	//
	public String getEnable_dm_commands() {
		return enable_dm_commands;
	}

	//
	public void setEnable_dm_commands(String enable_dm_commands) {
		this.enable_dm_commands = enable_dm_commands;
	}

	//
	public String getFail_dm_commands() {
		return fail_dm_commands;
	}

	//
	public void setFail_dm_commands(String fail_dm_commands) {
		this.fail_dm_commands = fail_dm_commands;
	}

	//
	public String getCard_uri() {
		return card_uri;
	}

	//
	public void setCard_uri(String card_uri) {
		this.card_uri = card_uri;
	}

}
