package basic;

import java.util.SortedSet;
import java.util.TreeSet;

public class TreeSetDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SortedSet<String> fruits = new TreeSet<>();
		
		System.out.println("is fruits empty: "+fruits.isEmpty());
		
		fruits.add("banana");
		fruits.add("apple");
		fruits.add("Grape");
		
		System.out.println("fruits are:" + fruits);
		
		fruits.add("Mango");
		fruits.add("grapes");
		
		System.out.println("fruits after modification are  " + fruits);
	}

}
