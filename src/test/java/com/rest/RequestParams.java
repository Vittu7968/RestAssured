package com.rest;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class RequestParams {
	
	
	@Test()
	public void single_query_parameter()
	{
		
		RestAssured.given()
		.baseUri("https://postman-echo.com")
		//we can use a query parm also instaed of param
		//The above method is generic
//		.param("foo1", "bar1")
		//The below method is specific
		.queryParam("foo1", "bar1")
		.log()
		.all()
		.when()
		.get("/get")
		.then()
		.log()
		.all()
		.assertThat()
		.statusCode(200);
		
		
	}
	
	@Test()
	public void multiple_query_parameter()
	{
		
		RestAssured.given()
		.baseUri("https://postman-echo.com")
		.queryParam("foo1", "bar1")
		.queryParam("foo2", "bar2")
		.log()
		.all()
		.when()
		.get("/get")
		.then()
		.log()
		.all()
		.assertThat()
		.statusCode(200);
		
		
	}
	
	@Test()
	public void multiple_query_parameter_Using_HashMap()
	{
		
//		HashMap<String, String> params=new HashMap<String, String>();
//		params.put("foo1", "bar1");
//		params.put("foo2", "bar2");
		
		
		RestAssured.given()
		.baseUri("https://postman-echo.com")
		//The foo1 will be considers as key and following others as a List as value
		.queryParam("foo1", "bar1", "foo2", "bar2")
		.log()
		.all()
		.when()
		.get("/get")
		.then()
		.log()
		.all()
		.assertThat()
		.statusCode(200);
		
		
	}
	
	@Test()
	public void multiple_Value_query_parameter()
	{
		
//		HashMap<String, String> params=new HashMap<String, String>();
//		params.put("foo1", "bar1");
//		params.put("foo2", "bar2");
		
		
		RestAssured.given()
		.baseUri("https://postman-echo.com")
		
		//If we see the request URL, multiple value query parameter when appended will be represented as
		//https://postman-echo.com/get?foo1=bar1%2Cbar2
//		.queryParams("foo1", "bar1,bar2")
		.queryParams("foo1", "bar1;bar2;bar3")
		.log()
		.all()
		.when()
		.get("/get")
		.then()
		.log()
		.all()
		.assertThat()
		.statusCode(200);
		
		
	}
	
	@Test()
	public void multiple_Value_query_parameter_HashMap()
	{
		Map<String, List<String>> paramsMultiple = new HashMap<>();

        // Add multiple values for the same key
		paramsMultiple.put("key", Arrays.asList("value1", "value2", "value3"));
		RestAssured.given()
		.baseUri("https://postman-echo.com")
		
		.queryParams(paramsMultiple)
		.log()
		.all()
		.when()
		.get("/get")
		.then()
		.log()
		.all()
		.assertThat()
		.statusCode(200);
	}
	
	@Test()
	public void pathPrams()
	{
		RestAssured.given()
		.baseUri("https://reqres.in")
		.pathParam("id", "1")
		.log()
		.all()
		.when()
		//a variable to represent path param varaible
		.get("/api/users/{id}")
		.then()
		.log()
		.all()
		.assertThat()
		.statusCode(200);
	}
	
	@Test()
	public void multipart_form_data()
	{
		RestAssured.given()
		.baseUri("https://postman-echo.com")
		
		//Here the control name argument is the key that we passed as form data
		.multiPart("formData1", "value1")
		.log()
		.all()
		.when()
		//a variable to represent path param varaible
		.post("/post")
		.then()
		.log()
		.all()
		.assertThat()
		.statusCode(200);
	}
	

}
