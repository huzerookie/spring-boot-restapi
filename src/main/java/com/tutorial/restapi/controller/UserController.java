package com.tutorial.restapi.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tutorial.restapi.exception.UserNotFoundException;
import com.tutorial.restapi.model.Post;
import com.tutorial.restapi.model.User;
import com.tutorial.restapi.service.PostService;
import com.tutorial.restapi.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
@Tag(name = "User API", description = "CRUD operations for users")
public class UserController {
	UserService userService;
	PostService postService;

	public UserController(UserService userService, PostService postService) {
		super();
		this.userService = userService;
		this.postService = postService;
	}
	
	@Operation(summary = "Get all users")
	@GetMapping
	public ResponseEntity<List<User>> getUsers() {
		return ResponseEntity.ok(userService.getUsers());
	}

	@Operation(summary = "Get user by ID")
	@GetMapping("/{id}")
	public ResponseEntity<User> getUser(@PathVariable int id) {
		User user = userService.getUser(id);
		if (user == null) {
			throw new UserNotFoundException("User not found with id: " + id);
		}
		return ResponseEntity.ok(user);

	}

	@Operation(summary = "Create User")
	@PostMapping
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User savedUser = userService.createUser(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();
		return ResponseEntity.created(location).body(savedUser);
	}

	@Operation(summary = "Delete User by ID")
	@DeleteMapping("/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable int id) {
		userService.deleteUser(id);
		return ResponseEntity.noContent().build();
	}

	@Operation(summary = "Update existing User by ID")
	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@PathVariable int id, @Valid @RequestBody User user) {
		User updatedUser = userService.updateUser(id, user);
		return ResponseEntity.ok(updatedUser);
	}
	
	@Operation(summary = "Get all posts for a specific user by User ID")
	@GetMapping("/{id}/posts")
	public ResponseEntity<List<Post>> getPostsByUserId(@PathVariable int id) {
		User user = userService.getUser(id);
		if (user == null) {
			throw new UserNotFoundException("User not found with id: " + id);
		}
		return ResponseEntity.ok(user.getPosts());
	}
	
	@Operation(summary = "Get post by post ID for a specific user")
	@GetMapping("/{userId}/posts/{postId}")
	public ResponseEntity<Post> getPostByUserIdAndPostId(@PathVariable int userId, @PathVariable int postId) {
		User user = userService.getUser(userId);
		if (user == null) {
			throw new UserNotFoundException("User not found with id: " + userId);
		}
		Post post = user.getPosts().stream().filter(p -> p.getId() == postId).findFirst().orElse(null);
		if (post == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(post);
	}
	
	@Operation(summary = "Create a new post for a specific user by User ID")
	@PostMapping("/{id}/posts")
	public ResponseEntity<Post> createPostForUser(@PathVariable int id, @Valid @RequestBody Post post) {
		User user = userService.getUser(id);
		if (user == null) {
			throw new UserNotFoundException("User not found with id: " + id);
		}
		post.setUser(user);
		user.getPosts().add(post);
		userService.createUser(user); // Save the user to persist the new post
		postService.createPost(post); // Save the post
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{postId}").buildAndExpand(post.getId())
				.toUri();
		return ResponseEntity.created(location).body(post);
	}
	
}
