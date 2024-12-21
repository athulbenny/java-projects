package com.credai.userapi.service;

import java.util.List;
import com.credai.userapi.entity.Person;
import com.credai.userapi.exception.AgeNotValid;


public interface PersonService {

	public Person createPerson(Person inputPerson) throws AgeNotValid;
	
	public List<Person> getAllPerson();
}
