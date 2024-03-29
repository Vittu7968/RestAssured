package com.rest.collection;

import static org.hamcrest.Matchers.equalTo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.json.JSONException;
import org.skyscreamer.jsonassert.Customization;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.ValueMatcher;
import org.skyscreamer.jsonassert.comparator.CustomComparator;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rest.pojo.collections.Body;
import com.rest.pojo.collections.Collection;
import com.rest.pojo.collections.CollectionRoot;
import com.rest.pojo.collections.Folder;
import com.rest.pojo.collections.Header;
import com.rest.pojo.collections.Info;
import com.rest.pojo.collections.Item;
import com.rest.pojo.collections.Request;
import com.rest.pojo.collections.enhancedForRequestResponse.RequestRequest;
import com.rest.pojo.collections.enhancedForRequestResponse.*;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;

public class collection_Test {
	
	@BeforeClass
	public void beforeClass()
	{
		RequestSpecBuilder reqspecbuilder=new RequestSpecBuilder();
		
		RestAssured.requestSpecification=reqspecbuilder
				.setBaseUri("https://api.postman.com")
				.addHeader("X-Api-Key", "PMAK-658d7f14b7b0b2173f910ce6-0a4a5ebe171db0be33604f98d0c3b6e627")
		 .setContentType(ContentType.JSON)
		 .log(LogDetail.ALL)
		 .build();
		
		
		ResponseSpecBuilder respspecBuilder=new ResponseSpecBuilder();
		
		
		//Default Response Specification which will be applicable Through out the class
		RestAssured.responseSpecification=respspecBuilder.expectStatusCode(200)
				.expectContentType(ContentType.JSON)
				.log(LogDetail.ALL).build();
		
	}
	
	
	@Test
	public void cllection_Creation() throws JsonProcessingException, JSONException
	{
		
		Header newheader=new Header("Content-Type", "application/json");
		List<Header> listHeader= new ArrayList<Header>();
		listHeader.add(newheader);
		
		
		Body body=new Body("raw", "{\"data\": \"123\"}");
		
		
		
		
		Request req=new Request("https://postman-echo.com/post","POST",listHeader, body, "This is a sample POST Request");
		
		Item reqRoot=new Item("Sample POST Request", req);
		List<Item > reqRootlList=new ArrayList<Item>();
		reqRootlList.add(reqRoot);
		
		Folder fold=new Folder("This is a folder", reqRootlList);
		List<Folder> item=new ArrayList<Folder>();
		item.add(fold);
		
		
		Info inf=new Info("Collection1024", "This is just a sample collection.", "https://schema.getpostman.com/json/collection/v2.1.0/collection.json");
		
		
		Collection coll=new Collection(inf,item);
		CollectionRoot collRoot=new CollectionRoot(coll);
		
		
		
		
//		CollectionRoot cr2=
//		RestAssured.given()
//		.body(collRoot)
//		.when().post("/collections")
//		.then()
//		.extract()
//		.response()
//		.as(CollectionRoot.class);
		
//		MatcherAssert.assertThat(cr2.getCollection().getInfo().getName(),
//				equalTo(collRoot.getCollection().getInfo().getName()));
		
		String collectionUID=RestAssured.given()
		.body(collRoot)
		.when().post("/collections")
		.then()
		.extract()
		.response()
		.path("collection.uid");
		
		
		CollectionRoot cr3=RestAssured.given()
		.pathParam("collectionUid", collectionUID)
		.when()
		.get("/collections/{collectionUid}")
		.then()
		.extract()
		.response().
		as(CollectionRoot.class);
		
		ObjectMapper objectMapper=new ObjectMapper();
		String collectionRootStr=objectMapper.writeValueAsString(collRoot);
		
		String deserlializedCollectionRootStr=objectMapper.writeValueAsString(cr3);
		
		JSONAssert.assertEquals(collectionRootStr, deserlializedCollectionRootStr, 
				new CustomComparator(JSONCompareMode.STRICT_ORDER, 
						new Customization("collection.item[*].item[*].request.url", new ValueMatcher<Object>() {
							
							@Override
							public boolean equal(Object o1, Object o2) {
								// TODO Auto-generated method stub
								return true;
							}
						})));
		
		
		
		
	}
	
	
	@Test
	public  void collection_Creation_Serilization_DeserlizationEnhanced() throws JsonProcessingException, JSONException 
	{
		
		Header02 newheader=new Header02("Content-Type", "application/json");
		List<Header02> listHeader= new ArrayList<Header02>();
		listHeader.add(newheader);
		
		
		Body02 body=new Body02("raw", "{\"data\": \"123\"}");
		
		
		
		
		RequestRequest req=new RequestRequest("https://postman-echo.com/post","POST",listHeader, body, "This is a sample POST Request");
		
		ItemRequest reqRoot=new ItemRequest("Sample POST Request", req);
		List<ItemRequest > reqRootlList=new ArrayList<ItemRequest>();
		reqRootlList.add(reqRoot);
		
		FolderRequest fold=new FolderRequest("This is a folder", reqRootlList);
		List<FolderRequest> item=new ArrayList<FolderRequest>();
		item.add(fold);
		
		
		Info02 inf=new Info02("Collection1024", "This is just a sample collection.", "https://schema.getpostman.com/json/collection/v2.1.0/collection.json");
		
		
		CollectionRequest coll=new CollectionRequest(inf,item);
		CollectionRootRequest collRoot=new CollectionRootRequest(coll);
		
		String collectionUID=RestAssured.given()
		.body(collRoot)
		.when().post("/collections")
		.then()
		.extract()
		.response()
		.path("collection.uid");
		
		
		CollectionRootResponse cr3=RestAssured.given()
		.pathParam("collectionUid", collectionUID)
		.when()
		.get("/collections/{collectionUid}")
		.then()
		.extract()
		.response().
		as(CollectionRootResponse.class);
		
		ObjectMapper objectMapper=new ObjectMapper();
		String collectionRootStr=objectMapper.writeValueAsString(collRoot);
		
		String deserlializedCollectionRootStr=objectMapper.writeValueAsString(cr3);
		
		JSONAssert.assertEquals(collectionRootStr, deserlializedCollectionRootStr, 
				new CustomComparator(JSONCompareMode.STRICT_ORDER, 
						new Customization("collection.item[*].item[*].request.url", new ValueMatcher<Object>() {
							
							@Override
							public boolean equal(Object o1, Object o2) {
								// TODO Auto-generated method stub
								return true;
							}
						})));
		
		
		
		List<String> UrlRequestList=new ArrayList<String>();
		List<String> UrlResponseList=new ArrayList<String>();
		
		for (ItemRequest requestRootRequest : reqRootlList) {
			System.out.println("The url in Request is :"+requestRootRequest.getRequest().getUrl());
			UrlRequestList.add(requestRootRequest.getRequest().getUrl());
			
		}
		
		List<FolderResponse> folderResponseList=cr3.getCollection().getItem();
		for(FolderResponse folderResponse: folderResponseList)
		{
			List<ItemResponse> requestRootResponseList=folderResponse.getItem();
			for(ItemResponse requestRootResponse: requestRootResponseList)
			{
				URL urlResoponse=requestRootResponse.getRequest().getUrl();
				System.out.println("The url in Response is :"+urlResoponse.getRaw());
				UrlResponseList.add(urlResoponse.getRaw());
			}
		}
		
		MatcherAssert.assertThat( UrlResponseList, Matchers.containsInAnyOrder(UrlRequestList.toArray()));
		
		
	}
	
	
	@Test
	public  void Empty_collection_Creation() throws JsonProcessingException, JSONException 
	{
		
		List<FolderRequest> item=new ArrayList<FolderRequest>();
		Info02 inf=new Info02("Sample Collection 2", "This is just a sample collection.", "https://schema.getpostman.com/json/collection/v2.1.0/collection.json");
		
		
		CollectionRequest coll=new CollectionRequest(inf,item);
		CollectionRootRequest collRoot=new CollectionRootRequest(coll);
		
		String collectionUID=RestAssured.given()
		.body(collRoot)
		.when().post("/collections")
		.then()
		.extract()
		.response()
		.path("collection.uid");
		
		
		CollectionRootResponse cr3=RestAssured.given()
		.pathParam("collectionUid", collectionUID)
		.when()
		.get("/collections/{collectionUid}")
		.then()
		.extract()
		.response().
		as(CollectionRootResponse.class);
		
		
		ObjectMapper objectMapper=new ObjectMapper();
		String collectionRootStr=objectMapper.writeValueAsString(collRoot);
		
		String deserlializedCollectionRootStr=objectMapper.writeValueAsString(cr3);
		
		JSONAssert.assertEquals(collectionRootStr, deserlializedCollectionRootStr, 
				new CustomComparator(JSONCompareMode.STRICT_ORDER, 
						new Customization("collection.item[*].item[*].request.url", new ValueMatcher<Object>() {
							
							@Override
							public boolean equal(Object o1, Object o2) {
								// TODO Auto-generated method stub
								return true;
							}
						})));
		
		
		
		MatcherAssert.assertThat(objectMapper.readTree(collectionRootStr),
				equalTo(objectMapper.readTree(deserlializedCollectionRootStr)));
		
		
		
//		List<String> UrlRequestList=new ArrayList<String>();
//		List<String> UrlResponseList=new ArrayList<String>();
//		
//		for (ItemRequest requestRootRequest : reqRootlList) {
//			System.out.println("The url in Request is :"+requestRootRequest.getRequest().getUrl());
//			UrlRequestList.add(requestRootRequest.getRequest().getUrl());
//			
//		}
//		
//		List<FolderResponse> folderResponseList=cr3.getCollection().getItem();
//		for(FolderResponse folderResponse: folderResponseList)
//		{
//			List<ItemResponse> requestRootResponseList=folderResponse.getItem();
//			for(ItemResponse requestRootResponse: requestRootResponseList)
//			{
//				URL urlResoponse=requestRootResponse.getRequest().getUrl();
//				System.out.println("The url in Response is :"+urlResoponse.getRaw());
//				UrlResponseList.add(urlResoponse.getRaw());
//			}
//		}
//		
//		MatcherAssert.assertThat( UrlResponseList, Matchers.containsInAnyOrder(UrlRequestList.toArray()));
//		
		
	}

}
