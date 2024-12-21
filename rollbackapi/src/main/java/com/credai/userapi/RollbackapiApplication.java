package com.credai.userapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class RollbackapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RollbackapiApplication.class, args);
	}

}
