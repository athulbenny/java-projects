package com.credai.loggerDemo.model;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class UserDtoFromApi {
	private String place;
	private String state;
	private String country;
}
