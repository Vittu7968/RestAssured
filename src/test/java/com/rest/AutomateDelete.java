package com.rest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.matchesPattern;

import org.hamcrest.MatcherAssert;
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

public class AutomateDelete {
	
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
	public void validate_delete_request_bdd_style()
	{
		String workspaceIdValue="167164f7-0c91-4436-b5ce-b1dae0faa325";
		
//		given()
//		.pathParam("workspaceId", workspaceIdValue)
//		.when().delete("/workspaces/{workspaceId}")
//		.then().assertThat().body("workspace.id", matchesPattern("^[a-z0-9-]{36}$"),
//				"workspace.id", equalTo(workspaceIdValue));
		
		Response rsp=with().delete("/workspaces/"+workspaceIdValue);
		
		MatcherAssert.assertThat(rsp.path("workspace.id").toString(), equalTo(workspaceIdValue));
		
		
	}

	
	@Test()
	public void validate_delete_request_bdd_style_UsingPathParams()
	{
		String workspaceIdValue="6fcf5f28-8709-4aae-b336-5a45cd207fc2";
		
//		given()
//		.pathParam("workspaceId", workspaceIdValue)
//		.when().delete("/workspaces/{workspaceId}")
//		.then().assertThat().body("workspace.id", matchesPattern("^[a-z0-9-]{36}$"),
//				"workspace.id", equalTo(workspaceIdValue));
		
		given().pathParam("workspaceId", workspaceIdValue)
				.when().delete("/workspaces/{workspaceId}").then()
				.assertThat().body("workspace.id", equalTo(workspaceIdValue));
		
		
	}
}
