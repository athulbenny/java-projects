package basic;

import java.util.Scanner;

public class WhileDemo {
	
	public static void main(String args[]) {
		
		try (Scanner sc = new Scanner(System.in)) {
			System.out.println("Enter the table to be printed");
			int table = sc.nextInt();
			
			System.out.println("Enter the number of iteration");
			int itrs = sc.nextInt();
			
			int itr=1;
			while( itr <= itrs ) {
				System.out.println(table + " * " + itr + " = " + ( table * itr ));
				++itr;
			}
		}
		
	}

}
