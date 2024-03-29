package com.rest.pojo.collections.enhancedForRequestResponse;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestResponse extends RequestBase {
	
	//Changing the url from String to Object, as in get response URL is a json object
//	private String url;
	
	private URL url;

	
	public URL getUrl() {
		return url;
	}

	public void setUrl(URL url) {
		this.url = url;
	}

	public RequestResponse()
	{
	}
	
	public RequestResponse(URL url,String method,List<Header02> header,Body02 body,String description)
	{
		super(method, header, body ,description);
		this.url=url;
	}
	
//	public String getUrl() {
//		return url;
//	}
//	public void setUrl(String url) {
//		this.url = url;
//	}

}
