package com.rest.pojo.collections.enhancedForRequestResponse;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ItemRequest extends ItemBase {
	private RequestRequest request;
	public ItemRequest()
	{
		
	}
	
	public ItemRequest(String name, RequestRequest request)
	{
		super(name);
		this.request=request;
	}
		
	public RequestRequest getRequest() {
		return request;
	}
	public void setRequest(RequestRequest request) {
		this.request = request;
	}


}
