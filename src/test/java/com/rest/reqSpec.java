package com.rest;

import static io.restassured.RestAssured.given;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;//hamcrest is an assertion library along with rest assured to assert testcases

import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsEqual;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;

public class reqSpec {
	
	
	//Noe creating RequestSpecification as instance variable
	RequestSpecification rs;
	
	
	@BeforeClass
	public void BefClass()
	{
		
		//Using with instead of Given() to convert from BDD to Non-BDD
//		 rs=with().baseUri("https://api.postman.com")
//				.header("X-Api-Key", "PMAK-658d7f14b7b0b2173f910ce6-0a4a5ebe171db0be33604f98d0c3b6e627")
//				.log().all();
		 
		 
		 //Usin RequestSpecBuilder
		 
		 RequestSpecBuilder rsb=new RequestSpecBuilder();
		 
		rs= rsb.setBaseUri("https://api.postman.com")
		 .addHeader("X-Api-Key", "PMAK-658d7f14b7b0b2173f910ce6-0a4a5ebe171db0be33604f98d0c3b6e627").log(LogDetail.HEADERS)
		 .build();
	}

	
	@BeforeClass
	public void BefClass01()
	{
		
		//Using with instead of Given() to convert from BDD to Non-BDD
//		 rs=with().baseUri("https://api.postman.com")
//				.header("X-Api-Key", "PMAK-658d7f14b7b0b2173f910ce6-0a4a5ebe171db0be33604f98d0c3b6e627")
//				.log().all();
		 
		 
		 //Usin RequestSpecBuilder
		 
		 RequestSpecBuilder rsb=new RequestSpecBuilder();
		 
		 
		 //Static variable will be retained through out the class unless we overwrite it
		RestAssured.requestSpecification= rsb.setBaseUri("https://api.postman.com")
		 .addHeader("X-Api-Key", "PMAK-658d7f14b7b0b2173f910ce6-0a4a5ebe171db0be33604f98d0c3b6e627").log(LogDetail.HEADERS)
		 .build();
	}
	
	
	@Test()
	public void testinitial()
	{
		
		//Here rs is an Reference variable
		RequestSpecification rs=given().baseUri("https://api.postman.com")
				.header("X-Api-Key", "PMAK-658d7f14b7b0b2173f910ce6-0a4a5ebe171db0be33604f98d0c3b6e627");
	
//		rs.log().all().when()
//				.get("/workspaces")
//				.then().log().all().statusCode(200);
		
		
		
		given(rs).log().all().when()
		.get("/workspaces")
		.then().log().all().statusCode(200);
		
	
	}
	
	
	@Test(enabled = false)
	public void validateResponseBody()
	{
		
		//Here rs is an Reference variable
		
		
	   Response resp=	rs
		.get("/workspaces").then().log().all().extract().response();
		
	   
	   MatcherAssert.assertThat(resp.statusCode(), is(equalTo(200)));
	   
	   MatcherAssert.assertThat(resp.path("workspaces[0].name"), is(equalTo("My Workspace")));
		
	
	}
	
	@Test()
	public void validateResponseBodyReqSpecBuilders()
	{
		
		//Here rs is an Reference variable
		
		
		//For ReqqSpeBuilder we need to use given().spec(rs)
	   Response resp=	given().spec(rs)
		.get("/workspaces").then().log().all().extract().response();
		
	   
	   MatcherAssert.assertThat(resp.statusCode(), is(equalTo(200)));
	   
	   MatcherAssert.assertThat(resp.path("workspaces[0].name"), is(equalTo("My Workspace")));
		
	
	}
	
	
	@Test
	public void queryTest()
	
	{
		QueryableRequestSpecification queryableRequestSpecification= SpecificationQuerier.query(RestAssured.requestSpecification);
		
		System.out.println(queryableRequestSpecification.getBaseUri());
		System.out.println(queryableRequestSpecification.getHeaders());
		
	}
	
	

}
