package streams;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ReduceStreamDemo {

	public static void main(String[] args) {
		
		List<Integer> numbers = List.of(1,2,4,5,6);
		List<String> str = Arrays.asList("aswanth","asad","samanyu");
		
		//String concatenation
		System.out.println(str.stream().reduce("", (str1,str2)->str1+""+str2));
		System.out.println(str.stream().collect(Collectors.joining())); //joining(",")
		
		///Integer operations
		System.out.println(numbers.stream().reduce(1, (num1,num2)-> num1*num2));

		//string reverse
		System.out.println(List.of(str.stream().reduce("", (str1,str2)->str2+","+str1).split(",")));
		Collections.reverse(str);
		
		//number reverse
		System.out.println(List.of(numbers.stream()
				.map(String::valueOf)
				.reduce("", (num1,num2)->num2+","+num1).split(",")));
		
		System.out.println(IntStream.range(0, numbers.size())
				.mapToObj(i-> (numbers.get(numbers.size()-i-1)))
				.collect(Collectors.toList()));

	}

}
