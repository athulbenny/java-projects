package basic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ifElseDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int age = 55;
		
		if ( age >= 40 ) {
			System.out.println("eligible for double vacccine with booster dose");
		} else if ( age < 40 && age >= 18 ) {
			System.out.println("eligible for double vaccine");
		} else if ( age < 18 && age >= 12 ) {
			System.out.println("eligible for single vaccine");
		} else {
			System.out.println("not eligible for vaccine");
		}
		
	}
}
