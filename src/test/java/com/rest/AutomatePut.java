package com.rest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.matchesPattern;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;

public class AutomatePut {
	
	@BeforeClass
	public void befClass()
	{
		RequestSpecBuilder reqspecbuilder=new RequestSpecBuilder();
		
		
		//Default Request Specification which will be applicable Through out the class
		RestAssured.requestSpecification=reqspecbuilder
				.setBaseUri("https://api.postman.com")
		 .addHeader("X-Api-Key", "PMAK-658d7f14b7b0b2173f910ce6-0a4a5ebe171db0be33604f98d0c3b6e627")
		 .setContentType(ContentType.JSON)
		 .log(LogDetail.ALL)
		 .build();
		
		
		ResponseSpecBuilder respspecBuilder=new ResponseSpecBuilder();
		
		
		//Default Response Specification which will be applicable Through out the class
		RestAssured.responseSpecification=respspecBuilder.expectStatusCode(200)
				.expectContentType(ContentType.JSON)
				.log(LogDetail.ALL).build();
	}
	
	
	@Test()
	public void validate_post_request_bdd_style()
	{
		String workspaceId="40e5b2fb-0bc9-402a-b4d0-7e812854fa07";
		String payload="{\r\n"
				+ "    \"workspace\": {\r\n"
				+ "        \"name\": \"My WorkspaceD212\",\r\n"
				+ "        \"type\": \"personal\",\r\n"
				+ "        \"description\": \"This is your personal, private workspace to play around in. Only you can see the collections and APIs you create here - unless you share them with your team.\"\r\n"
				+ "    }\r\n"
				+ "}";
		
		
		
//		Response resp=given().body(payload)
//		.when().post("/workspaces")
//		.then().extract().response();
//		
//		System.out.println(resp.asString());
//		
//		 MatcherAssert.assertThat(resp.path("workspace.name").toString(), equalTo("My WorkspaceD02"));
		
		given().body(payload)
		.when().put("/workspaces/"+workspaceId)
		.then().assertThat().body("workspace.name", equalTo("My WorkspaceD02"), 
				"workspace.id", matchesPattern("^[a-z0-9-]{36}$"),
				"workspace.id", equalTo(workspaceId));
		
		
	}
	
	@Test()
	public void validate_post_request_bdd_style_UsingpathParams()
	{
		String workspaceIdValue="40e5b2fb-0bc9-402a-b4d0-7e812854fa07";
		String payload="{\r\n"
				+ "    \"workspace\": {\r\n"
				+ "        \"name\": \"My WorkspaceD212\",\r\n"
				+ "        \"type\": \"personal\",\r\n"
				+ "        \"description\": \"This is your personal, private workspace to play around in. Only you can see the collections and APIs you create here - unless you share them with your team.\"\r\n"
				+ "    }\r\n"
				+ "}";
		
	
		
		given().body(payload)
		.pathParam("workspaceId", workspaceIdValue)
		.when().put("/workspaces/{workspaceId}")
		.then().assertThat().body("workspace.name", equalTo("My WorkspaceD02"), 
				"workspace.id", matchesPattern("^[a-z0-9-]{36}$"),
				"workspace.id", equalTo(workspaceIdValue));
		
		
	}

}
