package com.credai.userapi.service;

import java.util.List;

import com.credai.userapi.entity.User;
import com.credai.userapi.exceptions.EmailIdNotValid;
import com.credai.userapi.exceptions.PasswordLengthNotValid;
import com.credai.userapi.exceptions.UserIdNotExist;
import com.credai.userapi.exceptions.UserNameAlreadyExist;

public interface UserService {
	

	public User createUserV1(User user);
	
	public List<User> getAllUsersV1();
	
	public User getUserV1(long userId);
	
	public User updateUserV1(long userId, User user);
	
	public boolean deleteUserV1(long userId) ;
	
//	public User getUser

	public User createUserV2(User user) throws UserNameAlreadyExist, PasswordLengthNotValid, EmailIdNotValid;
		
	public User getUserV2(long userId) throws UserIdNotExist;
	
	public User updateUserV2(long userId, User user) throws UserIdNotExist,UserNameAlreadyExist, PasswordLengthNotValid, EmailIdNotValid;
	
	public boolean deleteUserV2(long userId) throws UserIdNotExist;
}
