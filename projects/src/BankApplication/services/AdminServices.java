package BankApplication.services;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import BankApplication.exception.AccountAlreadyExistException;
import BankApplication.exception.NoSuchAccountExistException;
import BankApplication.model.Customer;
import BankApplication.constants.*;
import BankApplication.repository.*;

public class AdminServices {
	
	public AdminServices() {
		doAutoUpdate();
	}

	List<Customer> customers = Constants.users;
	Scanner sca = new Scanner(System.in);
	String path = Constants.filepath;
	CsvReader csvreader = new CsvReader();
	CsvWriter csvwriter = new CsvWriter();
	
	public void adminDashboardOperation() {
		
		boolean tryAgain = true;
		
		while(tryAgain) {
			this.customers = Constants.users;
			System.out.println("Enter	1: Create customer	2: Delete customer	3:Customer details	4: Logout");
			int n =  sca.nextInt();
			
			if(n==1) 
				createUser();
			else if(n==2) 
				deleteUser();
			else if(n==3) {
				System.out.println("Currently available Customers: ");
				System.out.println("Customers\tBalance");
				for(int i=1;i<this.customers.size();i++) {
					System.out.println(this.customers.get(i).getCustomerId() + "\t\t" + this.customers.get(i).getBalance());
				}
			}
			else if(n==4) {
				System.out.println("Successfully Logged out");
				break;
			}
//			
			System.out.println("Do you want to try again? Y/N");
			tryAgain = sca.next().equalsIgnoreCase("Y");
		}
	}
	
	public void createUser() {
		
		System.out.println("Enter the customer id");
		String customerId = sca.next();
		
		try {
			for(Customer customer: customers) {
				if(customer.getCustomerId().equals(customerId)) {
					throw new AccountAlreadyExistException("Account Already Exist");
				}
				if(customerId.equalsIgnoreCase("admin")) {
					throw new AccountAlreadyExistException("Process failed");
				}
			}
			
			System.out.println("Enter the customer password");
			String password = sca.next();
			double balance = 0.0;
			
			csvwriter.updateUserEntryToCsv(new Customer(customerId,password,balance));
			csvreader.readDataFromCsvByAdmin();
			
		}catch(AccountAlreadyExistException e) {
			System.out.println(e);
		}
		
	}
	
	public void deleteUser() {
		
		System.out.println("Enter the customer id");
		String customerId = sca.next();
		
		try {
			boolean flag = false;
		for(Customer customer: customers) {
			if(customer.getCustomerId().equals(customerId)) {
				flag = true;
			}
		}
		if(!flag) {
			throw new NoSuchAccountExistException("No such Account Exist");
		}
		
		csvwriter.rewriteDataToCsvAfterDelete(customerId);
		csvreader.readDataFromCsvByAdmin();
		
		}catch(NoSuchAccountExistException e) {
			System.out.println(e);
		}
		
	}

	//for removing duplicates in the csv file(duplicates that are created by customer(s))
	public void doAutoUpdate() {
		
		List<String> custList = new ArrayList<>();
		
		csvwriter.removeDuplicatesFromCsv();
		
		System.out.println("Current Customer Transactions: ");
		System.out.println("Customers\tBalance");
		for(int i=1;i<customers.size();i++) {
			System.out.println(customers.get(i).getCustomerId() + "\t\t" + customers.get(i).getBalance());
		}
		
		csvreader.readDataFromCsvByAdmin();
 
	}
	
}
