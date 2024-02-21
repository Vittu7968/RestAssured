package com.rest;

import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;//hamcrest is an assertion library along with rest assured to assert testcases

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AutomateHeaders {
	
	
	@Test()
	public void multiple_Headers() {
		
		
		
		Map<String, String> mp=new HashMap<String, String>();
		
		mp.put("headerName", "value2");
		mp.put("x-mock-match-request-headers", "headerName");
//		Header h01=new Header("headerName", "value2");
//		Header h02=new Header("x-mock-match-request-headers", "headerName");
//		Headers allHeaders = new Headers(h01, h02);
		
		given().baseUri("https://6e6f3901-92bd-43a0-b3a6-06cb647aa257.mock.pstmn.io")
//		.headers(allHeaders)
//		.headers(mp)
		.log().all()
		.when().get("/get")
		.then().log().all()
		.assertThat()
		.statusCode(200);
		
		
		//We cannot Use HashMap for Mutivalue Headers since it doesnt accept Duplicate Keys
		
		//So in This case We need to Use Headers
		
		
	}
	
	@Test()
	public void multipleValue_Headers() {
		
		Header h01=new Header("multivalueHeader", "value1");
		Header h02=new Header("multivalueHeader", "value2");
		Headers allHeaders = new Headers(h01, h02);
		
		given().baseUri("https://6e6f3901-92bd-43a0-b3a6-06cb647aa257.mock.pstmn.io")
//		.header("multivalueHeader","value1","value2")
		.headers(allHeaders)
		.log().headers()
		.when().get("/get")
		.then().log().all()
		.assertThat()
		.statusCode(200);
		
		
		//We cannot Use HashMap for Mutivalue Headers since it doesnt accept Duplicate Keys
		
		//So in This case We need to Use Headers
		
		
	}
	
	
	@Test()
	public void assertResponse_Headers() {
		
       Map<String, String> mp=new HashMap<String, String>();
		
		mp.put("headerName", "value1");
		mp.put("x-mock-match-request-headers", "headerName");
		
		
		//Asserting Response Headers
		given().baseUri("https://6e6f3901-92bd-43a0-b3a6-06cb647aa257.mock.pstmn.io")
		.headers(mp)
		.log().headers()
		.when().get("/get")
		.then().log().all()
		.assertThat()
		.statusCode(200)
//		.header("responseHeader", "resValue1")
//		.header("X-RateLimit-Limit", "120");
		
		//Validating Multiple Headers instead of Using Header Method Twice
		.headers("responseHeader", "resValue1",
				"X-RateLimit-Limit", "120" );	
	}
	
	@Test()
	public void extractResponse_Headers() {
		
       Map<String, String> mp=new HashMap<String, String>();
		
		mp.put("headerName", "value1");
		mp.put("x-mock-match-request-headers", "headerName");
		
		
		//Asserting Response Headers
		Headers extractedHeaders=given().baseUri("https://6e6f3901-92bd-43a0-b3a6-06cb647aa257.mock.pstmn.io")
		.headers(mp)
		.log().headers()
		.when().get("/get")
		.then()
		.assertThat()
		.statusCode(200)
		.extract()
		.headers();
		
		
		for(Header h: extractedHeaders)
		{
			System.out.println("header name :"+h.getName()+ ","+"header value :"+h.getValue());
		}
		
//		System.out.println("Headers name extracted are "+extractedHeaders.get("responseHeader").getName());
//		
//		System.out.println("Headers value extracted are "+extractedHeaders.getValue("responseHeader"));

		
		
	}
	
	
	@Test()
	public void extractMultiValueResponse_Headers() {
		
       Map<String, String> mp=new HashMap<String, String>();
		
		mp.put("headerName", "value1");
		mp.put("x-mock-match-request-headers", "headerName");
		
		
		//Asserting Response Headers
		Headers extractedHeaders=given().baseUri("https://6e6f3901-92bd-43a0-b3a6-06cb647aa257.mock.pstmn.io")
		.headers(mp)
		.log().headers()
		.when().get("/get")
		.then()
		.assertThat()
		.statusCode(200)
		.extract()
		.headers();
		
		
//		for(Header h: extractedHeaders)
//		{
//			System.out.println("header name :"+h.getName()+ ","+"header value :"+h.getValue());
//		}
		
//		System.out.println("Headers name extracted are "+extractedHeaders.get("responseHeader").getName());
//		
//		System.out.println("Headers value extracted are "+extractedHeaders.getValue("responseHeader"));
		
		
		List<String> lt=extractedHeaders.getValues("multiValueHeader");
		
		for(String v:lt)
		{
			System.out.println(v);
		}

		
		
	}

}
