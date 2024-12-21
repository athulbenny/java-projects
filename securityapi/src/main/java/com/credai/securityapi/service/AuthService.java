package com.credai.securityapi.service;

import com.credai.securityapi.model.LoginDto;

public interface AuthService {
	String login(LoginDto loginDto);
}
