package com.tutorial.restapi.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.tutorial.restapi.exception.UserNotFoundException;
import com.tutorial.restapi.model.User;

@Repository
public class UserRepository {
	private static List<User> userList;
	private static int idCounter=0;
	static {
		userList = new ArrayList<>();
		userList.add(new User(++idCounter, "Huzefa", "India", LocalDate.of(1996, 10, 18)));
		userList.add(new User(++idCounter, "Sakina", "UK", LocalDate.of(1998, 6, 12)));
		userList.add(new User(++idCounter, "Batul", "USA", LocalDate.of(2023, 04, 13)));
		userList.add(new User(++idCounter, "John", "UAE", LocalDate.of(1992, 1, 1)));
	}
	
	public List<User> getUsers(){
		return userList;
	}
	
	public User getUser(int id) {
		return userList.stream().filter(e->e.getId()==id).findFirst().orElseThrow(()->new UserNotFoundException("User Not Found with Id:"+id));
	}
	
	public User createUser(User user) {
		user.setId(++idCounter);
		userList.add(user);
		return user;
	}
}
