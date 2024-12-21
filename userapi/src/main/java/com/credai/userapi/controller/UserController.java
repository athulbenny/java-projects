package com.credai.userapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.credai.userapi.entity.User;
import com.credai.userapi.exceptions.EmailIdNotValid;
import com.credai.userapi.exceptions.PasswordLengthNotValid;
import com.credai.userapi.exceptions.UserIdNotExist;
import com.credai.userapi.exceptions.UserNameAlreadyExist;
import com.credai.userapi.service.UserService;
import jakarta.validation.Valid;

@RestController
public class UserController extends ResponseEntityExceptionHandler{

	@Autowired
	private UserService userService;
	

	@PostMapping("/api/v1/user")
	public User createUserV1(@Valid @RequestBody User userInput) {
		User user = userService.createUserV1(userInput);
		return user;
	}
	
	@PostMapping("/api/v2/user")
	public ResponseEntity<?> createUserV2(@Valid @RequestBody User userInput) {
		User user = null;
		try {
			user = userService.createUserV2(userInput);
		} catch (UserNameAlreadyExist e) {
			return new ResponseEntity<>("validation failed: "+e.getMessage(),HttpStatus.BAD_REQUEST);
			
		} catch (PasswordLengthNotValid e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		} catch (EmailIdNotValid e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
		return new ResponseEntity<>(user, HttpStatus.CREATED);
	}
	
	
	@GetMapping({"/api/v1/user","/api/v2/user"})
	public List<User> getAllUsersV1(){
		List<User> userList = userService.getAllUsersV1();
		return userList;
	}
	
	@GetMapping("/api/v1/user/{userId}")
	public User getUserV1(@PathVariable long userId){
		User user = userService.getUserV1(userId);
		return user;
	}
	
	@GetMapping("/api/v2/user/{userId}")
	public ResponseEntity<?> getUserV2(@PathVariable long userId){
		User user = null;
		try {
			user = userService.getUserV2(userId);
		} catch (UserIdNotExist e) {
			return new ResponseEntity<>("Error: "+e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(user,HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/api/v1/user/{userId}")
	public User updateUserV1(@PathVariable long userId, @RequestBody User userInput) {
		User user = userService.updateUserV1(userId, userInput);
		return user;
	}
	
	@PutMapping("/api/v2/user/{userId}")
	public ResponseEntity<?> updateUserV2(@PathVariable long userId, @RequestBody User userInput){
		User user =null;
		
		try {
			user = userService.updateUserV2(userId, userInput);
		} catch (UserIdNotExist e) {
			return new ResponseEntity<>("error: "+e.getMessage(),HttpStatus.BAD_REQUEST);
			
		}catch (UserNameAlreadyExist e) {
			return new ResponseEntity<>("validation failed: "+e.getMessage(),HttpStatus.BAD_REQUEST);
		} catch (PasswordLengthNotValid e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		} catch (EmailIdNotValid e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
		return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/api/v1/user/{userId}")
	public boolean deleteUserV1(@PathVariable long userId) {
		boolean b = userService.deleteUserV1(userId);
		return b;
	}
	
	public ResponseEntity<?> deleteUserV2(@PathVariable long userId){
		boolean b= false;
		try {
			b = userService.deleteUserV2(userId);
		} catch (UserIdNotExist e) {
			return new ResponseEntity<>("error:" +e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("suceess",HttpStatus.ACCEPTED);
	}
	
}
