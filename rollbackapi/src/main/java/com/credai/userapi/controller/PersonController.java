package com.credai.userapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.credai.userapi.entity.Person;
import com.credai.userapi.exception.AgeNotValid;
import com.credai.userapi.service.PersonService;

@RequestMapping("/person")
@RestController
public class PersonController extends ResponseEntityExceptionHandler{
	
	@Autowired
	private PersonService personService;
	
	@PostMapping()
	public ResponseEntity<?> createPerson(@RequestBody Person inputPerson){
		Person person = null;
		
			try {
				person = personService.createPerson(inputPerson);
			} catch (AgeNotValid e) {
				return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
			}
		
			return new ResponseEntity<>(person,HttpStatus.ACCEPTED);
	}
	
	@GetMapping()
	public List<Person> getAllPerson(){
		
		List<Person> personList = personService.getAllPerson();
		
		return personList;
	}
}
