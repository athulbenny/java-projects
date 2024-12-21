package com.credai.sampleapi.exception;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CustomExceptionDetails {
	private Date timestamp;
	private String message;
	private String errorDetails;
}
