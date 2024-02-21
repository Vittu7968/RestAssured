package com.rest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class downloadFile {
	
	@Test()
	public void upload_File_multipart_form_data() throws IOException
	{
		
		byte[] b=RestAssured.given()
		.baseUri("https://raw.githubusercontent.com")
		.log()
		.all()
		.when()
		.get("/appium/appium/master/packages/appium/sample-code/apps/ApiDemos-debug.apk")
		.then()
		.log()
		.all()
		.extract()
		.asByteArray();
		
		
		FileOutputStream fo=new FileOutputStream(new File("downloaded_File.apk"));
		//Here the File will be created In Root directory
		fo.write(b);
		fo.close();
		
	}
	
	@Test()
	public void upload_File_multipart_form_data_AsInputStream() throws IOException
	{
		
		InputStream is=RestAssured.given()
		.baseUri("https://raw.githubusercontent.com")
		.log()
		.all()
		.when()
		.get("/appium/appium/master/packages/appium/sample-code/apps/ApiDemos-debug.apk")
		.then()
		.log()
		.all()
		.extract()
		.asInputStream();
		
		
		FileOutputStream fo=new FileOutputStream(new File("downloaded_File_Is.apk"));
		//Here the File will be created In Root directory
		//is.available -> returns number of bytes that are available
		byte[] b=new byte[is.available()];
		is.read(b);//Reads input stream To Bytes array
		fo.write(b);
		fo.close();
		
	}

}
