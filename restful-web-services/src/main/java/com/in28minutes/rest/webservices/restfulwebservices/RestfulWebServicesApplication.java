package com.in28minutes.rest.webservices.restfulwebservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestfulWebServicesApplication {

	// LOCAL: we run this class to launch app
	// PROD: we run jar file
	// DevTools is DISABLED when running jar file!
	// => PROD: NO DEVTOOLS => NO STACKTRACE!
	// (der ganze stracktrace bei änderung kommt von devtools)
	public static void main(String[] args) {
		SpringApplication.run(RestfulWebServicesApplication.class, args);
	}

}
