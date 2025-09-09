package com.tutorial.restapi.model;

import java.time.LocalDateTime;

public class ErrorResponse {
	private String exception;
	private LocalDateTime timestamp;
	private String description;
	
	public ErrorResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ErrorResponse(String exception, LocalDateTime timestamp, String description) {
		super();
		this.exception = exception;
		this.timestamp = timestamp;
		this.description = description;
	}
	public String getException() {
		return exception;
	}
	public void setException(String exception) {
		this.exception = exception;
	}
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
