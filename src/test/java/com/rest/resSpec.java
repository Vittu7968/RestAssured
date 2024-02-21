package com.rest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class resSpec {

RequestSpecification rs;
	
	
ResponseSpecification rsp;
	@BeforeClass
	public void BefClass()
	{
		
		//Using with instead of Given() to convert from BDD to Non-BDD
//		 rs=with().baseUri("https://api.postman.com")
//				.header("X-Api-Key", "PMAK-658d7f14b7b0b2173f910ce6-0a4a5ebe171db0be33604f98d0c3b6e627")
//				.log().all();
		 
		 
		 //Usin RequestSpecBuilder
		 
		 RequestSpecBuilder rsb=new RequestSpecBuilder();
		 
		RestAssured.requestSpecification= rsb.setBaseUri("https://api.postman.com")
		 .addHeader("X-Api-Key", "PMAK-658d7f14b7b0b2173f910ce6-0a4a5ebe171db0be33604f98d0c3b6e627").log(LogDetail.HEADERS)
		 .build();
		
		
		//Here the ResponseSpecification reference variable is assigned an object Which includes code to check status code 
//		rsp=RestAssured.expect().statusCode(200).contentType(ContentType.JSON);
		
		
		ResponseSpecBuilder respBuild=new ResponseSpecBuilder();
		
		respBuild.expectStatusCode(200).expectContentType(ContentType.JSON).log(LogDetail.ALL);
		
//		rsp=respBuild.build();
		
		RestAssured.responseSpecification=respBuild.build();
		
		
		
	}
	
	
	
	@Test()
	public void defaultReqSpecBuilder01()
	{
		
		//Checking status code only in This Test
		
		
		//Providing Logging within the code
//		 	RestAssured.get("/workspaces").then().spec(rsp).log().all().extract().response();
		 	
		 	//Providing Logging as part of Response Specification
		
		
		 	RestAssured.get("/workspaces");
	}

	
	@Test()
	public void defaultReqSpecBuilder()
	{
		
//		 Response resp=	RestAssured.get("/workspaces").then().spec(rsp).log().all().extract().response();
					
//		 Response resp=	RestAssured.get("/workspaces").then().spec(rsp).extract().response();  
		
		//USING DEFAULT RESPONSE SPECIFICATION
		Response resp=	RestAssured.get("/workspaces").then().extract().response();  
//				   MatcherAssert.assertThat(resp.statusCode(), is(equalTo(200)));
		 
		 MatcherAssert.assertThat(resp.path("workspaces[0].name").toString(), equalTo("My Workspace"));
	}

}
