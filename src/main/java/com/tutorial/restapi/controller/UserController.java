package com.tutorial.restapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tutorial.restapi.exception.UserNotFoundException;
import com.tutorial.restapi.model.User;
import com.tutorial.restapi.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
@Tag(name = "User API", description = "CRUD operations for users")
public class UserController {

	@Autowired
	UserService userService;

	@Operation(summary = "Get all users")
	@GetMapping
	public ResponseEntity<List<User>> getUsers() {
		return new ResponseEntity<List<User>>(userService.getUsers(), HttpStatus.OK);
	}

	@Operation(summary = "Get user by ID")
	@GetMapping("/{id}")
	public ResponseEntity<User> getUser(@PathVariable int id) {
		User user = userService.getUser(id);
		if (user == null) {
			throw new UserNotFoundException("User not found with id: " + id);
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@Operation(summary = "Create User")
	@PostMapping
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User userObj = userService.createUser(user);
		return new ResponseEntity<User>(userObj, HttpStatus.OK);
	}

	@Operation(summary = "Delete User by ID")
	@DeleteMapping("/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable int id) {
		User user = userService.deleteUser(id);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@Operation(summary = "Update existing User by ID")
	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@PathVariable int id, @Valid @RequestBody User user) {
		User existingUser = userService.getUser(id);
		if (existingUser == null) {
			throw new UserNotFoundException("User not found with id: " + id);
		}
		existingUser.setName(user.getName());
		existingUser.setCountry(user.getCountry());
		existingUser.setDateOfBirth(user.getDateOfBirth());
		User updatedUser = userService.createUser(existingUser);
		return new ResponseEntity<User>(updatedUser, HttpStatus.OK);
	}

}
