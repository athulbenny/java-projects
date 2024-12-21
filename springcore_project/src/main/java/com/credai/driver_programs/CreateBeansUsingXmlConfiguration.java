package com.credai.driver_programs;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.credai.beans.Employee;
import com.credai.beans.Hello;
import com.credai.beans.Traveller;

public class CreateBeansUsingXmlConfiguration {//driver program: excutable code

	public static void main(String[] args) {
		System.out.println("===============================");

		Hello h = new Hello();// object h is out of spring container
		String msg = h.sayHello();
		System.out.println(msg);
		System.out.println("===============================");
		
		ApplicationContext context =  new ClassPathXmlApplicationContext("spring_configuration.xml");
		//context is the container that created by executing xml configuration file
		// creates hello bean and emp bean and traveller, bike, car, bus beans
		Hello hello = context.getBean("hello", Hello.class);
		
		//hello is managed by spring container
		String msg2 = hello.sayHello();
		System.out.println(msg2);
		System.out.println("================================");

		Employee emp = context.getBean("emp", Employee.class);
		System.out.println(emp);
		System.out.println("================================");
		
		Traveller traveller = context.getBean("traveller", Traveller.class);
		traveller.startTravel();
		System.out.println("================================");


	}

}
