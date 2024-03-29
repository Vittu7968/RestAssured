package com.rest.pojo.collections;

import org.testng.annotations.Ignore;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CollectionRoot {
	Collection collectionMain;
	
	public CollectionRoot(){
		
	}
	
	public CollectionRoot(Collection collectionMain) {
		this.collectionMain=collectionMain;
		

	}

	public Collection getCollection() {
		return collectionMain;
	}

	public void setCollection(Collection collectionMain) {
		this.collectionMain = collectionMain;
	}
	

}
