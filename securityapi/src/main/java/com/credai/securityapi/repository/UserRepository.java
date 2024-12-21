package com.credai.securityapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.credai.securityapi.entity.SUser;

public interface UserRepository extends JpaRepository<SUser, Long>{
	Optional<SUser> findByUsername(String username);
}
