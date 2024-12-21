package com.credai.empapi.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class RestControllerAdviceGlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
		
		CustomException customException = new CustomException(
				new Date(),"From MethodArgumentNotValid Exception in <<Gloabl Exception Handler>>",ex.getMessage());

		return new ResponseEntity<>(customException, HttpStatus.BAD_REQUEST);
	}
}
