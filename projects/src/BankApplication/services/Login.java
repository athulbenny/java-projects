package BankApplication.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import BankApplication.constants.*;
import BankApplication.model.Admin;
import BankApplication.model.Customer;
import BankApplication.repository.CsvReader;

public class Login {
	
	boolean loginFlag=false;
	
	public boolean login() {
			Scanner sc = new Scanner(System.in);
			System.out.println("Login with your customerId and Password");
			
			System.out.println("Enter CustomerId");
			String username = sc.next();
			System.out.println("Enter password");
			String password = sc.next();
			
			CsvReader csvReader = new CsvReader();
			boolean loginFlag = csvReader.readFromCsvForLogin(username, password);
		return loginFlag;
	}
	
	public boolean route(String[] values,String username, String password) {
		
		String mode = "customer";
		
		
		if(username.equals(values[0]) && password.hashCode()  == Integer.parseInt(values[1])) {
			loginFlag=true;
			
			if(username.equalsIgnoreCase("AdmIn")) {
				mode = "admin";
				Constants.mode = "admin";
				Admin admin = new Admin(values[0],values[1], Double.parseDouble(values[2]));
			}
		}
		
		if(loginFlag && Constants.mode == "admin") {
			Customer customer = new Customer(values[0],values[1], Double.parseDouble(values[2]));
			Constants.users.add(customer);
		}
		else if(loginFlag && username.equals(values[0])) {
			Customer customer = new Customer(values[0],values[1], Double.parseDouble(values[2]));
			if(!Constants.users.isEmpty())
			Constants.users.set(0,customer);
			else Constants.users.add(customer);
		}
			return loginFlag;
	}

}
