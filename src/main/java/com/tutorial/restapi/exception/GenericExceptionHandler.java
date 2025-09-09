package com.tutorial.restapi.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.tutorial.restapi.model.ErrorResponse;

@ControllerAdvice
public class GenericExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleException(Exception ex, WebRequest req){
		ErrorResponse res = new ErrorResponse(ex.getLocalizedMessage(), LocalDateTime.now(), req.getDescription(false));
		return new ResponseEntity<ErrorResponse>(res,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException ex, WebRequest req){
		ErrorResponse res = new ErrorResponse(ex.getLocalizedMessage(), LocalDateTime.now(), req.getDescription(false));
		return new ResponseEntity<ErrorResponse>(res,HttpStatus.NOT_FOUND);
	}
}
