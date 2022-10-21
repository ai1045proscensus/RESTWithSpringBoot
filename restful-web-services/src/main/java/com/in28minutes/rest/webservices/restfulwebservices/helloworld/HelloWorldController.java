package com.in28minutes.rest.webservices.restfulwebservices.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// REST API
@RestController
public class HelloWorldController {
	
	// endpoint: /hello-world
	// return: "Hello World"
	//	@RequestMapping(method = RequestMethod.GET, path = "hello-world")
	//  @GetMapping is a composed annotation that acts as a shortcut for @RequestMapping(method = RequestMethod.GET).
	//	analog:
	//	@PostMapping
	//	@PutMapping usw...
	@GetMapping(path = "hello-world")
	public String helloWorld() {
		return "Hello World";
	}
	
	// rest api gibt json zurück nicht string=> gib bean zurück
	// zeigt am browser als JSON an:
	//	{"message":"alsdkfjölaskdjfölaskdjf"}
	//	just returned a bean back 
	// and this was AUTOMATICALLY CONVERTED into a JSON response.
	// (JACKSONHttpMessageConverters)
	@GetMapping(path = "hello-world-bean")
	public HelloWorldBean blablubb() {
		return new HelloWorldBean("alsdkfjölaskdjfölaskdjf");
	}

}


//Dinge die zu beachten sind:
//1) component scan => helloworld subpackage unter
//com.in28minutes.rest.webservices.restfulwebservices
//(=> da drin ist @SpringBootApplication => d.h. in dessen package 
//UND den sub-packages wird gescannt)
//2) @RestController
//3) @RequestMapping