package com.rest.pojo.collections.enhancedForRequestResponse;

import org.testng.annotations.Ignore;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public abstract  class CollectionRootBase {
	
	public CollectionRootBase(){
		
	}
	

}
