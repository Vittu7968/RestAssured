package com.rest.pojo.collections;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Collection {
	
	Info info;
	List<Folder> item;
	
	public Collection()
	{
		
	}
	
	public Collection(Info info, List<Folder> item2)
	{
		this.info=info;
		this.item=item2;
	}
	
	
	public Info getInfo() {
		return info;
	}
	public void setInfo(Info info) {
		this.info = info;
	}
	public List<Folder> getItem() {
		return item;
	}
	public void setItem(List<Folder> item) {
		this.item = item;
	}
}
