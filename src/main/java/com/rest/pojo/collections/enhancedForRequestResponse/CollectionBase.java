package com.rest.pojo.collections.enhancedForRequestResponse;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class CollectionBase {
	
	Info02 info;
	
	public CollectionBase()
	{
		
	}
	
	public CollectionBase(Info02 info)
	{
		this.info=info;
	}
	
	
	public Info02 getInfo() {
		return info;
	}
	public void setInfo(Info02 info) {
		this.info = info;
	}
}
