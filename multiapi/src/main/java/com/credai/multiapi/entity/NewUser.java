package com.credai.multiapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "USERTABLE")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class NewUser {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userId;
	
	@Column(name="first_name")
	private String firstName;

	@Column(name="last_name")
	private String lastName;
	
	@Column(name="phone1")
	private String phone1;
	
	@Column(name="phone2")
	private String phone2;
	
	@Column(name="email")
	private String email;
	
	@Column(name="web")
	private String web;
	
	@Column(name="place")
	private String placeName;
	
	@Column(name="state")
	private String state;
	
	@Column(name="country")
	private String countryCode;
}
