package todo;

import java.text.ParseException;
import java.util.Scanner;

import todo.services.AddAndTrack;
import todo.services.FilterService;

public class Main {
	public static void main(String args[]) {
		
		Scanner sc = new Scanner(System.in);
		boolean tryAgain =true;
		AddAndTrack addntrack = new AddAndTrack();
		FilterService fs = new FilterService();
		
		while(tryAgain) {
			try {
				System.out.println("Enter your choice");
			System.out.println("1: Add\t2: Modify\t3: display\t4: filter\t5: sort");
			int n =sc.nextInt();
			switch(n) {
			case 1: {
				try {
					addntrack.addTodo();
				} catch (ParseException e) {
					e.printStackTrace();
				}
				break;
			}
			case 2: {
				addntrack.modifyTodo();
				break;
			}
			case 3:{
				addntrack.displayTodo();
				break;
			}
			case 4:
				fs.filter();
				break;
			case 5:
				fs.sort();
				break;
			default:
				System.out.println("invalied choice");
		}
			System.out.println("do you want to continue(Y/n)");
			tryAgain = sc.next().equalsIgnoreCase("Y");
	  }catch(Exception e) {
		System.out.println("Exception is catched due to invalid input, RETRY!!!");  
	  }
		}	
	}
}
