package com.credai.sampleapi.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class RestControllerAdviceGlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> handleMethodArgumentsNotValid(MethodArgumentNotValidException ex){
		
		CustomException customException = new CustomException(
				new Date(),"From MethodArgumentNotValid Exception in <<Gloabl Exception Handler>>",ex.getMessage());

		return new ResponseEntity<>(customException, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex){
		
		CustomException customException = new CustomException(
				new Date(),"From MethodArgumentNotValid Exception in <<Gloabl Exception Handler>>",ex.getMessage());

		return new ResponseEntity<>(customException, HttpStatus.BAD_REQUEST);
	}

}
