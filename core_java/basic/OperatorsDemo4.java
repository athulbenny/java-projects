package basic;
import java.util.Scanner;

public class OperatorsDemo4 {

	public static void main(String args[]) {
		
		int x , y;
		try (Scanner sc = new Scanner(System.in)) {
			System.out.println("Enter x = " + (x = sc.nextInt()));
//		x = sc.nextInt();
			y = sc.nextInt();
		}
		
		System.out.println("isEqual x and y " + (x == y));
		
		System.out.println("isnotEqual x and y " + (x != y));
		
		System.out.println("isLesserandEqual x and y " + (x <= y));
		
		System.out.println("isLesser x and y " + (x < y));
		
		System.out.println("isGreaterandEqual x and y " + (x >= y));
		
		System.out.println("isGreater x and y " + (x > y));
		
	}
}
