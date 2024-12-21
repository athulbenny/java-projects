package com.credai.userapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.credai.userapi.entity.Person;
import com.credai.userapi.exception.AgeNotValid;
import com.credai.userapi.repository.PersonRepository;

@Service
//@Transactional
public class PersonServiceImpl implements PersonService{

	@Autowired
	private PersonRepository personRepository;
	
	@Override
	@Transactional(rollbackFor = {AgeNotValid.class}, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED)
	public Person createPerson(Person inputPerson) throws AgeNotValid{
		
		Person person = personRepository.save(inputPerson);
		
		if(inputPerson.getName()==null || inputPerson.getName().length()==0) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		
		if(inputPerson.getAge()<18) {
			throw new AgeNotValid("age not supported");
		}
		
		return person;
	}

	@Override
	public List<Person> getAllPerson() {
		
		List<Person> personList = personRepository.findAll();
		
		return personList;
	}

}
