package BankApplication.services;

import java.util.Scanner;

import BankApplication.exception.InvalidAmountException;
import BankApplication.constants.*;
import BankApplication.model.*;
import BankApplication.repository.*;

public class CustomerServices {

	public CustomerServices(Customer customer){
		this.customer = customer;
	}
	Customer customer;
	public String path = Constants.filepath;
	
	public void bankProcess() {
		CsvWriter csvwriter = new CsvWriter();
		
		System.out.println("Welcome " + this.customer.getCustomerId());
		try (Scanner sc = new Scanner(System.in)) {
			boolean tryAgain = true;
			boolean logFlag = true;
			
			while(tryAgain && logFlag) {
				System.out.println("Enter 	1: Deposit,	 2: Withdraw,	3: Check balance,	4: Update password,	5: Logout");
				int n = sc.nextInt();
				switch(n) {
					
				case 1:{
						System.out.println("Enter the amount to be deposited");
						String amountInString = sc.next();
						
						try {
							double amount = Double.parseDouble(amountInString);

							if(amount < 0) {
								throw new InvalidAmountException("Invalid amount!");
							}
							this.customer.setBalance(this.customer.getBalance() + amount);
							csvwriter.updateUserEntryToCsv(this.customer);
							System.out.println("amount successfully added and updated");
						}
						catch(InvalidAmountException e) {
							System.out.println(e);
						}catch(NumberFormatException e) {
							System.out.println("Amount must be a number");
						}
						break;
					}	
				case 2:{
						System.out.println("Enter the amount to be withdrawn");
						String amountInString = sc.next();
						
						try {
							double amount = Double.parseDouble(amountInString);
							if(amount> this.customer.getBalance()) {
								throw new InvalidAmountException("Insufficient balance!");
							}
							this.customer.setBalance(this.customer.getBalance() - amount);
							csvwriter.updateUserEntryToCsv(this.customer);
							System.out.println("amount successfully debited and updated");
						}
						catch(InvalidAmountException e) {
							System.out.println(e);
						}catch(NumberFormatException e) {
							System.out.println("Amount must be a number");
						}
						break;
					}	
				case 3:
						System.out.println("balance is: "+this.customer.getBalance());
						break;	
				case 4:{
						System.out.println("Enter the new password");
						String password = sc.next();
						this.customer.setPassword(password);
						
						csvwriter.updateUserEntryToCsv(this.customer);
						System.out.println("password is successfully updated");
						break;
					}
				case 5:{
					logFlag = false;
					System.out.println("Successfully Logged out");
					break;
				}	
				default:
						System.out.println("Invalid option!");
					}
				
				if(logFlag) {
					System.out.println("Do you want to try again? Y/N");
					tryAgain = sc.next().equalsIgnoreCase("Y");
				}
			}
		}
	}
}