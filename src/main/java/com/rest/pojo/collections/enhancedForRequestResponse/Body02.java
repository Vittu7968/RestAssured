package com.rest.pojo.collections.enhancedForRequestResponse;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Body02 {
	
	private String mode;
	private String raw;
	
	public Body02()
	{
	}
	
	public Body02(String mode, String raw)
	{
		this.mode=mode;
		this.raw=raw;
	}
	
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getRaw() {
		return raw;
	}
	public void setRaw(String raw) {
		this.raw = raw;
	}
	

}
