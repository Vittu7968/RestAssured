package com.rest.gmailApi;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;

public class GetProfile {
	
	
	String accessToken="ya29.a0Ad52N38DEkcEKHbWy1Z-YQhmAFa0LkGp5bkMSrio-RaXeiGVVxlBbhd1EzNj7k6n8JVQizqQwqGSvXoBv-N8LDtsROEGfi47p6A3wEi5BwZiSKyzCDx29krdmBEuQYWJRb-iy1L2wOLug_6sQSP20ySrdvuyrU4REUKNMgaCgYKAeUSARASFQHGX2MitargEwDMpqVEQa283bYtJw0173";
	
	@BeforeClass
	public void beforeClass()
	{
		
		
		RequestSpecBuilder reqSpecBuild=new RequestSpecBuilder();
		
		
		RestAssured.requestSpecification=reqSpecBuild.setBaseUri("https://gmail.googleapis.com")
				.addHeader("Authorization", "Bearer " + accessToken)
		.setContentType(ContentType.JSON)
		.log(LogDetail.ALL)
		.build();
		
		
		
		ResponseSpecBuilder resSpecBuild=new ResponseSpecBuilder();
		RestAssured.responseSpecification=resSpecBuild.expectStatusCode(200)
		.expectContentType(ContentType.JSON)
		.log(LogDetail.ALL)
		.build();
	}
	
	
	@Test
	public void getUserProfile()
	{
		RestAssured.given()
		.basePath("/gmail/v1")
		.pathParam("userid", "aksh79681@gmail.com")
		.when()
		.get("/users/{userid}/profile")
		.then()
		.assertThat()
		.body("emailAddress", Matchers.equalTo("aksh79681@gmail.com"));
	}

}
