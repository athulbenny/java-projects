package basic;

import java.util.TreeSet;

public class NavigableSetDemo {

	public static void main(String args[]) {
		TreeSet<Integer> empids = new TreeSet<Integer>();
		empids.add(1001);
		empids.add(2001);
		empids.add(3002);
		empids.add(1500);
		
		
		System.out.println(empids);
		
		System.out.println("ceiling: "+empids.ceiling(2001));
		System.out.println("lower: "+ empids.lower(2001));
		System.out.println("floor " + empids.floor(1500));
		System.out.println("higher: "+ empids.higher(1500));
	}
}
