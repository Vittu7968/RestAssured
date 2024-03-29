package com.rest.pojo.collections.enhancedForRequestResponse;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Info02 {
	
	private String name;
	private String description;
	private String schema;
	
	
	//Default Constructor
	public Info02()
	{
		
	}
	
	public Info02(String name, String description, String schema)
	{
		this.name=name;
		this.description=description;
		this.schema=schema;
		
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSchema() {
		return schema;
	}
	public void setSchema(String schema) {
		this.schema = schema;
	}

	

}
