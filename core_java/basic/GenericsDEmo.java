package basic;

import java.util.List;
import java.util.ArrayList;

public class GenericsDEmo {

	
	public static void main(String args[]) {
		
		List<String> names = new ArrayList<String>();
		//names.add(new StringBuilder("malli"));
		names.add("My name");
		printNames(names);
	}
	
	public static void printNames(List<String> list) {
		for(String name: list) {
			System.out.println(name);
		}
	}
}
