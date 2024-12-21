package com.credai.loggerDemo.service;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import com.credai.loggerDemo.model.UserDtoFromCsv;
import entity.NewUser;



public interface UserService {
	public List<UserDtoFromCsv> addUserToDb(MultipartFile file);
	
	public List<NewUser> getUserList();
}
