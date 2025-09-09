package com.tutorial.restapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tutorial.restapi.model.User;
import com.tutorial.restapi.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	public List<User> getUsers(){
		return userRepository.getUsers();
	}
	
	public User getUser(int id){
		return userRepository.getUser(id);
	}
	
	public User createUser(User user) {
		return userRepository.createUser(user);
	}
	
	
}
