package com.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.config.Config;
import io.restassured.config.EncoderConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;

public class RequestPayloadAsJsonArray {
	
	
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
	public void validate_post_request_payload_json_Array_As_List()
	{
		
		HashMap<String, String> h1=new HashMap<String, String>();
		h1.put("id", "5001");
		h1.put("type", "None");
		
		HashMap<String, String> h2=new HashMap<String, String>();
		h2.put("id", "5002");
		h2.put("type", "Glazed");
		
//		List<Object> l1=new ArrayList<Object>();
//		l1.add(h1);
//		l1.add(h2);
		
		
		/*When you use List<Map>, 
		 * you are explicitly defining that the list contains elements of type Map. In your case, each map contains key-value pairs representing data. 
		 * This approach ensures type safety and readability, as it clearly specifies the structure of the data being passed.
		 */
		
		List<Map> l1=new ArrayList<Map>();
		l1.add(h1);
		l1.add(h2);
		
		
		
		
		
		
		RestAssured.given()
		//The list here will bw internally Converted To Json by using Jackson bind library that we added in PON.xml
		.body(l1)
		.when()
		.post("/post")
		.then()
		.assertThat()
		.body("msg", Matchers.equalTo("success"));
		
		
	}

}
