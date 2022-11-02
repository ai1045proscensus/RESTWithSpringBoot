package com.in28minutes.rest.webservices.restfulwebservices.versioning;

public class UserV1 {

	private String name;
	
	public UserV1(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "UserV1 [name=" + name + "]";
	}
	
	
}
