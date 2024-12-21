package com.credai.sampleapi.service;

import com.credai.sampleapi.entity.Account;
import com.credai.sampleapi.model.AccountDto;
import com.credai.sampleapi.model.AccountStatusDto;

public interface AccountService {
	public AccountDto createAccount(AccountDto accountInput);
	
	public AccountDto updateAccount(AccountStatusDto accStatusInput);
	
	public void DeleteAccount(long accountId);
}
