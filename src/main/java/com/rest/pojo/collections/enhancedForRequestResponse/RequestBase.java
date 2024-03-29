package com.rest.pojo.collections.enhancedForRequestResponse;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rest.pojo.collections.enhancedForRequestResponse.*;
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class RequestBase {
	
	//Changing the url from String to Object, as in get response URL is a json object
//	private String url;
	private String method;
	private List<Header02> header;
	private Body02 body;
	private String description;
	
	public RequestBase()
	{
	}
	
	public RequestBase(String method,List<Header02> listHeader,Body02 body2,String description)
	{
		this.method=method;
		this.header=listHeader;
		this.body=body2;
		this.description=description;
	}
	
//	public String getUrl() {
//		return url;
//	}
//	public void setUrl(String url) {
//		this.url = url;
//	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public List<Header02> getHeader() {
		return header;
	}
	public void setHeader(List<Header02> header) {
		this.header = header;
	}
	public Body02 getBody() {
		return body;
	}
	public void setBody(Body02 body) {
		this.body = body;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	
	

}
