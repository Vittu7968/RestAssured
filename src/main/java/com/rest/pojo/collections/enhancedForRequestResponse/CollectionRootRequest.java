package com.rest.pojo.collections.enhancedForRequestResponse;

import org.testng.annotations.Ignore;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public  class CollectionRootRequest extends CollectionRootBase{
	CollectionRequest collectionMain;
	
	public CollectionRootRequest(){
		
	}
	
	public CollectionRootRequest(CollectionRequest collectionMain) {
		this.collectionMain=collectionMain;
		

	}

	public CollectionRequest getCollection() {
		return collectionMain;
	}

	public void setCollection(CollectionRequest collectionMain) {
		this.collectionMain = collectionMain;
	}
	

}
