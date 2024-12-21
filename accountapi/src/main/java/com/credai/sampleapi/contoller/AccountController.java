package com.credai.sampleapi.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.credai.sampleapi.entity.Account;
import com.credai.sampleapi.exception.NotValidException;
import com.credai.sampleapi.model.AccountDto;
import com.credai.sampleapi.model.AccountStatusDto;
import com.credai.sampleapi.service.AccountService;

@RestController
@RequestMapping("/api")
public class AccountController {

	@Autowired
	private AccountService accountService;
	
	@PostMapping("/account")
	public ResponseEntity<?> createAccount(@RequestBody AccountDto accountInput) throws NotValidException{
		if(!(accountInput.getStatus().equalsIgnoreCase("open") || accountInput.getStatus().equalsIgnoreCase("close"))){
			throw new NotValidException("Invalid status");
		}
		AccountDto account =null;
		try {
		account = accountService.createAccount(accountInput);
		}catch(Exception e) {
			return new ResponseEntity<>(new NotValidException("Error in creating new account"),HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(account,HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/updateAccountStatus") //{"custId" : 123,"status: "close"}
	public ResponseEntity<?> updateAccountStatus(@RequestBody AccountStatusDto accountStatusInput) throws NotValidException {
		if(!(accountStatusInput.getStatus().equalsIgnoreCase("open") || accountStatusInput.getStatus().equalsIgnoreCase("close"))){
			throw new NotValidException("Invalid status");
		}
		AccountDto account =null;
		try {
		account = accountService.updateAccount(accountStatusInput);
		}catch(Exception e) {
			return new ResponseEntity<>(new NotValidException("Error while updating a new account"),HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(account,HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/deleteAccount/{accountId}")
	public ResponseEntity<Object> deleteCustomer(@PathVariable long custId){
		try {
			accountService.DeleteAccount(custId);
		}catch(Exception e) {
			return new ResponseEntity<>(new NotValidException("Error while deleting an account"),HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("Customer deleted succesfully",HttpStatus.ACCEPTED);
	}

}
