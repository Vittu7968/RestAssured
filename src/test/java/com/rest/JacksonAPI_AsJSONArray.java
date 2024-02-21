package com.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.config.EncoderConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;

public class JacksonAPI_AsJSONArray {
	
	@BeforeClass
	public void befClass()
	{
		RequestSpecBuilder reqspecbuilder=new RequestSpecBuilder();
		
		//Default Request Specification which will be applicable Through out the class
		
		//Providing mock server URL
		RestAssured.requestSpecification=reqspecbuilder
				.setBaseUri("https://6e6f3901-92bd-43a0-b3a6-06cb647aa257.mock.pstmn.io")
//				 .setContentType("application/json;charset=utf-8")
		 .setContentType(ContentType.JSON)
		 //Our code was working without adding config too but if ever there is need to set Default Content Type 
		 //should not be appended with cahrset, we use the following

		 .setConfig(RestAssured.config.encoderConfig(EncoderConfig.encoderConfig()
				 .appendDefaultContentCharsetToContentTypeIfUndefined(false)))
		 .addHeader("x-mock-match-request-body", "true")
		 .log(LogDetail.ALL)
		 .build();
		
		
		//**************
		ResponseSpecBuilder respspecBuilder=new ResponseSpecBuilder();
		
		//Default Response Specification which will be applicable Through out the class
		RestAssured.responseSpecification=respspecBuilder.expectStatusCode(200)
				.expectContentType(ContentType.JSON)
				.log(LogDetail.ALL).build();
	}
	
	
	@Test()
	public void validate_post_request_payload_json_Array_As_List() throws JsonProcessingException
	{
		
		HashMap<String, String> h1=new HashMap<String, String>();
		h1.put("id", "5001");
		h1.put("type", "None");
		
		HashMap<String, String> h2=new HashMap<String, String>();
		h2.put("id", "5002");
		h2.put("type", "Glazed");
		

		List<Map> l1=new ArrayList<Map>();
		l1.add(h1);
		l1.add(h2);
		
		
		
		//Explicitly using jackson library 
		ObjectMapper obj=new ObjectMapper();
		
		//the writeValue As String is seralizing ArrayList to JSON Array
		String masterArray=obj.writeValueAsString(l1);
		
		
		
		RestAssured.given()
		//The json payload in the body is converted to JSON object using the dependency Jackson 
		//but above explicitly we are using Object Mapper to convert into a JSON Array
		.body(masterArray)
		.when()
		.post("/post")
		.then()
		.assertThat()
		.body("msg", Matchers.equalTo("success"));
		
		
	}
	
	
	@Test()
	public void serialize_json_array_using_jackson_using_ArrayNode() throws JsonProcessingException
	{
		ObjectMapper obj=new ObjectMapper();
		ObjectNode objnode1=obj.createObjectNode();
		objnode1.put("id", "5001");
		objnode1.put("type", "None");
		
		ObjectNode objnode2=obj.createObjectNode();
		objnode2.put("id", "5002");
		objnode2.put("type", "Glazed");
		

		ArrayNode arrNode=obj.createArrayNode();
		arrNode.add(objnode1);
		arrNode.add(objnode2);
		
		//Here we are using writeValue as String method so that we can pass this in body as a payload or we dan directly pass arryNode
		String jsonListStr=obj.writeValueAsString(arrNode);
		
		RestAssured.given()
		.body(jsonListStr)
		.when()
		.post("/post")
		.then()
		.assertThat()
		.body("msg", Matchers.equalTo("success"));
		
		
	}

}
