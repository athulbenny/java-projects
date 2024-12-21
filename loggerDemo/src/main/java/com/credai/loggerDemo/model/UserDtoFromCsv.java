package com.credai.loggerDemo.model;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class UserDtoFromCsv {
	private String username;
	private long zipcode;
}
