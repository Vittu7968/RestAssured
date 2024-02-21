package com.rest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;

public class Filters {
	
	@BeforeClass
	public void beforeClass() throws FileNotFoundException
	
	{
		
		PrintStream FileOutPutStream=new PrintStream(new File("restAssured2.log"));
		
     RequestSpecBuilder reqspecbuilder=new RequestSpecBuilder();
		
		
		//Default Request Specification which will be applicable Through out the class
		RestAssured.requestSpecification=reqspecbuilder
				.addFilter(new ResponseLoggingFilter(FileOutPutStream))
				.addFilter(new RequestLoggingFilter(FileOutPutStream))
				.build();
		
        ResponseSpecBuilder respspecBuilder=new ResponseSpecBuilder();
		//Default Response Specification which will be applicable Through out the class
		RestAssured.responseSpecification=respspecBuilder.build();
		
	}
	
	
	@Test()
	public void loggingFilter() {
		RestAssured.given()
		.baseUri("https://postman-echo.com")
		//Alternate way of logging
		.filter(new RequestLoggingFilter(LogDetail.BODY))
		.filter(new ResponseLoggingFilter(LogDetail.STATUS))
//		.log().all()
		.when()
		.get("/get")
		.then()
//		.log().all()
		.assertThat()
		.statusCode(200);

	}
	
	
	@Test()
	public void loggingFilterInFile() throws FileNotFoundException {
		
		PrintStream FileOutPutStream=new PrintStream(new File("restAssured.log"));
		RestAssured.given()
		.baseUri("https://postman-echo.com")
		//Alternate way of logging
		.filter(new RequestLoggingFilter(LogDetail.BODY,FileOutPutStream))
		.filter(new ResponseLoggingFilter(LogDetail.STATUS,FileOutPutStream))
//		.log().all()
		.when()
		.get("/get")
		.then()
//		.log().all()
		.assertThat()
		.statusCode(200);

	}
	
	
	@Test()
	public void loggingFilterInFile02() throws FileNotFoundException {
		RestAssured.given()
		.baseUri("https://postman-echo.com")
		//Alternate way of logging
//		.log().all()
		.when()
		.get("/get")
		.then()
//		.log().all()
		.assertThat()
		.statusCode(200);

	}



}
