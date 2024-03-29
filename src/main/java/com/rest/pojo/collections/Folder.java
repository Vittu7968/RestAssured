package com.rest.pojo.collections;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Folder {
	
	private String name;
	List<Item> item;
	
	public Folder()
	{
		
	}
	
	public Folder(String name, List<Item> item)
	{
		this.name=name;
		this.item=item;
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Item> getItem() {
		return item;
	}
	public void setItem(List<Item> item) {
		this.item = item;
	}


}
