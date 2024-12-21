package com.credai.loggerDemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import entity.NewUser;

public interface UserRepository extends JpaRepository<NewUser, Long>{

}
