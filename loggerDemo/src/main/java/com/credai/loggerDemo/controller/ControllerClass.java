package com.credai.loggerDemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.slf4j.MDC;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.credai.loggerDemo.model.UserDtoFromCsv;
import com.credai.loggerDemo.service.UserService;

@RequestMapping("/api")
@RestController
public class ControllerClass{


	
	@Autowired
	private UserService userService;
	
	@PostMapping(value="/users", consumes= {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = "application/json")
	public ResponseEntity<Object> getUserFromCsv(@RequestParam(value="files") MultipartFile file) {
		
		List<UserDtoFromCsv> userList = userService.addUserToDb(file);
		return new ResponseEntity<>(userList,HttpStatus.ACCEPTED);
	}
	
	
	
	
}

//private static final Logger logger = LoggerFactory.getLogger(ControllerClass.class);
//
//@GetMapping("/api")	
//public String tryLogger() {
//	logger.info("Get task is performing...");	
//	if(logger.isDebugEnabled()) {
//		logger.debug("Debug message with paramters: {}", 1+2);
//		//output in log file: Debug message with paramters: 3
//		//also called paramterized logger
//	}
//	try {
//		int a= 10/1;
//		//MDC(Mapped Diagnostic Context) Loggers
//		MDC.put("userId", "12345"); //logging as key-value pair, but not visible in log file
//		logger.info(MDC.get("userId")); // value can be accessed using get method
//		MDC.clear(); // clear all MDC data
//		return "hi";
//	}
//	catch(Exception e) {
//		logger.error("error is occured "+e.getMessage());
//		return "Error";
//	}	
//}
