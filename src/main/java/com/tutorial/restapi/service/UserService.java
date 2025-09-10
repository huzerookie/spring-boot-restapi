package com.tutorial.restapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tutorial.restapi.model.User;
import com.tutorial.restapi.repository.UserJPARepository;

@Service
public class UserService {

	@Autowired
	UserJPARepository userRepository;

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
	
	
}
