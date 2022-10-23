package com.in28minutes.rest.webservices.restfulwebservices.resources.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

//REST API
@RestController
public class UserController {

	@Autowired
	UserDaoService daoService;
	
	//	autowiring geht auch über KONSTRUKTOR-INJECTION, OHNE @Autowired!
	//	
	//	private UserDaoService service;
	//
	//	public UserResource(UserDaoService service) {
	//		this.service = service;
	//	}

	//	GET /users
	@GetMapping(path = "users")
	public List<User> retrieveAllUsers() {
		return daoService.findAll();
	}
	
	//	Retrieve one User
	//	GET /users/{id} -> /users/1
	@GetMapping(path = "users/{id}")
	public User getUser(@PathVariable int id) {
		return daoService.getUser(id);
	}
}
