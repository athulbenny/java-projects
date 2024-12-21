package com.credai.javaconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.credai.beans.Employee;
import com.credai.beans.Hello;

@Configuration
public class JavaSpringConfiguration {

    @Bean
    String wish() {
		return "hi, this is from java config wish";
	}
    
    @Bean
    Hello h() {
    	return new Hello();
    }
    
    @Bean
    Employee emp() {
    	return new Employee(101,"ali",25,25000,"blguru");			
    }
    
    @Bean(name="malli")
    Employee emp1() {
    	return new Employee(102,"malli",26,26000,"mysuru");			
    }
    
    @Primary
    @Bean 
    Employee emp2() {
    	return new Employee(103,"sandi",27,27000,"chennai");			
    }
    
    
}
