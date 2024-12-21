package streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FilterStreamDemo {
	
	public static void main(String args[]) {
		
		List<String> strList = Arrays.asList("apple","oaai","banana","orange","grapes");
		List<String> strList2 = Arrays.asList("banana","orange","water melon");
		
		//search for letter 'p'
		char search = 'p';
		long count = strList.stream().filter(item->item.contains(String.valueOf(search))).count();
		System.out.println(count);
		
		
		//checks for common elemnts in strList and strList2
		List<String> commonItems = strList.stream()
				.filter(strList2::contains)
				.collect(Collectors.toList());
		commonItems.forEach(System.out::println);
		
		
		"apple".chars().forEach(System.out::println);//prints ascii code of each character of thwt string
		
		
		//string contains only vowels
		List<String> vowelStrings = strList.stream()
                .filter(s -> s.matches("[aeiouAEIOU]+"))
                .collect(Collectors.toList());
		System.out.println("Strings containing only vowels: " + vowelStrings);

		
		//display string that contain duplicat characters
		List<String> duplicateStrings = strList.stream()
                .filter(s -> s.length() != s.chars().distinct().count())
                .collect(Collectors.toList());
		duplicateStrings.forEach(System.out::println);
		
		
		//elements with more than 2 'a', filter inside a filter
		char targetChar = 'a';
		List<String> conatiningCharAtleast3 = strList.stream()
				.filter(s->s.chars().filter(c-> c== targetChar).count()>2)
				.collect(Collectors.toList());
		System.out.println(conatiningCharAtleast3);
		
		
		//display only prime numbers
		List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15);
		System.out.println(numbers.stream().filter(FilterStreamDemo::isPrime).collect(Collectors.toList()));
	}
	
	
	public static boolean isPrime(int n) {
		if(n<=1) return false;
		for(int i=2;i<=Math.sqrt(n);i++) {
			if(n%i==0) {
				return false;
			}
		}
		return true;
	}
}
