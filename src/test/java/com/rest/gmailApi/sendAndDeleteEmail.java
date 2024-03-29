package com.rest.gmailApi;

import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

import java.util.Base64;
import java.util.HashMap;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBodyData;

public class sendAndDeleteEmail {
	
	String accessToken="ya29.a0Ad52N3_p4n8xIW6uCxvnQdfua_pvekccwaAFgsINiawO0arVu3j4bG6vfJ3oa2aEo04VqnqArO-jnjfShpDgGgLuXc_PBF_6LAoddzydiWdacu-mQvM49B5G2z9oPkViF8fdUW2CN_7YZfY2lG6NV99h91QJ5Pf5tHmEaCgYKAWwSARASFQHGX2Mi7L2f8EUjiViqmiPPEPP_Iw0171";
	
	String id ="";
	
	@BeforeClass
	public void beforeClass()
	{
		
		
		RequestSpecBuilder reqBuild=new RequestSpecBuilder();
		
		RestAssured.requestSpecification=reqBuild
		.setBaseUri("https://gmail.googleapis.com")
		.addHeader("Authorization", "Bearer " + accessToken)
		.setContentType(ContentType.JSON)
		.log(LogDetail.ALL)
		.build();
		
		
		ResponseSpecBuilder respBuild=new ResponseSpecBuilder();
		
		RestAssured.responseSpecification=respBuild
		.expectContentType(ContentType.JSON)
//		.expectStatusCode(200)
		.log(LogDetail.ALL)
		.build();
		
	}
	
	
	
	@Test
	public void sendgmail()
	{
		
		String msg= "From: aksh79681@gmail.com\r\n"
				+ "To: vittu79681@gmail.com\r\n"
				+ "Subject: Test Email\r\n"
				+ "\r\n"
				+ "Sending Gmail Gmail API";
		
		String base64URLEncodedMsg=Base64.getEncoder().encodeToString(msg.getBytes());
		
		HashMap<String, String> payload=new HashMap<String, String>();
		payload.put("raw", base64URLEncodedMsg);
		
//		RestAssured
//		.given()
//		.basePath("/gmail/v1")
//		.pathParam("userid", "aksh79681@gmail.com")
//		.body(payload)
//		.when()
//		.post("/users/{userid}/messages/send")
//		.then()
//		.assertThat()
//		.body(Matchers.containsString("SENT"));
		
		
		 Response resp=RestAssured
		.given()
		.basePath("/gmail/v1")
		.pathParam("userid", "aksh79681@gmail.com")
		.body(payload)
		.when()
		.post("/users/{userid}/messages/send")
		.then()
		.extract()
		.response();
		 
		 
		 
		 id =JsonPath.from(resp.asString()).getString("id");
		 System.out.println("Gmail Sent Id is :"+id);
		 
		 
		
		
	}
	
	
	@Test(dependsOnMethods = "sendgmail")
	public void deleteSentEmail()
	{
		int statusCode=RestAssured
		.given()
		.basePath("/gmail/v1")
		.pathParam("userid", "aksh79681@gmail.com")
		.pathParam("id", id)
		.when()
		.delete("/users/{userid}/messages/{id}")
		.then()
		.extract()
		.statusCode();
		
		System.out.println(statusCode);
		
		Assert.assertEquals(statusCode, 204);
		
		
	}
	
	
	
	

}
