package com.in28minutes.rest.webservices.restfulwebservices.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

// REST API
@RestController
public class HelloWorldController {

	// endpoint: /hello-world
	// return: "Hello World"
	// @RequestMapping(method = RequestMethod.GET, path = "hello-world")
	// @GetMapping is a composed annotation that acts as a shortcut for
	// @RequestMapping(method = RequestMethod.GET).
	// analog:
	// @PostMapping
	// @PutMapping usw...
	@GetMapping(path = "hello-world")
	public String helloWorld() {
		return "Hello World";
	}

	// rest api gibt json zurück nicht string=> gib bean zurück
	// zeigt am browser als JSON an:
	// {"message":"alsdkfjölaskdjfölaskdjf"}
	// just returned a bean back
	// and this was AUTOMATICALLY CONVERTED into a JSON response.
	// (JACKSONHttpMessageConverters)
	@GetMapping(path = "hello-world-bean")
	public HelloWorldBean blablubb() {
		return new HelloWorldBean("alsdkfjölaskdjfölaskdjf");
	}

	// @PathVariable
	// Annotation which indicates that a method parameter should be bound to a URI
	// template variable. Supported for RequestMapping annotated handler methods.
	// /users/{id}/todos/{id} => /users/2/todos/200
	// /hello-world/path-variable/{name}
	// /hello-world/path-variable/Ranga

	@GetMapping(path = "/hello-world/path-variable/{name}")
	public HelloWorldBean aslödkfj(@PathVariable String name) {
		return new HelloWorldBean("input: " + name);

	}

}

//Dinge die zu beachten sind:
//1) component scan => helloworld subpackage unter
//com.in28minutes.rest.webservices.restfulwebservices
//(=> da drin ist @SpringBootApplication => d.h. in dessen package 
//UND den sub-packages wird gescannt)
//2) @RestController
//3) @RequestMapping