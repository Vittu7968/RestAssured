package com.rest.pojo.workspace;

public class workspace_pojo {
	
	private String id;
	
	private String name;
	private String description;
	private String type;
	
	public  workspace_pojo()
	{
	}
	
	public  workspace_pojo(String name, String description, String type)
	{
		this.name=name;
		this.description=description;
		this.type=type;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	

}
