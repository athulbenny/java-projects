package basic;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

 class Employee {

	private int empno;
	private String name;
	private float age;
	private double salary;
	private boolean isMarried;
	private char gender;
	private static String companyName = "Cred.ai";
	private static String[] hobbies;
	
	Employee(int empno,String name,float age, double salary, boolean isMarried, char gender, String[] hobbies){
		this.empno = empno;
		this.name = name;
		this.age = age;
		this.salary = salary;
		this.isMarried = isMarried;
		this.gender = gender;
		Employee.hobbies = hobbies;
	}
	
	public void printEmployeeInformation() {
		System.out.println("employee number: " + this.empno);
		System.out.println("Name: " + this.name);
		System.out.println("age: " + this.age);
		System.out.println("salary : Rs" + this.salary);
		System.out.println("isMarried: " + this.isMarried);
		System.out.println("gender: " + this.gender);
		System.out.println("Company: " + companyName);
	}
	
	public static void employeeHobbies() {
		System.out.println("Employee hobbies are: ");
		for(int i=0;i<3;i++) {
			System.out.println(hobbies[i]);
		}
	}
	
}


public class CreateEmployee{
	public static void main(String args[]) {
		
		String hobbies[] = new String[3];
		
		try (Scanner sc = new Scanner(System.in)) {
			boolean isNewEmplyeeNeedToBeCreated = true;
			List<Employee> empList = new ArrayList<Employee>();
			
			while(isNewEmplyeeNeedToBeCreated) {
				
				System.out.println("Enter Employee number");
				int empno = sc.nextInt();
				
				System.out.println("Enter Employee name");
				sc.nextLine();
				String name = sc.nextLine();
				
				System.out.println("Enter Employee age");
				float age = sc.nextFloat();
				
				System.out.println("Enter Employee salary");
				double salary = sc.nextDouble();
				
				System.out.println("is the  Employee is married (true/false)");
				boolean isMarried = sc.nextBoolean();
				
				System.out.println("Enter Employee gender(F/M)");
				char gender = sc.next().charAt(0);
				
				System.out.println("Enter atleast 3 hobbies");
				sc.nextLine();
				for(int i=0;i<3;i++) {
				hobbies[i] = sc.nextLine();
				}
				
				Employee emp = new Employee(empno, name, age, salary, isMarried, gender, hobbies);
				empList.add(emp);
				
				System.out.println("Enter whether New Emplyee Need To Be Created(true/false)");
				isNewEmplyeeNeedToBeCreated = sc.nextBoolean();
			}
			
			for(Employee emp : empList) {
				emp.printEmployeeInformation();
				Employee.employeeHobbies();
				
			}
		}
		
		
	}
}