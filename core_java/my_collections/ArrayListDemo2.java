package my_collections;

import java.util.ArrayList;
import java.util.List;

public class ArrayListDemo2 {

	public static void main(String ars[]) {
		List<String> animals = new ArrayList();
		animals.add("Lion");
		animals.add("tiger");
		animals.add("camel");
		
		System.out.println("Animals " + animals);
		
		animals.add(2,"giraffe");
		
		System.out.println("Animals after updation " + animals);
		
		List<Integer> numbers = new ArrayList();
		numbers.add(1);
		numbers.add(2);
		numbers.add(3);
		numbers.add(4);
		try {
			numbers.remove(4);
		}catch(IndexOutOfBoundsException e) {
			System.out.println("jvm searches for 4th index, not for number 4");
		}
		numbers.remove(new Integer(4));
		
		System.out.println(numbers);
	}
}
