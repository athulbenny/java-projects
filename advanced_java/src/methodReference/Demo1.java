package methodReference;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Demo1 {
	public static void main(String args[]) {
		
		String[] names = {"John","Alice"," bob "};
		Arrays.sort(names,String::compareToIgnoreCase); //method refernce
		
		Arrays.sort(names,(s1,s2)-> s1.compareToIgnoreCase(s2));//lambda expression
		System.out.println(Arrays.toString(names));
		
//		Check checkObj =new Check() {
//			public boolean check(String item) {
//				if(item.equalsIgnoreCase("bob")) {
//					return true;
//				}
//				return false;
//			}
//		};
		
		Check checkObj = (item)-> {
			if(item.equalsIgnoreCase("bob")) {
				return true;
			}return false;
		};
		
		List<String> nameList = List.of(names);
		List<String> namesList1 = nameList.stream()
						.map(String::trim)
						.filter(checkObj::check) //item -> item.equalsIgnoreCase("bob")
						.collect(Collectors.toList());
		System.out.println(namesList1);
	}
}

@FunctionalInterface
interface Check{
	public boolean check(String item) ;
}