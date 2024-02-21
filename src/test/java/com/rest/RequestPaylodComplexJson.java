package com.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.config.EncoderConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;

public class RequestPaylodComplexJson {
	
	@BeforeClass
	public void befClass()
	{
		RequestSpecBuilder reqspecbuilder=new RequestSpecBuilder();
		
		//Default Request Specification which will be applicable Through out the class
		
		//Providing mock server URL
		RestAssured.requestSpecification=reqspecbuilder
				.setBaseUri("https://6e6f3901-92bd-43a0-b3a6-06cb647aa257.mock.pstmn.io")
		 .setContentType(ContentType.JSON)
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
	public void validate_post_request_payload_json_ComplexPayload()
	{
		List<Integer> id_elements=new ArrayList<Integer>();
		id_elements.add(5);
		id_elements.add(9);
		
		HashMap<String, Object> batterHashmap2=new HashMap<String, Object>();
		batterHashmap2.put("id", id_elements);
		batterHashmap2.put("type", "Chocolate");
		
		HashMap<String, Object> batterHashmap1=new HashMap<String, Object>();
		batterHashmap1.put("id", "1001");
		batterHashmap1.put("type", "Regular");
		
		
		List<HashMap<String, Object>> batter=new ArrayList<HashMap<String,Object>>();
		batter.add(batterHashmap1);
		batter.add(batterHashmap2);
		
		HashMap<String, Object> batters=new HashMap<String, Object>();
		batters.put("batter", batter);
		
		
		
		//This is for topping section
		
		List<String> type=new ArrayList<String>();
		type.add("test1");
		type.add("test2");
		
		HashMap<String, Object> toppingHashmap2=new HashMap<String, Object>();
		toppingHashmap2.put("id", "5002");
		toppingHashmap2.put("type", type);
		
		HashMap<String, String> toppingHashmap1=new HashMap<String, String>();
		toppingHashmap1.put("id", "5001");
		toppingHashmap1.put("type", "None");
		
		List<Object> toppingList=new ArrayList<Object>();
		toppingList.add(toppingHashmap1);
		toppingList.add(toppingHashmap2);
		
		HashMap<String, Object> mainJson=new HashMap<String, Object>();
		mainJson.put("id", "0001");
		mainJson.put("type", "donut");
		mainJson.put("name", "Cake");
		mainJson.put("ppu", 0.55);
		mainJson.put("batters", batters);
		mainJson.put("topping", toppingList);
		
		
		
		
		RestAssured.given()
		.body(mainJson)
		.when()
		.post("/postComplex")
		.then()
		.assertThat()
		.body("msg", Matchers.equalTo("success"));
		
	}

}
