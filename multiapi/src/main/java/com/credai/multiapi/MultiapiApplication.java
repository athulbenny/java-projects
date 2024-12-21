package com.credai.multiapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import com.credai.multiapi.controller.UserController;
import com.credai.multiapi.service.UserService;

@SpringBootApplication
@EnableAsync
public class MultiapiApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(MultiapiApplication.class, args);
	}

	@Autowired
	private UserService userService;
	
	@Override
	public void run(String... args) throws Exception {
//		userService.addUserToDbUsingRunner();
	}

}
