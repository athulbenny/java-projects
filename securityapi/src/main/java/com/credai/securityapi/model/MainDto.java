package com.credai.securityapi.model;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class MainDto {
	
	private String temp;
	private String temp_min;
	private String temp_max;
	private String humidity;
	private String pressure;
}
