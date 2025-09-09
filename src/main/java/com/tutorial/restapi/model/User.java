package com.tutorial.restapi.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class User {
	
	@Id
	private int id;
	
	private String name;
	private String country;
	private LocalDate dateOfBirth;
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(int id, String name, String country, LocalDate dateOfBirth) {
		super();
		this.id = id;
		this.name = name;
		this.country = country;
		this.dateOfBirth = dateOfBirth;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
}
