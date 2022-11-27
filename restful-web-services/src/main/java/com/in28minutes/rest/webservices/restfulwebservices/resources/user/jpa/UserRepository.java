package com.in28minutes.rest.webservices.restfulwebservices.resources.user.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.in28minutes.rest.webservices.restfulwebservices.resources.user.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
