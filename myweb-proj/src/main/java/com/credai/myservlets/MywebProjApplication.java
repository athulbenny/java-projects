package com.credai.myservlets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class MywebProjApplication {

	public static void main(String[] args) {
		SpringApplication.run(MywebProjApplication.class, args);
	}

}
