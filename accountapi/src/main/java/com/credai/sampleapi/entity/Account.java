package com.credai.sampleapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Table(name = "ACCOUNT")
@Data
@Entity
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long accountId;
	
	@Column(name ="account_number")
	private long accountNumber;
	
	@Column(name = "account_balance")
	private double accountBalance;
	
	@Column(name="account_nominee")
	private String accountNominee;
	
	@Column(name = "status")
	private String status;
}
