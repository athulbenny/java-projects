package streams;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class MapStreamDemo {

	public static void main(String[] args) {
		List<Integer> numbers = List.of(1,2,2,2,3,3,5,4,3); 	
		List<String> fruits = Arrays.asList("Apple","Orange","Banana","Grapes");
		
		
		//find sum of elements
		int sum = numbers.stream().mapToInt(Integer::intValue).sum();
		System.out.println("sum: " + sum);
		
		
		//find max element
		System.out.println(numbers.stream().max(Integer::compareTo));//Stream
		System.out.println(numbers.stream().map(Integer::intValue).max(Integer::compareTo));
		System.out.println(numbers.stream().mapToInt(Integer::intValue).max()); //IntStream
		
		
		//find average
		System.out.println(numbers.stream().mapToDouble(Integer::doubleValue).average());
	
		
		//checks whether all elements are of same length
		System.out.println(fruits.stream()
				.map(String::length)
				.distinct()
				.count()==1);
		
		
		//remove all non-numeric characters from string
		List<String> strings = Arrays.asList("a1b2c4", "4a2b3c", "126abc","123");
        Pattern pattern = Pattern.compile("[^0-9]");
        List<String> numericStrings = strings.stream()
                .map(s -> pattern.matcher(s).replaceAll(""))
                .collect(Collectors.toList());
        System.out.println(numericStrings);
		
		
        
    	//display list elemts that contains numbers only
		System.out.println(strings.stream().filter(s->s.matches("\\d+")).collect(Collectors.toList()));
	
		
		
		//mode of list of integers: most frequent element
		Map<Integer,Long> freqMap = numbers.stream().collect(Collectors.groupingBy(i->i, Collectors.counting()));
		long maxFreq = freqMap.values().stream().max(Long::compareTo).get();
		List<Integer> modes = freqMap.entrySet().stream()
				.filter(item->item.getValue()==maxFreq)
				.map(Map.Entry::getKey)
				.collect(Collectors.toList());
		System.out.println(modes);
		
		
			
		//elements with maximum numbers of vowels
		Map<String, Long> frequencyMap = fruits.stream()
                .collect(Collectors.toMap(s -> s, s -> s.chars().filter(c -> "AEIOUaeiou".indexOf(c) != -1).count()));
        long maxVowelCount = frequencyMap.values().stream().mapToLong(Long::longValue).max().orElse(0);
        List<String> maxVowelStrings = frequencyMap.entrySet().stream()
                .filter(entry -> entry.getValue() == maxVowelCount)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        System.out.println(maxVowelStrings);
	}

}