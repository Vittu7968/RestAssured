package com.rest;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.config.LogConfig;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;//hamcrest is an assertion library along with rest assured to assert testcases

import java.util.HashSet;
import java.util.Set;

import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;


//Here we are using static methods to maintain code raedability


public class Test01 {
	
	
	@Test()
	public void testinitial()
	{
		//Readability will suffer a bit here
		
		//If we use static methods only the method names will Be used, instaed of using class name with method
		given().baseUri("https://api.postman.com").header("X-Api-Key","PMAK-658d7f14b7b0b2173f910ce6-0a4a5ebe171db0be33604f98d0c3b6e627").
		when().get("/workspaces").
		then().log().all().assertThat().statusCode(200).body("workspaces.name", hasItems("My Workspace"), 
				"workspaces.type", hasItems("personal"), "workspaces[0].type",is(equalTo( "personal")),
				"workspaces.size()",equalTo(1),
				"workspaces.name",hasItem("My Workspace"));
	}
	
	
	@Test()
	public void automateGetResponse()
	{
		
		//Here Response is an Interface
		Response res=given().baseUri("https://api.postman.com")
		.header("X-Api-Key","PMAK-658d7f14b7b0b2173f910ce6-0a4a5ebe171db0be33604f98d0c3b6e627").
		when().get("/workspaces").
		then().assertThat()
		.statusCode(200).extract()
		.response();
		
		System.out.println(res.asString());//asString returns Response Body data
		
//		System.out.println("Workspace array is :"+JsonPath.from(res.asString()).getString("workspaces"));
	}
	
	@Test
	public void extract_single_value_from_response()
	{
		
		//Here Response is an Interface
		//2nd way to extract a single value
//		Response res=given().baseUri("https://api.postman.com")
//		.header("X-Api-Key","PMAK-658d7f14b7b0b2173f910ce6-0a4a5ebe171db0be33604f98d0c3b6e627").
//		when().get("/workspaces").
//		then().assertThat()
//		.statusCode(200).extract()
//		.response();
//		
//		JsonPath js=new JsonPath(res.asString());
//		System.out.println(js.getString("workspaces[0].type"));
		
		
		//*******************************************
		//The following is 1 option of extaracting a single value
//		System.out.println("The workpace name is "+ res.path("workspaces[0].type", null));//asString returns Response Body data
		
		
		//*******************************************
				//The following is 3 option of extaracting a single value
		String res=given().baseUri("https://api.postman.com")
				.header("X-Api-Key","PMAK-658d7f14b7b0b2173f910ce6-0a4a5ebe171db0be33604f98d0c3b6e627").
				when().get("/workspaces").
				then().assertThat()
				.statusCode(200).extract()
				.response().asString();//Extracting The Response as a String
		
		
		System.out.println("Workspace name is :"+JsonPath.from(res).getString("workspaces[0].name"));//from response getting the required String
		
		
		
		//4th way of extracting a single value
		
		String res2=given().baseUri("https://api.postman.com")
				.header("X-Api-Key","PMAK-658d7f14b7b0b2173f910ce6-0a4a5ebe171db0be33604f98d0c3b6e627").
				when().get("/workspaces").
				then().assertThat()
				.statusCode(200).extract()
				.response().path("workspaces[0].type", "");
				
				System.out.println("The Required Workpace Type is "+ res2);
				
				
				//Hamcrest Assertion Outside Rest assured
				
		MatcherAssert.assertThat(res2, equalTo("personal"));
		
		//Using TestNg assertion instead og Hamcrest assertion
		
		Assert.assertEquals(res2, "personal1");
		
		
	}
	

	@Test()
	public  void testHamcrestAssertionMethods()
	{
		//Readability will suffer a bit here
		
		//If we use static methods only the method names will Be used, instaed of using class name with method
		given().baseUri("https://api.postman.com").header("X-Api-Key","PMAK-658d7f14b7b0b2173f910ce6-0a4a5ebe171db0be33604f98d0c3b6e627").
		when().get("/workspaces").
		then()
//		.log().all()
//		.assertThat().statusCode(200).body("workspaces.name", Matchers.contains("My Workspace","MyW02"));
		.assertThat().statusCode(200).body("workspaces.name", Matchers.containsInAnyOrder("MyW02","My Workspace"),
				"workspaces.name", is(not(Matchers.emptyArray())), "workspaces.name", Matchers.hasSize(2)
				,"workspaces[0]", Matchers.hasKey("name")
				,"workspaces[0]", Matchers.hasEntry("id", "fc7e4844-1740-4cbc-868e-2e01c24584db")
				,"workspaces[0].name", Matchers.allOf(startsWith("M"), containsString("W")));
		//allOf passes is all validations inside it passes.
		
		
		
		//contains() -> Checks all elements of collection in a strict order
		
		//containsInAnyOrder() -> Checks all elements of collection in a any order
	}
	
	
	@Test()
	public void Request_response_logging()
	{
		//Readability will suffer a bit here
		
		//If we use static methods only the method names will Be used, instaed of using class name with method
//		given().baseUri("https://api.postman.com").header("X-Api-Key","PMAK-658d7f14b7b0b2173f910ce6-0a4a5ebe171db0be33604f98d0c3b6e627")
//		.log().all()
//		.when().get("/workspaces").
//		then().log().all().assertThat().statusCode(200);
		
		
		//If we want to print only headers in request and body only for response
		
//		given().baseUri("https://api.postman.com")
//		.header("X-Api-Key","PMAK-658d7f14b7b0b2173f910ce6-0a4a5ebe171db0be33604f98d0c3b6e627")
//		.log().all()
//		.when().get("/workspaces").
//		then().log()
//		.ifError().assertThat()
//		.statusCode(200);
		
//		given().baseUri("https://api.postman.com")
//		.header("X-Api-Key","PMAK-658d7f14b7b0b2173f910ce6-0a4a5ebe171db0be33604f98d0c3b6e627")
//		.log().ifValidationFails()
//		.when().get("/workspaces").
//		then().log()
//		.ifValidationFails().assertThat()
//		.statusCode(204);
		
//		given().baseUri("https://api.postman.com")
//		.header("X-Api-Key","PMAK-658d7f14b7b0b2173f910ce6-0a4a5ebe171db0be33604f98d0c3b6e627")
//		.config(config.logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails()))
////		.log().ifValidationFails()
//		.when().get("/workspaces").
//		then()
////		.log().ifValidationFails()
//		.assertThat()
//		.statusCode(204);
		
		
		//*****Blacklisting single header
		
//		given().baseUri("https://api.postman.com")
//		.header("X-Api-Key","PMAK-658d7f14b7b0b2173f910ce6-0a4a5ebe171db0be33604f98d0c3b6e627")
//		.config(config.logConfig(LogConfig.logConfig().blacklistHeader("X-Api-Key", "Blacklist API key")))
//		.log().all()
//		.when().get("/workspaces").
//		then()
//		.log().all()
//		.assertThat()
//		.statusCode(204);
		
		//*****Blacklisting collection of headers
		
		Set<String> headers=new HashSet<String>();
		headers.add("X-Api-Key");
		headers.add("Accept");
		given().baseUri("https://api.postman.com")
		.header("X-Api-Key","PMAK-658d7f14b7b0b2173f910ce6-0a4a5ebe171db0be33604f98d0c3b6e627")
		.config(config.logConfig(LogConfig.logConfig().blacklistHeaders(headers)))
		.log().all()
		.when().get("/workspaces").
		then()
		.log().all()
		.assertThat()
		.statusCode(200);
		
		
		
	}

}
