package com.credai.sampleapi.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.credai.sampleapi.entity.Customer;
import com.credai.sampleapi.exception.NotValidException;
import com.credai.sampleapi.model.CustIdDto;
import com.credai.sampleapi.model.CustomerRequestDto;
import com.credai.sampleapi.model.CustomerResponseDto;
import com.credai.sampleapi.service.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@PostMapping("/createCustomer")
	public ResponseEntity<Object> createCustomer(
			@RequestBody CustomerRequestDto customerInput) {
		CustomerResponseDto customer =null;
		NotValidException exp = new NotValidException("Error while creating a new customer");
		try {
		customer = customerService.createCustomer(customerInput);
		}catch(Exception e) {
			return new ResponseEntity<>(exp,HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(customer,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/getCustDetails")
	public ResponseEntity<Object> getCustomer(@RequestBody CustIdDto custIdDto) {
		CustomerResponseDto customer =null;
		NotValidException exp = new NotValidException("Error while getting customer");
		try {
		customer = customerService.getCustomer(custIdDto);
		}catch(Exception e) {
			return new ResponseEntity<>(exp,HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(customer,HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/deleteCustomer/{custId}")
	public ResponseEntity<Object> deleteCustomer(@PathVariable long custId){
		try {
			customerService.deleteCustomer(custId);
		}catch(Exception e) {
			return new ResponseEntity<>("!Customer deleted succesfully",
					HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("Customer deleted succesfully",HttpStatus.ACCEPTED);
	}
	
}
