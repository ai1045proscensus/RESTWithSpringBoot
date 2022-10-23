package com.in28minutes.rest.webservices.restfulwebservices.resources.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

//to be able to play with a DB and to perform operations on entities,
//You would write a DAO object, data access object/DAO service to manage this.

//Indicates that an annotated class is a "component".
//Such classes are considered as CANDIDATES FOR AUTO-DETECTION 
//when using annotation-based configuration and classpath scanning.
// "I want spring to manage this, to find it for me (injection)"
@Component
public class UserDaoService {
	// verwendung dann böyle: einfach
	//	@Autowired
	//	UserDaoService daoService; 
	// injecten olunuyor
	
	// later: change rest-api to talk to db: JPA/Hibernate > Database
	// first: talk to list: UserDaoService > Static List
	
	private static List<User> users = new ArrayList<>();
	
	// Later, we'll actually have a SEQUENCE which is generated
	// in the DB and we'll be mapping to it using JPA and hibernate.
	private static int id = 1;
	
	static {
		users.add(new User(id++, "Adam", LocalDate.now().minusYears(30)));
		users.add(new User(id++, "Eve", LocalDate.now().minusYears(25)));
		users.add(new User(id++, "Jim", LocalDate.now().minusYears(20)));
	}
	
	
	public List<User> findAll() {
		return users;
	}
	
	
	public User getUser(int id) {
		User theUser = null;
		for (User user : users) {
			if (user.getId() == id) {
				theUser = user;
				break;
			}
		}
		return theUser;
	}

	public User persistUser(User user) {
		user.setId(id++);
		users.add(user);
		return user;
	}

}
