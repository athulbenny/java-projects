package com.example.myservlets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MywebProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(MywebProjectApplication.class, args);
	}

}


class HelloServlets extends HttpServlets{
	
}