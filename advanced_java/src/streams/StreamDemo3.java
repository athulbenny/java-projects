package streams;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class StreamDemo3 {
	public static void main(String args[]) {
		
		List<String> gamesList = Arrays.asList("Football","Cricket","Chess","Hockey");
		
		//each stream can be used only once, 
		//after each execution the stream will be closed automatically
		//reusing same stream leads to IllegalStateException
		//so need to declare or create new stream at each time
 		Stream<String> strm = gamesList.stream();
        Stream<String> resfilter = strm.filter(item->item.startsWith("C"));
        Stream<String> resmap = strm.map(String::toUpperCase); //error: since strm stream is usd again
        resmap.forEachOrdered(System.out::println);
      
        //As a solution to above problem supplier is used (java.util package)
        //so only need to declare once and reuse multiple time
        Supplier<Stream<String>> streamSupplier = ()-> gamesList.stream(); //Stream.of("Football","Cricket","Chess","Chess","Hockey");
      		 											 
        Stream<String> resfilter2 = streamSupplier.get().filter(item->item.startsWith("C"));
        streamSupplier.get().close();
        Stream<String> resmap2 = streamSupplier.get().map(String::toLowerCase);
        resmap2.forEachOrdered(System.out::println);
	}
}
