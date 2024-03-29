package com.rest.pojo.collections.enhancedForRequestResponse;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class ItemBase {
	
	private String name;
	
	public ItemBase()
	{
		
	}
	
	public ItemBase(String name)
	{
		this.name=name;
	}
		
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
