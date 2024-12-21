package streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SortStreamDemo {

	public static void main(String args[]) {
		
		
		List<Integer> numbers = Arrays.asList(4,2,8,6,10);
		System.out.println(numbers.stream()
				.sorted((num1,num2)->num2-num1) //reverse sorting
				.skip(1)
				.findFirst().get()); //second largest element
		}
}
