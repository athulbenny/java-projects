package com.credai.driver_programs;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.credai.beans.Employee;
import com.credai.beans.Hello;
import com.credai.javaconfig.JavaSpringConfiguration;

public class CreateBeansFromJavaConfiguration {

	public static void main(String[] args) {

		ApplicationContext context = new AnnotationConfigApplicationContext(JavaSpringConfiguration.class);
		String s = context.getBean("wish", String.class);
		System.out.println(s);
		
		Hello h = context.getBean("h",Hello.class);
		System.out.println(h.sayHello());
		
//		Employee emp =context.getBean("emp1",Employee.class);
//		System.out.println(emp.toString());
//		
//		Employee emp1 =context.getBean("malli",Employee.class);
//		System.out.println(emp1.toString());
		
//		Employee emp1 =context.getBean(Employee.class); //leads to error: NoUniqueBeanDefinitionException: if no @primary used
//		System.out.println(emp1.toString());
		
//		Employee emp4 =context.getBean(Employee.class); dto: data transfer object, entity<==> orm, bean comp comb, dao
//		System.out.println(emp4.toString());  // by using @primary
		
	}

}


