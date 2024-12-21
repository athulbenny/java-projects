package com.credai.securityapi.model;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Component
public class UserDto {


		private Long userId;
		
		private String email;
		
		private String username;
		
		private String password;
		
	

}
