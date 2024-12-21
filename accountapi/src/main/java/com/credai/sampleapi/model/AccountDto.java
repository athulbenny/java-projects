package com.credai.sampleapi.model;

import lombok.Data;

@Data
public class AccountDto {

	private long accountId;
	
	private long accountNumber;
	
	private double accountBalance;
	
	private String accountNominee;
	
	private String status;
}