package com.credai.sampleapi.model;

import java.time.LocalDate;

import com.credai.sampleapi.entity.Account;

import lombok.Data;

@Data
public class CustomerResponseDto {
	
	private long custId;
	
	private String fname;
	
	private String mname;
	
	private String lname;
	
	private LocalDate dob;
	
	private Account account;
	
	
}