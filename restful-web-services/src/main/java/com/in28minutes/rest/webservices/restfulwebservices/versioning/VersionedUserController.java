package com.in28minutes.rest.webservices.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionedUserController {
	
	public UserV1 getFirstVersionOfUser() {
		return new UserV1("ilkad soyad");
	}
	
	public UserV2 getSecondVersionOfUser() {
		return new UserV2(new Name("Bo", "Agar"));
	}

	@GetMapping("/v1/users")
	public UserV1 url1() {
		return getFirstVersionOfUser();
	}
	
	@GetMapping("/v2/users")
	public UserV2 url2() {
		return getSecondVersionOfUser();
	}
	
	@GetMapping(path = "users", params = "v1")
	UserV1 requestParameter1(){
		return getFirstVersionOfUser();
	}
	
	@GetMapping(path = "users", params = "v2")
	UserV2 requestParameter2(){
		return getSecondVersionOfUser();
	}
	
	// in talend bei headers eintrag BLUBB mit value 1 eintragen
	@GetMapping(path = "/users/header", headers = "BLUBB=1")
	UserV1 header1(){
		return getFirstVersionOfUser();
	}
	
	@GetMapping(path = "/users/header", headers = "BLUBB=2")
	UserV2 header2(){
		return getSecondVersionOfUser();
	}
	
	// in talend bei accept header "application/bla1+json" eintragen
	// in echt: application/vnd.company.app-v1+json
	@GetMapping(path = "/users/accept", produces = "application/bla1+json")
	UserV1 acceptHeader1(){
		return getFirstVersionOfUser();
	}
	
	@GetMapping(path = "/users/accept", produces = "application/bla2+json")
	UserV2 acceptHeader2(){
		return getSecondVersionOfUser();
	}
}
