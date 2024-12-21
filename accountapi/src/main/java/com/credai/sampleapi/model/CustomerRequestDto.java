package com.credai.sampleapi.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerRequestDto {
	
	private long custId;
	
	private String fname;
	
	private String mname;
	
	private String lname;
	
	private LocalDate dob;
	
	private long accountId;
	
	
}