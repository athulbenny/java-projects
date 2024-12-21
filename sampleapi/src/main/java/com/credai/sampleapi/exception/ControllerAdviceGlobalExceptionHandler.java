package com.credai.sampleapi.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerAdviceGlobalExceptionHandler extends ResponseEntityExceptionHandler{
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		
		CustomException customException = new CustomException(
				new Date(),"From MethodArgumentNotValid Exception in <<Gloabl Exception Handler>>",ex.getMessage());
		
		return new ResponseEntity<>(customException, HttpStatus.BAD_REQUEST);
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
			HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

		CustomException customException = new CustomException(
				new Date(),"From HttpRequestMethodNotSupported Exception in <<Gloabl Exception Handler>>",ex.getMessage());
		
		return new ResponseEntity<>(customException, HttpStatus.METHOD_NOT_ALLOWED);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex, WebRequest request){
		
		CustomException customException = new CustomException(
				new Date(),"From UserNotFoundException Exception in <<Custom Exception Handler>>",ex.getMessage());
		
		return new ResponseEntity<>(customException, HttpStatus.BAD_GATEWAY);
	}
}
