package com.in28minutes.rest.webservices.restfulwebservices.resources.user;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
	public ResponseEntity<Object> persistUser(@RequestBody User user) {
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
		User persistedUser = daoService.persistUser(user);
		
		//in ResponseEntity there are different methods for different statuses
		// (accepted, bad request created, no content, not found.
		// => number of methods for the response status you want to return back.
		
		//Create a new builder with a CREATED status (steht für 201) and a location header set to the given URI.
		// RESPONSEENTITY: Extension of HttpEntity (normaler response) that ADDS AN HTTPSTATUS STATUS CODE.
		// => WIR MÜSSEN RESPONSEENTITY ZURÜCKGEBEN (return)
		
		// to return the URI (location) of the user who is angelegt: 
		
		// location: users/4 => users/{id}
		// => user can check direkt
		// => you need to make use of a specific HTTP header: location header => created method of ResponseEntity:
		
		//		ResponseEntity.created(URI location)
		//		Create a new builder with a CREATED status and A LOCATION HEADER SET TO THE GIVEN URI.
		//		Parameters: location the location URI
		URI location = ServletUriComponentsBuilder.fromCurrentRequest() // to the URI of the current request
				.path("/{id}") // we want to add a "/{id}"
				.buildAndExpand(persistedUser.getId()) // and we want to replace this "/{id}" with the ID of the angelegter user
				.toUri(); // convert it to a URI
		return ResponseEntity.created(location).build(); // RETURN HTTPRESPONSE MIT 201 RESPONSE UND LOCATION VON ANGELEGTEM USER
		
		// so mit hardcoded string auch möglich, aber mit ServletUriComponentsBuilder besser:
		// return ResponseEntity.created(URI.create("users/" + persistedUser.getId())).build();
	}
	
	// Delete a User
	// DELETE /users/{id} -> /users/1
	@DeleteMapping(path = "users/{id}")
	public User deleteUser(@PathVariable int id) {
		return daoService.deleteUser(id);
	}
	
	

}
