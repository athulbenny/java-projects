package com.credai.userapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.credai.userapi.entity.User;
import com.credai.userapi.exceptions.EmailIdNotValid;
import com.credai.userapi.exceptions.PasswordLengthNotValid;
import com.credai.userapi.exceptions.UserIdNotExist;
import com.credai.userapi.exceptions.UserNameAlreadyExist;
import com.credai.userapi.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User createUserV1(User userInput) {
		User user = userRepository.save(userInput);
		return user;
	}

	@Override
	public List<User> getAllUsersV1() {
		List<User> userList = userRepository.findAll();
		return userList;
	}

	@Override
	public User getUserV1(long userId) {
		User user = userRepository.findById(userId).get();
		return user;
	}

	@Override
	public User updateUserV1(long userId, User userInput) {
		userInput.setUserId(userId);
		User user = userRepository.save(userInput);
		return user;
	}

	@Override
	public boolean deleteUserV1(long userId) {
		if(userRepository.findById(userId).isPresent()) {
			userRepository.deleteById(userId);
			return true;
		}
		return false;
	}

	@Override
	public User createUserV2(User userInput) throws UserNameAlreadyExist, PasswordLengthNotValid, EmailIdNotValid {
		if(userRepository.findByUserName(userInput.getUserName())!=null) {
			throw new UserNameAlreadyExist("User name already exist");
		}
		if(userInput.getPassword().length()<10) {
			throw new PasswordLengthNotValid("password length is not valid");
		}
		if(!(userInput.getEmail().contains("@") && userInput.getEmail().contains("."))) {
			throw new EmailIdNotValid("email is not valid");
		}
		User user =userRepository.save(userInput);
		return user;
	}

	@Override
	public User getUserV2(long userId) throws UserIdNotExist {
		if(userRepository.findById(userId).isEmpty()) {
			throw new UserIdNotExist("user id not exist");
		}
		User user = userRepository.findById(userId).get();
		return user;
	}
	
	

	@Override
	public User updateUserV2(long userId, User userInput)
			throws UserIdNotExist, UserNameAlreadyExist, PasswordLengthNotValid, EmailIdNotValid {
		userInput.setUserId(userId);
		if(userRepository.findById(userId).isEmpty()) {
			throw new UserIdNotExist("user id not exist");
		}
		if(!userInput.getUserName().toString().equals(userRepository.findById(userId).get().getUserName().toString())) {
			throw new UserNameAlreadyExist("You cannot update the username");
		}
		if(userInput.getPassword().length()<10) {
			throw new PasswordLengthNotValid("password length is not valid");
		}
		if(!(userInput.getEmail().contains("@") && userInput.getEmail().contains("."))) {
			throw new EmailIdNotValid("email is not valid");
		}
		
		User user =userRepository.save(userInput);
		return user;
	}

	@Override
	public boolean deleteUserV2(long userId) throws UserIdNotExist {
		if(userRepository.findById(userId).isEmpty()) {
			throw new UserIdNotExist("user id not exist");
		}
		userRepository.deleteById(userId);
		return true;
	}

	
}
