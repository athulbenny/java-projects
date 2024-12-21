package com.credai.sampleapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.credai.sampleapi.entity.Account;
import com.credai.sampleapi.entity.Customer;
import com.credai.sampleapi.model.AccountDto;
import com.credai.sampleapi.model.AccountStatusDto;
import com.credai.sampleapi.repository.AccountRepository;
import com.credai.sampleapi.repository.CustomerRepository;

@Service
public class AccountServiceImpl implements AccountService{

	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public AccountDto createAccount(AccountDto accountInput) {
		AccountDto accountDto =accountInput;
		Account account = new Account();
		account.setAccountNumber(accountDto.getAccountNumber());
		account.setAccountBalance(accountDto.getAccountBalance());
		account.setAccountNominee(accountDto.getAccountNominee());
		account.setStatus(accountDto.getStatus());
		Account account1 = accountRepository.save(account);
		accountDto.setAccountId(account1.getAccountId());
		return accountDto;
	}

	@Override
	public void DeleteAccount(long accountId) {
		if(accountRepository.findById(accountId).isPresent()) {
			Account account = accountRepository.findById(accountId).get();
			accountRepository.deleteById(accountId);
			
		}
	}

	@Override
	public AccountDto updateAccount(AccountStatusDto accStatusInput) {
		Account account=null;
		Customer customer =null;
		AccountDto accountDto = new AccountDto();
		if(customerRepository.findById(accStatusInput.getCustId()).isPresent()) {
			customer = customerRepository.findById(accStatusInput.getCustId()).get();
			account = customer.getAccount();
			account.setStatus(accStatusInput.getStatus().toLowerCase());
			
		}
			account = accountRepository.save(account);
			accountDto.setAccountId(account.getAccountId());
			accountDto.setAccountNumber(account.getAccountNumber());
			accountDto.setAccountBalance(account.getAccountBalance());
			accountDto.setAccountNominee(account.getAccountNominee());
			accountDto.setStatus(account.getStatus());
		return accountDto;
	}

}
