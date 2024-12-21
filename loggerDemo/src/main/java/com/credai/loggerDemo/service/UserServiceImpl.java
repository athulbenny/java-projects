package com.credai.loggerDemo.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.credai.loggerDemo.model.UserDtoFromCsv;
//import com.credai.loggerDemo.repository.UserRepository;
import com.credai.loggerDemo.repository.UserRepository;

import entity.NewUser;



@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public List<UserDtoFromCsv> addUserToDb(MultipartFile file) {
		List<UserDtoFromCsv> userDtoListFromCsv = getUserFromCsv(file);
		
		return userDtoListFromCsv;
	}

	@Override
	public List<NewUser> getUserList() {
		List<NewUser> userList = userRepository.findAll();
		return userList;
	}
	
	public List<UserDtoFromCsv> getUserFromCsv(MultipartFile file) {
		List<UserDtoFromCsv> userCsvList = new ArrayList<UserDtoFromCsv>();
		
		try(BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
			String line;
			while((line=br.readLine())!=null) {
				String[] data = line.split(",");
				UserDtoFromCsv userDtoFromCsv = new UserDtoFromCsv();
				userDtoFromCsv.setUsername(data[0]);
				userDtoFromCsv.setZipcode(Integer.parseInt(data[1]));
				userCsvList.add(userDtoFromCsv);
			}
		}catch(Exception e ) {
			e.printStackTrace();
		}
		
		return userCsvList;
	}

}
