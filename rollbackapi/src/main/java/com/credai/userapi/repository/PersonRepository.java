package com.credai.userapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.credai.userapi.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Long>{

}
