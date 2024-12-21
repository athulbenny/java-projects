package com.credai.multiapi.service;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.credai.multiapi.exception.ServerErrorException;
import com.credai.multiapi.model.UserDtoFromCsv;
import com.credai.multiapi.utility.CsvParsing;
import com.credai.multiapi.utility.ExternalApiCalling;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;


@Service
public class UserServiceImpl implements UserService{

	Logger logger = LoggerFactory.getLogger(UserService.class);
	
	public UserServiceImpl(ExternalApiCalling externalApiCalling) {
		this.externalApiCalling = externalApiCalling;
	}


	CsvParsing csvParsing = new CsvParsing();
	private final ExternalApiCalling externalApiCalling;
	
	
	@Async("taskExecutor")
	@Override
	public CompletableFuture<Object> addUserToDbWeb(MultipartFile file) throws ServerErrorException {
		logger.info("here {}", Thread.currentThread().getName());
		List<UserDtoFromCsv> userDtoListFromCsv = new ArrayList<>();
		try {
			userDtoListFromCsv = csvParsing.getUserFromCsvUsingOpenCsv(file);
		} catch (IOException e) {
			logger.info("error is catched while processing: {}",e.getMessage());
		}
		
		externalApiCalling.getUserUsingWebClientCompletableFuture(userDtoListFromCsv);
		return CompletableFuture.completedFuture("");
	}
	
	
	@Override
	public CompletableFuture<Object> addUserToDbRest() {
		
		List<UserDtoFromCsv> userDtoListFromCsv = new ArrayList<>();
		try {
			userDtoListFromCsv = csvParsing.getUserFromCsvForRunner();
		} catch (IOException e) {
			logger.info("error is catched while processing: {}",e.getMessage());
		}
		
		externalApiCalling.getUserUsingRestTemplateCompletableFuture(userDtoListFromCsv);	
		return CompletableFuture.completedFuture("");
	}
	
	
	
	@Override
	public CompletableFuture<Object> addUserToDbUsingRunner() {
		
		List<UserDtoFromCsv> userDtoListFromCsv = new ArrayList<>();
		try {
			userDtoListFromCsv = csvParsing.getUserFromCsvForRunner();
		} catch (IOException e) {
			logger.info("error is catched while processing: {}",e.getMessage());
		}
		
		externalApiCalling.getUserUsingRestTemplateCompletableFuture(userDtoListFromCsv);	
		return CompletableFuture.completedFuture("");
	}
	
}