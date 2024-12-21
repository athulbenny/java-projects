package com.credai.sampleapi.service;

import com.credai.sampleapi.entity.Customer;
import com.credai.sampleapi.exception.NotValidException;
import com.credai.sampleapi.model.CustIdDto;
import com.credai.sampleapi.model.CustomerRequestDto;
import com.credai.sampleapi.model.CustomerResponseDto;

public interface CustomerService {

	public CustomerResponseDto createCustomer(CustomerRequestDto customerInput) throws NotValidException;
	
	public CustomerResponseDto getCustomer(CustIdDto custIdDto);
	
	public void deleteCustomer(long custId);
}
