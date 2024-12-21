package com.credai.sampleapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.credai.sampleapi.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{

}
