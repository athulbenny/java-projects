package streams;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

class MinMaxStreamDemo {
	public static void main(String args[]) {
		List<String> strList = Arrays.asList("apple", "banana", "pear", "orange", "kiwi");
		
		//alpebetic comparison
		System.out.println(strList.stream().min(String::compareTo));
		//length comparison , if same length looks on insertion order
		System.out.println(strList.stream().min(Comparator.comparingInt(String::length)));
	}
}
