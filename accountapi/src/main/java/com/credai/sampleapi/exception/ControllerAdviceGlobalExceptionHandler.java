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
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
			HttpRequestMethodNotSupportedException ex, 
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {

		CustomExceptionDetails customExceptionDetails = new CustomExceptionDetails(
				new Date(),
				"HttpRequestMethodNotSupported Exception is catched", ex.getMessage());
		
		return new ResponseEntity<Object>(customExceptionDetails, HttpStatus.ACCEPTED);
	}
	

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, 
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {

		CustomExceptionDetails customErrorDetails = new CustomExceptionDetails(
				new Date(), 
				"From MethodArgumentNotValid Exception in <<Gloabl Exception Handler>>", 
				ex.getMessage()
				);
		return new ResponseEntity<Object>(customErrorDetails, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NotValidException.class)
	public ResponseEntity<Object> handleNotValidExceptions(
			NotValidException ex, WebRequest request){
		
		CustomExceptionDetails customErrorDetails = new CustomExceptionDetails(
				new Date(), 
				"From NotValidException Exception in <<Gloabl Exception Handler>>", 
				ex.getMessage()
				);
		return new ResponseEntity<Object>(customErrorDetails, HttpStatus.METHOD_NOT_ALLOWED);
		}
	
}
