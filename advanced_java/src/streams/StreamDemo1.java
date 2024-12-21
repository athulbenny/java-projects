package streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamDemo1{

	public static void main(String[] args) {
		
        List<String> gamesList = Arrays.asList("Football","Cricket","Chess","Chess","Hockey"); 
//        List<String> gameList = List.of("Football","Cricket","Chess","Chess","Hockey"); 
	    
        System.out.println("=====================");
	    gamesList.forEach(item->System.out.print(item+" "));
	    gamesList.forEach(System.out::println);
		
	    System.out.println("=====================");
	    gamesList.stream().forEachOrdered(game->System.out.print(game+" "));
	    gamesList.stream().forEachOrdered(System.out::println); 
	    
//	    Predicate<String> isStartsWithC = s-> s.startsWith("C");
	    
        System.out.println("=====================");
        List<String> result = gamesList.stream()
        		.filter(item->item.startsWith("C")) //isStartsWithC
        		.map(String::toUpperCase) //item->item+" hi"
        		.distinct()
        		.sorted()  
        		//.peek(item-> xyz.add(item)) // xyz can be a set
        		//.peek(item->sysout(item)) //peek work similar to forEach
        		.collect(Collectors.toList());//.collect(Collectors.toCollection(TreeSet::new)); 
        result.forEach(System.out::println);
         
        System.out.println("=====================");
        System.out.println(result.stream().count());
        System.out.println(result.stream().findFirst().get());
        
        System.out.println("=====================");
        String concatenated = gamesList.stream().reduce("",(initial,finalString)->initial+" "+finalString);
        System.out.println(concatenated);
        
        System.out.println("=====================");
        System.out.println(gamesList.stream().anyMatch(item->item.startsWith("C"))); //allMatch
        
        System.out.println("=====================");

	}	
}

/**
 * intermediate operations:
 * 		map, filter, sorted, flatmap, distinct, peek
 * 
 * terminal operations:
 * 		collect, forEach, reduce, count, findFirst, allMatch, anyMatch	
 */
