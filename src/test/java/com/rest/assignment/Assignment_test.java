package com.rest.assignment;

import java.util.HashMap;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.hamcrest.core.IsNull;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.rest.pojo.assignment.Address;
import com.rest.pojo.assignment.Geo;
import com.rest.pojo.assignment.users_rootpojo;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;

public class Assignment_test {
	
	@BeforeClass
	public void beforeClass()
	{
RequestSpecBuilder reqspecbuilder=new RequestSpecBuilder();
		
		
		//Default Request Specification which will be applicable Through out the class
		RestAssured.requestSpecification=reqspecbuilder
				.setBaseUri("https://jsonplaceholder.typicode.com/users")
		 .setContentType(ContentType.JSON)
		 .log(LogDetail.ALL)
		 .build();
		
		
		ResponseSpecBuilder respspecBuilder=new ResponseSpecBuilder();
		
		
		//Default Response Specification which will be applicable Through out the class
		RestAssured.responseSpecification=respspecBuilder.expectStatusCode(201)
				.expectContentType(ContentType.JSON)
				.log(LogDetail.ALL).build();
	}
	
	@Test
	public void users_post()
	{
		Address addr=new Address("Kulas Light", "Apt. 556", "Gwenborough", "92998-3874");
		
		Geo geo=new Geo("-37.3159", "81.1496");
		users_rootpojo ur=new users_rootpojo("Leanne Graham", "Bret", "Sincere@april.biz", addr, geo);
		
		
		//This piece of code is added to understand NON_EMPTY part in JsonInclude
		HashMap<String, String> newHp=new HashMap<String, String>();
		ur.setHp(newHp);
		
		//*****
		
		users_rootpojo ur2=RestAssured
		.given()
		.body(ur)
		.when()
		.post()
		.then().extract()
		.response()
		.as(users_rootpojo.class);
		
		MatcherAssert.assertThat("Id is not null",ur2.getId(), Matchers.notNullValue());
		
		System.out.println(ur2.getId());
		
		
	}

}
