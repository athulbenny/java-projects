package com.credai.multiapi.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ZipCodeValidator implements ConstraintValidator<ValidZipCode, String>{

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		return value.length()==5 || value.length() ==9;
	}

}
