package basic;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class HashMapTest {

	public static void main(String[] args) {

		Map<Integer,String> map = new HashMap<>();
		
		map.put(101, "name1");
		map.put(102, "name2");
		map.put(103, "name3");
		
		System.out.println("map are: " + map);
		
		System.out.println(map.put(101,"name4"));
		
		System.out.println("Map after editing" +  map);
		
		map.putIfAbsent(105,"name5");
		map.put(106, null);
		map.putIfAbsent(106,"name6");
		
		Set<Integer> keys = map.keySet();
		System.out.println( "" + keys);	
		
		Collection<String> values = map.values();
		System.out.println("values: " + values);
		
		System.out.println("is 105 a key? " + map.containsKey(105));
		System.out.println("is name4 a value? " + map.containsValue("name4"));
		
		Map<String,Integer> details = new HashMap<String,Integer>();
		
		details.put("arjun", 1);
		details.put("aswanth", 2);
		details.put("asad", 4);
		details.put("samanyu", 5);
		details.put("sandeep", 6);
		details.put("yadu", 7);
		
		Set<Entry<String, Integer>> entryAll = details.entrySet();
		for(Map.Entry<String, Integer> entry : entryAll) {
		
		System.out.println(entry.getKey());
		if(entry.getKey() == "asad")
			entry.setValue(8);
		}
		details.putIfAbsent("athul", 3);
		entryAll.forEach((k)->{ System.out.println(k); } );
		details.forEach((k,v)->{System.out.println(k +" "+v);});
		
		/**
		 * {
		 * "yadu":7 => map entry(Map.Entry or Entry)
		 * "athul":3 => map entry
		 * "aswnth":2
		 * "sandeep":6
		 * "asad":8
		 * "samanyu":5
		 * "arjun":1
		 * }
		 * */
	
		
		}

}


