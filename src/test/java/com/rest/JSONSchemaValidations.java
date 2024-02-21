package com.rest;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;

public class JSONSchemaValidations {
	
	
	@Test()
	public void validateJsonSchema()
	{
		RestAssured.given()
		.baseUri("https://postman-echo.com")
		.log()
		.all()
		.when()
		.get("/get")
		.then()
		.log()
		.all()
		.assertThat()
		//We are asserting Json schema of message body is matching with Stored json scema
		.body(JsonSchemaValidator.matchesJsonSchema(new File("src\\main\\resources\\res\\EchoGet.json")))
		.statusCode(200);
	}

}
