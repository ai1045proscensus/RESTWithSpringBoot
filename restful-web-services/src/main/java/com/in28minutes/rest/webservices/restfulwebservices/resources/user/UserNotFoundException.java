package com.in28minutes.rest.webservices.restfulwebservices.resources.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// @ResponseStatus: Marks a method or exception class with the STATUS CODE and reason THAT SHOULD BE RETURNED.
// CODE: THE STATUS CODE TO USE FOR THE RESPONSE.
// Default is HttpStatus.INTERNAL_SERVER_ERROR, which should typically be changed to something more appropriate.
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

	public UserNotFoundException(String message) {
		super(message);
	}

	

}
