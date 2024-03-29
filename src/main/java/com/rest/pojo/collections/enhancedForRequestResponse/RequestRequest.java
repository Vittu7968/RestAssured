package com.rest.pojo.collections.enhancedForRequestResponse;

import java.util.List;
import com.rest.pojo.collections.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestRequest extends RequestBase {
	
	//Changing the url from String to Object, as in get response URL is a json object
//	private String url;
	
	private String url;
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	
	public RequestRequest()
	{
	}
	
	public RequestRequest(String url,String method,List<Header02> listHeader,Body02 body,String description)
	{
		super(method, listHeader, body ,description);
		this.url=url;
	}
	
//	public String getUrl() {
//		return url;
//	}
//	public void setUrl(String url) {
//		this.url = url;
//	}
}
