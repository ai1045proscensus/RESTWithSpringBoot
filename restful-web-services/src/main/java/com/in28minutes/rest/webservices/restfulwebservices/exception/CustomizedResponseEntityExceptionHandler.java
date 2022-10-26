package com.in28minutes.rest.webservices.restfulwebservices.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.in28minutes.rest.webservices.restfulwebservices.resources.user.UserNotFoundException;

//@ControllerAdvice : to be able to be picked up by spring framework. (Specialization of @Component
// for classes that declare @ExceptionHandler, ...)
// => make this applicable to all the controllers and restcontrollers in the  project
@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler {

	// ResponseEntityExceptionHandler.handleException'den esinlendi (copy+anpassen):

	// @ExceptionHandler is how you say what exceptions you'd want to handle
	@ExceptionHandler(value = Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
		ErrorDetails errorDetails = getErrorDetails(ex, request);
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(value = UserNotFoundException.class)
	public final ResponseEntity<Object> handleUserNotFoundExceptions(Exception ex, WebRequest request) {
		ErrorDetails errorDetails = getErrorDetails(ex, request);
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

	private ErrorDetails getErrorDetails(Exception ex, WebRequest request) {
		// build our own custom exception object (dh die custom error message und response status)
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(),
				request.getDescription(false));
		return errorDetails;
	}

}
