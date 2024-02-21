package com.rest;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class uploadFile {
	
	@Test()
	public void upload_File_multipart_form_data()
	{
		
		
		String attributes="{\"name\":\"Temp.txt\", \"parent\":{\"id\":\"123456\"}}";
		RestAssured.given()
		.baseUri("https://postman-echo.com")
		
		//Here the control name argument is the key that we passed as form data
		.multiPart("file",new File("Temp.txt"))
		
		//Explicitly specify content type otherwise it will taken as plain Text
		.multiPart("attributes", attributes, "application/json")
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
