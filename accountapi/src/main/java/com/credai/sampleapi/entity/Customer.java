package com.credai.sampleapi.entity;

import java.time.LocalDate;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Table(name ="CUSTOMER")
@Data
@Entity
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long custId;
	
	@Column(name = "fname")
	private String fname;
	
	@Column(name = "mname")
	private String mname;
	
	@Column(name = "lname")
	private String lname;
	
	@Column(name = "dob")
	private LocalDate dob;
	
	@OneToOne
	@JoinColumn(name = "account_id")
	private Account account;
	
	
}
