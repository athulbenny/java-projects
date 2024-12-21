package basic;

import java.util.*;

/*read the operation
 * add, sub, mul, div, mod
 * please enter first number
 * please enter second number
 * perform operation
 * would u like to continue 
 * if yes continue from top as ask for operation
 * */

public class Calculator {

	@SuppressWarnings("finally")
	public static void main(String[] args) {
		
		try (Scanner sc = new Scanner(System.in)) {
			boolean checkForNextAttempt = true;
			
			while(checkForNextAttempt) {
				
				System.out.println("Enter the operation: Enter 1: ADD, 2: Subtract, 3: Multiply, 4: Division, 5: Mod");
				int operation = sc.nextInt();
				
				System.out.println("Enter the first number");
				double num1 = sc.nextDouble();
				
				System.out.println("Enter the second number");
				double num2 = sc.nextDouble();
				
				System.out.print("Result after the operation is: ");
				switch(operation) {
					case 1:
						System.out.println(num1+num2);
						break;
					case 2:
						System.out.println(num1-num2);
						break;
					case 3:
						System.out.println(num1*num2);
						break;
					case 4:
						try {
							System.out.println(num1/num2);
						}catch(Exception e) {
							System.out.println("Exception while divison");
						}finally {
							break;
						}
					case 5:
						System.out.println(num1%num2);
						break;
					default:
						System.out.println("Operator miss match, retry again");	
				}
				
				System.out.println("Would you like to continue: true/false");
				checkForNextAttempt = sc.nextBoolean();
			}
		}
		System.out.println("Thanks!!!");
	}

}
