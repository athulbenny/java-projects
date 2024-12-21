package scrum17;

import java.util.Scanner;

class WithdrawalException extends Exception{
	WithdrawalException(String msg){
		super(msg);
	}
}

public class Question2  {
	public static void main(String args[]) {
		
		Scanner sc =new Scanner(System.in);
		
		System.out.println("Enter the balance");
		double balance = sc.nextDouble();
		
		System.out.println("Enter the amount");
		double amount= sc.nextDouble();
		
		try {
			if(amount>balance) {
				throw new WithdrawalException("amount exceeds the balance");
			}
		}catch(WithdrawalException e) {
			System.out.println(e);
		
		}
	}
}
