package com.rest.pojo.collections.enhancedForRequestResponse;

import org.testng.annotations.Ignore;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public  class CollectionRootResponse extends CollectionRootBase {
	CollectionResponse collectionMain;
	
	public CollectionRootResponse(){
		
	}
	
	public CollectionRootResponse(CollectionResponse collectionMain) {
		this.collectionMain=collectionMain;
		

	}

	public CollectionResponse getCollection() {
		return collectionMain;
	}

	public void setCollection(CollectionResponse collectionMain) {
		this.collectionMain = collectionMain;
	}
	

}
