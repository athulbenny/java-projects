package com.credai.batchapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class BatchapiApplication	{

	public static void main(String[] args) {
		SpringApplication.run(BatchapiApplication.class, args);
	}


}

/**
 * <dependency>
		    <groupId>org.springframework.batch</groupId>
		    <artifactId>spring-batch-core</artifactId>
		</dependency>
 */
