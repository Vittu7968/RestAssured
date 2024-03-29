package com.rest.gmailApi;

import static org.hamcrest.Matchers.equalTo;

import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GetMessage {
	
	
	String accessToken = "ya29.a0Ad52N3_p4n8xIW6uCxvnQdfua_pvekccwaAFgsINiawO0arVu3j4bG6vfJ3oa2aEo04VqnqArO-jnjfShpDgGgLuXc_PBF_6LAoddzydiWdacu-mQvM49B5G2z9oPkViF8fdUW2CN_7YZfY2lG6NV99h91QJ5Pf5tHmEaCgYKAWwSARASFQHGX2Mi7L2f8EUjiViqmiPPEPP_Iw0171";

	@BeforeClass
	public void beforeClass() {

		RequestSpecBuilder reqBuild = new RequestSpecBuilder();

		RestAssured.requestSpecification = reqBuild.setBaseUri("https://gmail.googleapis.com")
				.addHeader("Authorization", "Bearer " + accessToken).setContentType(ContentType.JSON).log(LogDetail.ALL)
				.build();

		ResponseSpecBuilder respBuild = new ResponseSpecBuilder();

		RestAssured.responseSpecification = respBuild
				.expectContentType(ContentType.JSON)
		.expectStatusCode(200)
				.log(LogDetail.ALL).build();

	}

	@Test
	public void getMessage() {
		String id = "18e5d1358cf34897";
		RestAssured
				.given()
				.basePath("/gmail/v1")
				.pathParam("userid", "aksh79681@gmail.com")
				.pathParam("id", id).when()
				.get("/users/{userid}/messages/{id}")
				.then()
				.assertThat().body("payload.headers[1].value", equalTo("aksh79681@gmail.com"));

	}

}
