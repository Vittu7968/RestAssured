package com.rest.pojo.collections;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Request {
	
	//Changing the url from String to Object, as in get response URL is a json object
//	private String url;
	
	private Object url;
	public Object getUrl() {
		return url;
	}

	public void setUrl(Object url) {
		this.url = url;
	}
	private String method;
	private List<Header> header;
	private Body body;
	private String description;
	
	public Request()
	{
	}
	
	public Request(Object url,String method,List<Header> header,Body body,String description)
	{
		this.url=url;
		this.method=method;
		this.header=header;
		this.body=body;
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
	public List<Header> getHeader() {
		return header;
	}
	public void setHeader(List<Header> header) {
		this.header = header;
	}
	public Body getBody() {
		return body;
	}
	public void setBody(Body body) {
		this.body = body;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	
	

}