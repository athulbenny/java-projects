package streams;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class FeaturesDemo1 {
	public static void main(String args[]) {
		List<String> games = Arrays.asList("january","febrvary","march","april","may","june");
		
		//skip(how many elements to be skipped)
		games.stream().skip(2).forEach(s->System.out.println(s));
		
		//limit(how many elements to be included)
		games.stream().limit(2).forEach(System.out::println);
		
		System.out.println(games.stream().max(Comparator.comparing(String::valueOf)).get());// may: character sequence
		System.out.println(games.stream().min(Comparator.comparing(String::valueOf)).get());// april: character sequence

		
	//some functional interfaces
		Predicate<String> isLongerThanThree = s-> s.length()>3;
		System.out.println(isLongerThanThree.test("Hello"));
		
		Function<String,Integer> stringLength = s->s.length();
		System.out.println(stringLength.apply("Hello"));
		
		Consumer<String> consumer = message -> System.out.println(message);
		consumer.accept("hi all");
		
		Supplier<String> supplier = ()->"hello";
		System.out.println(supplier.get());
		
		
		//display kth largest element
		 List<Integer> numbers = Arrays.asList(3, 1, 4, 1, 5, 9, 2, 6, 5);
	        int k = 3; // Find the 3rd largest element
	        Collections.sort(numbers, Collections.reverseOrder());
	        Integer kthLargest = numbers.stream().distinct().skip(k - 1).findFirst().orElse(null);
	        System.out.println("The " + k + "th largest element: " + kthLargest);
	}
}
