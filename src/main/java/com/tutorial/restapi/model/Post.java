package com.tutorial.restapi.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "posts")
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "post_seq")
    @SequenceGenerator(name = "post_seq", sequenceName = "post_sequence", initialValue = 20003, allocationSize = 50)
	private int id;

	@Size(min = 2, message = "Description should have at least 2 characters")
	private String description;

	@Future(message = "Deadline should be in the future")
	private LocalDate deadline;

	// Many-to-One relationship with User
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private User user;
	public Post() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Post(int id, @Size(min = 2, message = "Description should have at least 2 characters") String description,
			@Future(message = "Deadline should be in the future") LocalDate deadline, User user) {
		super();
		this.id = id;
		this.description = description;
		this.deadline = deadline;
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getDeadline() {
		return deadline;
	}

	public void setDeadline(LocalDate deadline) {
		this.deadline = deadline;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
