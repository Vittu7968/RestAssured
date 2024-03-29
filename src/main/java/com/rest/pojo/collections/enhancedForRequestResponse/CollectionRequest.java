package com.rest.pojo.collections.enhancedForRequestResponse;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rest.pojo.collections.Info;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CollectionRequest extends CollectionBase {
	
	List<FolderRequest> item;
	
	public CollectionRequest()
	{
		
	}
	
	public CollectionRequest(Info02 inf, List<FolderRequest> item2)
	{
		super(inf);
		this.item=item2;
	}
	
	public List<FolderRequest> getItem() {
		return item;
	}
	public void setItem(List<FolderRequest> item) {
		this.item = item;
	}
}
