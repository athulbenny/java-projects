package com.credai.multiapi.model;

import org.springframework.stereotype.Component;

import com.credai.multiapi.validation.ValidZipCode;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class UserDtoFromCsv {


	@NotBlank(message="first_name cannot be null")
	private String firstName;
	
	@NotBlank(message="last_name cannot be null")
	private String lastName;
	
	@NotBlank(message="zipcode cannot be null")
	@ValidZipCode(message = "zipcode must be either 5 digits or 9 digits")
	private String zipcode;
	
	@Pattern(regexp = "^\\d{3}-\\d{3}-\\d{4}$",message = "phone number 1 is not in valid format")
	private String phone1;

	@Pattern(regexp = "^\\d{3}-\\d{3}-\\d{4}$",message = "phone number 2 is not in valid format")
	private String phone2;
	
	@Email(message="invalid email")
	@NotBlank(message="email cannot be null")
	private String email;
	
	private String web;
}
