package com.rest;

//import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;

import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rest.pojo.simple.SimplePojo;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.matcher.RestAssuredMatchers;
import io.restassured.response.*;

public class SamplePojoTest {
	
	
	@BeforeClass
	public void beforeClass()
	{
		
		
		RequestSpecBuilder reqSpec=new RequestSpecBuilder();
		
		RestAssured.requestSpecification=reqSpec.setBaseUri("https://6e6f3901-92bd-43a0-b3a6-06cb647aa257.mock.pstmn.io/postS02")
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
	public void samplePOJOexmaaple()
	{
		
		
		HashMap<String, String> hp=new HashMap<String, String>();
		hp.put("key1", "value1");
		hp.put("key2", "value2");
		
		
		RestAssured
		.given()
		.body(hp)
		.when()
		.post()
		.then()
		.assertThat()
		.body("key1", equalTo("value1"),
				"key2", equalTo("value2"));
		
	}
	
	@Test()
	public void samplePOJOexamaple_UsingPOJO()
	{
		
//		SimplePojo sp=new SimplePojo("value1", "value2");
		SimplePojo sp2=new SimplePojo();
		sp2.setKey1("value1");
		sp2.setKey2("value2");
		
		RestAssured
		.given()
		.body(sp2)
		.when()
		.post()
		.then()
		.assertThat()
		.body("key1", equalTo(sp2.getKey1()),
				"key2", equalTo(sp2.getKey2()));
		
	}
	
	
	@Test()
	public void samplePOJOexamaple_Deserialization() throws JsonProcessingException
	{
		SimplePojo sp2=new SimplePojo();
		sp2.setKey1("value1");
		sp2.setKey2("value2");
		
		
		//Deserializing JSON back to Java Object
		SimplePojo sp3=RestAssured
		.given()
		.body(sp2)
		.when()
		.post()
		.then()
		.extract()
		.response()
		.as(SimplePojo.class);
		
		
		//We will assert entire JSON Response Body
		
		ObjectMapper obj=new ObjectMapper();
		//Converting Java object to JSON in its String Representation
		String responseBiody=obj.writeValueAsString(sp3);
		
		String requestBody=obj.writeValueAsString(sp2);
	MatcherAssert.assertThat(obj.readTree(requestBody), equalTo(obj.readTree(responseBiody)));
		
		
		
		
	}

}
