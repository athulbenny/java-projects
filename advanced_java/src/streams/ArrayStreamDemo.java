package streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ArrayStreamDemo {
	public static void main(String args[]) {
		int[] arr= {0,20,2,3};
		
		Arrays.stream(arr).forEach(item->System.out.println(item));
//		List<String> s1 = Arrays.asList("1","2","3");
		
//		Stream stream = Stream.of(s1);
		Stream stream = Stream.of(arr);
		
//		Stream<int[]> stream = Arrays.stream(arr); => ERROR
//		IntStream intSTream1 = Stream.of(arr); =>ERROR

		IntStream intStream = Arrays.stream(arr);
		
		Stream<int[]> streamInt = Stream.of(arr);
		
		//parallel threading:=> multithreading concept
		
		List<String> list = new ArrayList<>();
		list.add("abc");
		list.add("abcd:");
		list.add("def");
		
		list.parallelStream().forEachOrdered(System.out::println); //same as insertion order
		list.parallelStream().forEach(s->System.out.println(s)); //order may changes due to multi threading

	}
}
