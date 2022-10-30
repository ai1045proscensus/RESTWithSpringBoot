package com.in28minutes.rest.webservices.restfulwebservices.helloworld;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

// REST API
@RestController
public class HelloWorldController {
	
	// Strategy interface for resolving messages, with support for the parameterization and internationalization of such messages.
//	Spring provides two out-of-the-box implementations for production:
//	ResourceBundleMessageSource: built on top of the standard java.util.ResourceBundle, sharing its limitations.
//	ReloadableResourceBundleMessageSource: highly configurable, in particular with respect to reloading message definitions.
	private MessageSource messageSource;
	
	
	public HelloWorldController(MessageSource msgSource) {
		// Constructor injection
		messageSource = msgSource;
	}

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

	//	path variables are nothing but the variable values in your URIs.

	// /users/{id}/todos/{id} => /users/2/todos/200
	// /hello-world/path-variable/{name}
	// /hello-world/path-variable/Ranga

	@GetMapping(path = "/hello-world/path-variable/users/{id}/todos/{id2}")
	public HelloWorldBean aslödkfj(@PathVariable String id, @PathVariable String id2) {
		return new HelloWorldBean("id1: " + id + " ; id2: " + id2);

	}
	
	@GetMapping(path = "hello-world-internationalized")
	public String helloWorldInternationalized() {
//		return the locale associated with the current thread.
//		Otherwise it would return the system Default local.
//		So along with the request when user sends accept language in request header,
//		the locale for that header will be returned 
		Locale locale = LocaleContextHolder.getLocale(); // utility method to get the locale
		String returnValue = messageSource.getMessage("good.morning.message", null, "pulinamadi", locale);
		return returnValue;
		
		//1: define those value somewhere (property file: messages.properties in src/main/resources - standard way 4 i18n)
		//2: write code to pick them up (messageSource)
		
//		- Example: `en` - English (Good Morning)
//		- Example: `nl` - Dutch (Goedemorgen)
//		- Example: `fr` - French (Bonjour)
//		- Example: `de` - Deutsch (Guten Morgen)
	}

}

//Dinge die zu beachten sind:
//1) component scan => helloworld subpackage unter
//com.in28minutes.rest.webservices.restfulwebservices
//(=> da drin ist @SpringBootApplication => d.h. in dessen package 
//UND den sub-packages wird gescannt)
//2) @RestController
//3) @RequestMapping