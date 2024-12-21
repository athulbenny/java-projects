package com.credai.multiapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.credai.multiapi.entity.NewUser;

public interface UserRepository extends JpaRepository<NewUser, Long>{

}
