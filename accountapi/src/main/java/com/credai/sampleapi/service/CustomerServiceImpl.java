package com.credai.sampleapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.credai.sampleapi.entity.Account;
import com.credai.sampleapi.entity.Customer;
import com.credai.sampleapi.exception.NotValidException;
import com.credai.sampleapi.model.CustIdDto;
import com.credai.sampleapi.model.CustomerRequestDto;
import com.credai.sampleapi.model.CustomerResponseDto;
import com.credai.sampleapi.repository.AccountRepository;
import com.credai.sampleapi.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	
	

	@Override
	public CustomerResponseDto createCustomer(CustomerRequestDto customerInput) throws NotValidException{
		Customer crd = new Customer() ;
		Account acc=  accountRepository.findById(customerInput.getAccountId()).get();
		Customer customer = customerRepository.findByAccount(acc);
		if(customer !=null) {
			throw new NotValidException("account is already occupied");
		}
		crd.setFname(customerInput.getFname());
		crd.setMname(customerInput.getMname());
		crd.setLname(customerInput.getLname());
		crd.setDob(customerInput.getDob());
		crd.setAccount(acc);
		
		Customer customer1 = customerRepository.save(crd);
		
		CustomerResponseDto cResDto = new CustomerResponseDto();
		cResDto.setCustId(customer1.getCustId());
		cResDto.setFname(customerInput.getFname());
		cResDto.setMname(customerInput.getMname());
		cResDto.setLname(customerInput.getLname());
		cResDto.setDob(customerInput.getDob());
		cResDto.setAccount(acc);
		return cResDto;
	}

	@Override
	public CustomerResponseDto getCustomer(CustIdDto custIdDto) {
		Customer customer = customerRepository.findById(custIdDto.getCustId()).get();
		
		CustomerResponseDto cResDto = new CustomerResponseDto();
		cResDto.setCustId(customer.getCustId());
		cResDto.setFname(customer.getFname());
		cResDto.setMname(customer.getMname());
		cResDto.setLname(customer.getLname());
		cResDto.setDob(customer.getDob());
		cResDto.setAccount(customer.getAccount());
		
		return cResDto;
	}

	@Override
	public void deleteCustomer(long custId) {
		if(customerRepository.findById(custId).isPresent()) {
			Customer customer = customerRepository.findById(custId).get();
			accountRepository.deleteById(customer.getAccount().getAccountId());
			customerRepository.deleteById(custId);
		}
	}

}
