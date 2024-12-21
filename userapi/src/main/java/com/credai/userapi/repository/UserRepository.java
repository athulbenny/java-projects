package com.credai.userapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.credai.userapi.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

	User findByUserName(String name);

}
