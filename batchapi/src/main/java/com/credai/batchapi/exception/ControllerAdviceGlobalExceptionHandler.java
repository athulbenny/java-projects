package com.credai.batchapi.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class ControllerAdviceGlobalExceptionHandler extends ResponseEntityExceptionHandler{

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		
		CustomException customException = new CustomException(
				new Date(),HttpStatus.BAD_REQUEST.value(),"From MethodArgumentNotValid Exception in <<Gloabl Exception Handler>>",ex.getMessage());
		
		return new ResponseEntity<>(customException, HttpStatus.BAD_REQUEST);
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
			HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

		CustomException customException = new CustomException(
				new Date(),HttpStatus.METHOD_NOT_ALLOWED.value(),"From HttpRequestMethodNotSupported Exception in <<Gloabl Exception Handler>>",ex.getMessage());
		
		return new ResponseEntity<>(customException, HttpStatus.METHOD_NOT_ALLOWED);
	}
	
//	@ExceptionHandler(CsvParseException.class)
//	protected ResponseEntity<Object> handleCsvParseException(
//			CsvParseException ex, WebRequest request) {
//
//		CustomException customException = new CustomException(
//				new Date(),HttpStatus.METHOD_NOT_ALLOWED.value(),"From CsvParseException in <<Gloabl Exception Handler>>", ex.getMessage());
//		
//		return new ResponseEntity<>(customException, HttpStatus.METHOD_NOT_ALLOWED);
//	}
	
	// Handle specific exception for 4xx errors
    @ExceptionHandler(ClientErrorException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleClientError(ClientErrorException ex) {
    	CustomException customException = new CustomException(
				new Date(),HttpStatus.BAD_REQUEST.value(),"From ClientErrorException Exception in <<Gloabl Exception Handler>>",ex.getMessage());
		
        return new ResponseEntity<>(customException, HttpStatus.BAD_REQUEST);
    }

    // Handle specific exception for 5xx errors
    @ExceptionHandler(ServerErrorException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> handleServerError(ServerErrorException ex) {
    	CustomException customException = new CustomException(
				new Date(),HttpStatus.INTERNAL_SERVER_ERROR.value(),"From ClientErrorException Exception in <<Gloabl Exception Handler>>",ex.getMessage());
		        return new ResponseEntity<>(customException, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Handle general exceptions for unknown errors (can cover 500 errors too)
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> handleGeneralException(Exception ex) {
    	CustomException customException = new CustomException(
				new Date(),HttpStatus.INTERNAL_SERVER_ERROR.value(),"From ClientErrorException Exception in <<Gloabl Exception Handler>>",ex.getMessage());
		        return new ResponseEntity<>(customException, HttpStatus.INTERNAL_SERVER_ERROR);
    }
	
}
