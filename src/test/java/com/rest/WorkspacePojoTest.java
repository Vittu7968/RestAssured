package com.rest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.matchesPattern;

import java.util.HashMap;

import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.rest.pojo.workspace.workspace_pojo;
import com.rest.pojo.workspace.workspace_root;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;//hamcrest is an assertion library along

public class WorkspacePojoTest {
	
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
	public void validate_post_request_payload_With_Pojo()
	{
		
		workspace_pojo wp=new workspace_pojo("My WorkspaceD909", "New Rest assured map paload Implementation","personal");
		
		workspace_root wr=new workspace_root(wp);
		
//	    File f1=new File("src/main/resources/res/CreateWorkspacePayload.json");
		workspace_root wr2=given().body(wr)
		.when().post("/workspaces")
		.then()
		.extract()
		.response()
		.as(workspace_root.class);
		
		MatcherAssert.assertThat(wr2.getWorkspace().getName(),
				equalTo(wr.getWorkspace().getName()));
		
		
		MatcherAssert.assertThat(wr2.getWorkspace().getId(),
				matchesPattern("^[a-z0-9-]{36}$"));
		
		
		
	}
	
	@Test(dataProvider = "testData")
	public void validate_post_request_payload_With_Pojo_Using_DataProvider(String name, String description, String type)
	{
		
		workspace_pojo wp=new workspace_pojo( name,  description,  type);
		
		workspace_root wr=new workspace_root(wp);
		
//	    File f1=new File("src/main/resources/res/CreateWorkspacePayload.json");
		workspace_root wr2=given().body(wr)
		.when().post("/workspaces")
		.then()
		.extract()
		.response()
		.as(workspace_root.class);
		
		MatcherAssert.assertThat(wr2.getWorkspace().getName(),
				equalTo(wr.getWorkspace().getName()));
		
		
		MatcherAssert.assertThat(wr2.getWorkspace().getId(),
				matchesPattern("^[a-z0-9-]{36}$"));
		
	}
	
	@DataProvider(name="testData")
	public Object[][] getWorkspace(){
		return new Object[][] {
		{"My WorkspaceD909", "New Rest assured map paload Implementation","personal"},
		{"My WorkspaceD990", "New Rest assured map paload Implementation","team"}
		};
		
		
	}

}
