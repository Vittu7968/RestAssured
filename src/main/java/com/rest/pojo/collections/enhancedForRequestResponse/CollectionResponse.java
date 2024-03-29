package com.rest.pojo.collections.enhancedForRequestResponse;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CollectionResponse extends CollectionBase {
	
	List<FolderResponse> item;
	
	public CollectionResponse()
	{
		
	}
	
	public CollectionResponse(Info02 info, List<FolderResponse> item2)
	{
		super(info);
		this.item=item2;
	}
	
	public List<FolderResponse> getItem() {
		return item;
	}
	public void setItem(List<FolderResponse> item) {
		this.item = item;
	}
}
