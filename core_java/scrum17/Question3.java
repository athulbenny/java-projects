package scrum17;

import java.util.Scanner;

public class Question3 {

	public static void main(String[] args) {

		Scanner sc= new Scanner(System.in);
		System.out.println("Enter ther String");
		
		String s = sc.next();
		StringBuilder sb = new StringBuilder();
		sb.append(s.charAt(0));
		
		for(int i=0;i< s.length()-1;i++) {
			 sb.append(s.charAt(i+1)-s.charAt(i));
			 sb.append(s.charAt(i+1)); 
		}
		
		System.out.println(sb);
	}

}
