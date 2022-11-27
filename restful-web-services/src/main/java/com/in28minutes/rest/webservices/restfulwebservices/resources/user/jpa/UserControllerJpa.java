/**
 * UserController kopiert damit rumspielen können...
 */


package com.in28minutes.rest.webservices.restfulwebservices.resources.user.jpa;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.in28minutes.rest.webservices.restfulwebservices.resources.user.User;
import com.in28minutes.rest.webservices.restfulwebservices.resources.user.UserDaoService;

//REST API
@RestController
public class UserControllerJpa {

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
	@GetMapping(path = "/springdatajpa/users")
	public List<User> retrieveAllUsers() {
		return daoService.findAll();
	}

	// Retrieve one User
	// GET /users/{id} -> /users/1
	@GetMapping(path = "/springdatajpa/users/{id}")
	public EntityModel<User> getUser(@PathVariable int id) {
		User user = daoService.getUser(id);
		
//		HAL (JSON Hypertext Application Language): Simple format („_links“:…)
//		that gives a consistent and easy way to HYPERLINK BETWEEN RESOURCES IN YOUR API 
//		Spring HATEOAS: Generate HAL responses with hyperlinks to resources 
//		(hateoas: in addition to data we return a few links to tell the consumers
//		 about how to perform other actions)
//
//		So whenever you see responses from HAL compatible APIs, you should see links coming back like this:
//
//		{
//		    "name": "Adam",
//		    "birthDate": "1992-08-19",
//		    "_links": {
//		        "all-users": {
//		            "href": "http://localhost:8080/users"
//		        }
//		    }
//		}
//
//		And to be able to generate this kind of response, we can make use of spring hateoas
		
//		(if your API is compliant with HAL, you can use Hal Explorer to explore the API.)
		
//		getUser: in addition to returning the data, I would want to return a link to the
//		users back. => add a link to //http://localhost:8080/users
//
//		=> bunun icin de hateoas’in
//		//EntityModel: A simple EntityModel wrapping a domain object and adding links to it.
//		ve
//		//WebMvcLinkBuilder (um links zu builden)
//		leri lazim. (wrap the user in entity model.)
		
		EntityModel<User> entityModel = EntityModel.of(user);
		
//		link pointing to the controller method.
//		methodOn: pick up the link to a specific method and add it as a link.
//		(What we want to do is add a link to the retrieve all users method.
//		I can actually hardcode the URL, but if the URL changes, you have to change the link)
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());

		entityModel.add(link.withRel("all-users")); // all-users: bezeichner für den link
		return entityModel;
	}

	// Create a User
	// POST /users
	@PostMapping("/springdatajpa/users")
	// content typically is sent as part of the request body.
	// the way we can accept that is by using @RequestBody:
	// Annotation indicating a METHOD PARAMETER SHOULD BE BOUND TO THE BODY OF THE
	// WEB REQUESt.
	// @Valid: Whenever the binding happens (user input zu bean user mappen/binden),
	// the validations which are defined on your bean (User) are automatically
	// invoked.
	public ResponseEntity<Object> persistUser(@Valid @RequestBody User user) {
		// In the web request which we sent in, wE WILL SEND A BODY ALONG WITH IT,
		// and in the body of the request we will have all the details of the user
		// and that would be mapped to the user Bean

		// The thing is, you cannot execute post requests directly from the browser.
		// You need some kind of rest API client to be able to fire post requests.
		// One of the simplest rest API clients is TALEND API TESTER:
		// "Talend API Tester makes it easy to invoke, discover and test HTTP and REST
		// APIs. "
		// "Visuelle Interaktion mit REST, SOAP und HTTP APIs.
		// Send requests and inspect responses"
		// (Chrome extension)
		User persistedUser = daoService.persistUser(user);

		// in ResponseEntity there are different methods for different statuses
		// (accepted, bad request created, no content, not found.
		// => number of methods for the response status you want to return back.

		// Create a new builder with a CREATED status (steht für 201) and a location
		// header set to the given URI.
		// RESPONSEENTITY: Extension of HttpEntity (normaler response) that ADDS AN
		// HTTPSTATUS STATUS CODE.
		// => WIR MÜSSEN RESPONSEENTITY ZURÜCKGEBEN (return)

		// to return the URI (location) of the user who is angelegt:

		// location: users/4 => users/{id}
		// => user can check direkt
		// => you need to make use of a specific HTTP header: location header => created
		// method of ResponseEntity:

		// ResponseEntity.created(URI location)
		// Create a new builder with a CREATED status and A LOCATION HEADER SET TO THE
		// GIVEN URI.
		// Parameters: location the location URI
		URI location = ServletUriComponentsBuilder.fromCurrentRequest() // to the URI of the current request
				.path("/{id}") // we want to add a "/{id}"
				.buildAndExpand(persistedUser.getId()) // and we want to replace this "/{id}" with the ID of the
														// angelegter user
				.toUri(); // convert it to a URI
		return ResponseEntity.created(location).build(); // RETURN HTTPRESPONSE MIT 201 RESPONSE UND LOCATION VON
															// ANGELEGTEM USER

		// so mit hardcoded string auch möglich, aber mit ServletUriComponentsBuilder
		// besser:
		// return ResponseEntity.created(URI.create("users/" +
		// persistedUser.getId())).build();
	}

	// Delete a User
	// DELETE /users/{id} -> /users/1
	@DeleteMapping(path = "/springdatajpa/users/{id}")
	public EntityModel<User> deleteUser(@PathVariable int id) {
		User user = daoService.deleteUser(id);
		EntityModel<User> entityModel = EntityModel.of(user);
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		entityModel.add(link.withRel("remaining-users"));
		return entityModel;

	}

}
