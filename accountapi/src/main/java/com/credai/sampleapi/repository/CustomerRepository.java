package com.credai.sampleapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.credai.sampleapi.entity.Account;
import com.credai.sampleapi.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{
	
	Customer findByAccount(Account account );
}
