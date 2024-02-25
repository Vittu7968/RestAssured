package com.rest.pojo.assignment;

public class users_rootpojo {
	
	Address address;
	Geo geo;
	private String name;
	private String username;
	private String email;
	
	private int id;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public users_rootpojo()
	{
		
	}

	public users_rootpojo(String name,String username, 
			String email,Address address,Geo geo)
	{
		this.name = name;
		this.username = username;
		this.email = email;
		this.address=address;
		this.geo=geo;
		
	}
	
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public Geo getGeo() {
		return geo;
	}
	public void setGeo(Geo geo) {
		this.geo = geo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}


}
