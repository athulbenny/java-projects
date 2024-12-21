package scrum17;

import java.util.Scanner;

class InvalidInputException extends Exception{
	InvalidInputException(String msg){
		super(msg);
	}
}

public class Question1  {
	public static void main(String args[]) {
		
		Scanner sc =new Scanner(System.in);
		
		System.out.println("Enter a positive interger");
		int n= sc.nextInt();
		
		try {
			if(n<0) {
				throw new InvalidInputException("negative number is not allowed");
			}
		}catch(InvalidInputException e) {
			System.out.println(e);
		
		}
	}
}

//class InvalidInputException extends Exception{
//	InvalidInputException(String msg){
//		super(msg);
//	}
//}
