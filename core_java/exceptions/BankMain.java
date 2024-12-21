package exceptions;

import java.util.Scanner;

public class BankMain {

	public static void main(String args[]) {
		
		BankServicesImpl bank = new BankServicesImpl();
		Scanner sc= new Scanner(System.in);
		String option = "";
		
		do {
			System.out.println("Select one option: ");
			System.out.println("1: Deposit,    2: Withdraw,    3: BankBalvce");
			option = sc.next();
			
			switch(option) {
			
				case "1":{
					System.out.println("Enter the amount to be deposited");
					double amount = sc.nextDouble();
					try {
						bank.deposit(amount);
					}catch(InvalidAmountException e) {
						System.out.println(e.getMessage());
					}
					bank.balance();
					break;
				}
				
				case "2":{
					System.out.println("Enter the amount");
					double amount = sc.nextDouble();
					double withdrawAmount = 0;
				
					try {
						withdrawAmount = bank.withDraw(amount);
					}catch(InvalidAmountException | InsufficientFundsException e) {
						System.out.println(e.getMessage());
					}
					System.out.println("withdraw amount: " + withdrawAmount);
					bank.balance();
					break;
				}
					
				case "3":
					bank.balance();
					break;
					
				default:
					System.out.println("Invalid option");
				
			}
			System.out.println("Do you like to continue");
			option = sc.next();
		}while(option.equalsIgnoreCase("yes"));	
	}
}
