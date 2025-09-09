package com.tutorial.restapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tutorial.restapi.model.User;
import com.tutorial.restapi.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userService;
	
	@GetMapping("/users")
	public ResponseEntity<List<User>> getUsers(){
		return new ResponseEntity<List<User>>(userService.getUsers(), HttpStatus.OK);
	}
	
	@GetMapping("/users/{id}")
	public ResponseEntity<User> getUser(@PathVariable int id){
		User user = userService.getUser(id);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@PostMapping("/users")
	public ResponseEntity<User> createUser(@RequestBody User user){
		User userObj = userService.createUser(user);
		return new ResponseEntity<User>(userObj, HttpStatus.OK);
	}
}
