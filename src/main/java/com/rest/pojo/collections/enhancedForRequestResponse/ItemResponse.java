package com.rest.pojo.collections.enhancedForRequestResponse;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ItemResponse extends ItemBase {
	
	private RequestResponse request;
	public ItemResponse()
	{
		
	}
	
	public ItemResponse(String name, RequestResponse request)
	{
		super(name);
		this.request=request;
	}
	
	public RequestResponse getRequest() {
		return request;
	}
	public void setRequest(RequestResponse request) {
		this.request = request;
	}


}
