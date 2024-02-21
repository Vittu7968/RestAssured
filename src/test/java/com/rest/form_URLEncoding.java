package com.rest;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.config.RestAssuredConfig;

public class form_URLEncoding {
	
	@Test()
	public void form_UrlEncoded_data()
	{
		
		RestAssured.given()
		.baseUri("https://postman-echo.com")
		.config(RestAssuredConfig.config()
				.encoderConfig(EncoderConfig.encoderConfig()
						
						//We are Telling Not to set default content Charset
		.appendDefaultContentCharsetToContentTypeIfUndefined(false)))
		.formParam("key1", "value1")
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
