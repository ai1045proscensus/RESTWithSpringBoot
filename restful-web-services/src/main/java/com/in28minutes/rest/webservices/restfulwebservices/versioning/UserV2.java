package com.in28minutes.rest.webservices.restfulwebservices.versioning;

public class UserV2 {
	
	private Name name;
	
	public UserV2(Name name) {
		super();
		this.name = name;
	}

	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}
	
	

}
