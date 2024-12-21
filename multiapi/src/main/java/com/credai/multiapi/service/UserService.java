package com.credai.multiapi.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.web.multipart.MultipartFile;

import com.credai.multiapi.entity.NewUser;
import com.credai.multiapi.exception.ServerErrorException;
import com.credai.multiapi.model.UserDtoFromCsv;



public interface UserService {
	public CompletableFuture<Object> addUserToDbWeb(MultipartFile file) throws ServerErrorException;

	public CompletableFuture<Object> addUserToDbRest() throws ServerErrorException;

	public CompletableFuture<Object> addUserToDbUsingRunner();

//	public String addUserToDb1(MultipartFile file);
//	
//	public String addUserToDb2(MultipartFile file);

}
