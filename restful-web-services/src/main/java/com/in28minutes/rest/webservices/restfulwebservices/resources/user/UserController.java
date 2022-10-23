package com.in28minutes.rest.webservices.restfulwebservices.resources.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//REST API
@RestController
public class UserController {

	@Autowired
	UserDaoService daoService;

	// autowiring geht auch über KONSTRUKTOR-INJECTION, OHNE @Autowired!
	//
	// private UserDaoService service;
	//
	// public UserResource(UserDaoService service) {
	// this.service = service;
	// }

	// GET /users
	@GetMapping(path = "users")
	public List<User> retrieveAllUsers() {
		return daoService.findAll();
	}

	// Retrieve one User
	// GET /users/{id} -> /users/1
	@GetMapping(path = "users/{id}")
	public User getUser(@PathVariable int id) {
		return daoService.getUser(id);
	}

	// Create a User
	// POST /users
	@PostMapping("users")
	// content typically is sent as part of the request body.
	// the way we can accept that is by using @RequestBody:
	// Annotation indicating a METHOD PARAMETER SHOULD BE BOUND TO THE BODY OF THE
	// WEB REQUESt.
	public void persistUser(@RequestBody User user) {
		// In the web request which we sent in, wE WILL SEND A BODY ALONG WITH IT,
		// and in the body of the request we will have all the details of the user
		// and that would be mapped to the user Bean

		// The thing is, you cannot execute post requests directly from the browser.
		// You need some kind of rest API client to be able to fire post requests.
		// One of the simplest rest API clients is TALEND API TESTER:
		// "Talend API Tester makes it easy to invoke, discover and test HTTP and REST APIs. "
		// "Visuelle Interaktion mit REST, SOAP und HTTP APIs. 
		// Send requests and inspect responses"
		// (Chrome extension)
		daoService.persistUser(user);
	}

}
