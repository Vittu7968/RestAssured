package com.rest;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.util.HashMap;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;

import static org.hamcrest.Matchers.*;

public class AutomatePost {
	
	
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
		
		String payload="{\r\n"
				+ "    \"workspace\": {\r\n"
				+ "        \"name\": \"My WorkspaceD03\",\r\n"
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
		.when().post("/workspaces")
		.then().assertThat().body("workspace.name", equalTo("My WorkspaceD02"), 
				"workspace.id", matchesPattern("^[a-z0-9-]{36}$"));
		
		
	}
	
	
	@Test()
	public void validate_post_request_Nonbdd_style()
	{
		
		String payload="{\r\n"
				+ "    \"workspace\": {\r\n"
				+ "        \"name\": \"My WorkspaceD03\",\r\n"
				+ "        \"type\": \"personal\",\r\n"
				+ "        \"description\": \"This is your personal, private workspace to play around in. Only you can see the collections and APIs you create here - unless you share them with your team.\"\r\n"
				+ "    }\r\n"
				+ "}";
		
		
		
		Response resp=with().body(payload)
		.post("/workspaces");
		
		System.out.println(resp.asString());
		
		 MatcherAssert.assertThat(resp.path("workspace.name").toString(), equalTo("My WorkspaceD02"));
		 MatcherAssert.assertThat("workspace.id", matchesPattern("^[a-z0-9-]{36}$"));
		
		
		
		
	}
	
	@Test()
	public void validate_post_request_from_File()
	{
		
	    File f1=new File("src/main/resources/res/CreateWorkspacePayload.json");
		given().body(f1)
		.when().post("/workspaces")
		.then().assertThat().body("workspace.name", equalTo("My WorkspaceD021"), 
				"workspace.id", matchesPattern("^[a-z0-9-]{36}$"));
		
	}
	
	@Test()
	public void validate_post_request_payload_As_Map()
	{
		
		HashMap<String, Object> masterObject=new HashMap<String, Object>();
		
		HashMap<String, String> nestedObject=new HashMap<String, String>();
		nestedObject.put("name", "My WorkspaceD021");
		nestedObject.put("type", "personal");
		nestedObject.put("description", "New Rest assured map paload Implementation");
		
		masterObject.put("workspace", nestedObject);
		
		
		
//	    File f1=new File("src/main/resources/res/CreateWorkspacePayload.json");
		given().body(masterObject)
		.when().post("/workspaces")
		.then().assertThat().body("workspace.name", equalTo("My WorkspaceD021"), 
				"workspace.id", matchesPattern("^[a-z0-9-]{36}$"));
		
	}
	


}
