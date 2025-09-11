package com.tutorial.restapi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tutorial.restapi.exception.UserNotFoundException;
import com.tutorial.restapi.model.User;
import com.tutorial.restapi.repository.UserJPARepository;

@Service
public class UserService {

	UserJPARepository userRepository;

	public UserService(UserJPARepository userJPARepository) {
		super();
		this.userRepository = userJPARepository;
	}

	public List<User> getUsers() {
		return userRepository.findAll();
	}

	public User getUser(int id) {
		return userRepository.findById(id).orElse(null);
	}

	public User createUser(User user) {
		return userRepository.save(user);
	}

	public User deleteUser(int id) {
		User user = getUser(id);
		if (user != null) {
			userRepository.deleteById(id);
		}
		return user;
	}
	
	public User updateUser(int id, User user) {
		User existingUser = getUser(id);
		if (existingUser == null) {
			throw new UserNotFoundException("User not found with id: " + id);
		}
		existingUser.setName(user.getName());
		existingUser.setCountry(user.getCountry());
		existingUser.setDateOfBirth(user.getDateOfBirth());
		User updatedUser = createUser(existingUser);
		return updatedUser;
	}

	
	
}
