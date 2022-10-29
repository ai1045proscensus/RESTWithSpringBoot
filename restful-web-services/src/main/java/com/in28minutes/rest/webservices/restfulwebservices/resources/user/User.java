package com.in28minutes.rest.webservices.restfulwebservices.resources.user;

import java.time.LocalDate;

import javax.annotation.Resource;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

// Resource
@Resource
public class User {
	
	private int id;
	
	@Size(min = 2)
	private String name;
	// auch selber Fehlermeldung angebbar:
	// @Size(min = 2, message = "size must be bigger than 2 bla AÖLSDKFJASLÖDFKJASÖLDKFJLSAÖKDFJSLÖDKFJ")
	
	@Past
	private LocalDate birthDate;
	// auch hier mit message fehlermeldung angebbar..
	
	public User(int id, String name, LocalDate birthDate) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", birthDate=" + birthDate + "]";
	}

	
}
