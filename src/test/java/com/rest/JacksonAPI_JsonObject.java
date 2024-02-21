package com.rest;

import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;

public class JacksonAPI_JsonObject {
	
	
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
	
	
	@Test
	public void validate_post_payload_As_Map_And_ObjectMapping_Json() throws JsonProcessingException
	{
		HashMap<String, Object> masterObject = new HashMap<String, Object>();

		HashMap<String, String> nestedObject = new HashMap<String, String>();
		nestedObject.put("name", "My Workspace08");
		nestedObject.put("type", "personal");
		nestedObject.put("description", "New Rest assured map paload Implementation");

		masterObject.put("workspace", nestedObject);
		
		//We will explicitly serialize Java oBject to JSON using object mapper
		
		ObjectMapper obj=new ObjectMapper();
		String maiObjectStr=obj.writeValueAsString(masterObject);
		
		
		//Previously restAssured was doing serialization internally for using Jackson whic we had added dependency in POM.xml File
		
		RestAssured.given()
		.body(maiObjectStr)
		.when()
		.post("/workspaces")
		.then()
		.assertThat()
		.body("workspace.name", equalTo("My Workspace08"));
	}
	
	
	@Test
	public void serialize_JSON_using_jackson_ObjectNode() throws JsonProcessingException
	{
		
		
		ObjectMapper obj=new ObjectMapper();
		
		
		//Using Object Node function from Jackson which is similar to HashMap
		ObjectNode nestedObjectNode=obj.createObjectNode();
		nestedObjectNode.put("name", "My Workspace23");
		nestedObjectNode.put("type", "personal");
		nestedObjectNode.put("description", "New Rest assured map paload Implementation");
		
		
		ObjectNode masterObjectNode=obj.createObjectNode();
		//We are using Set instead of put that we were using in HashMap
		masterObjectNode.set("workspace", nestedObjectNode);
		
		String mainObjectString=obj.writeValueAsString(masterObjectNode);
		
		//We will explicitly serialize Java oBject to JSON using object mapper
		
		//Previously restAssured was doing serialization internally for using Jackson whic we had added dependency in POM.xml File
		
		RestAssured.given()
		.body(mainObjectString)
		.when()
		.post("/workspaces")
		.then()
		.assertThat()
		.body("workspace.name", equalTo("My Workspace23"));
	}
	

}
