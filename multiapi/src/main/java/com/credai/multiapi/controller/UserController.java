package com.credai.multiapi.controller;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.credai.multiapi.exception.ClientErrorException;
import com.credai.multiapi.exception.ServerErrorException;
import com.credai.multiapi.service.UserService;

@RequestMapping("/api")
@RestController

public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping(value="/user/1", consumes= {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = "application/json")
	public ResponseEntity<Object> transferUserFromCsvAndApiToDb(@RequestParam(value="files") MultipartFile file) throws ClientErrorException {
		
		if("text/csv".equals(file.getContentType()) && !file.isEmpty()) {
			try {
				CompletableFuture<Object> userList = userService.addUserToDbWeb(file);
				return new ResponseEntity<>(userList,HttpStatus.ACCEPTED);
			}
			catch(ServerErrorException ex) {
				throw new ServerErrorException(ex.getMessage());
			}
		}
		
		else throw new ClientErrorException("File format exception ");
	}		

	
	
	@PostMapping("/user/2")
	public ResponseEntity<Object> transferUserFromCsvAndApiToDb() throws ClientErrorException {
		
		try {
			CompletableFuture<Object> userList = userService.addUserToDbRest();
			return new ResponseEntity<>(userList,HttpStatus.ACCEPTED);
		}
		catch(ServerErrorException ex) {
			throw new ServerErrorException(ex.getMessage());
		}
	}
			


	
}