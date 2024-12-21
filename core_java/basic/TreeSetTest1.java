package basic;

import java.util.Comparator;
import java.util.TreeSet;

class CustomComp implements Comparator<Object>{
	public int compare(Object o1,Object o2) {
		Integer i1 = (Integer)o1;
		Integer i2 = (Integer)o2;
		
		if(i1 < i2) {
			return 1;
		}else if (i1 > i2) return -1;
		else return 0;
	}
}

public class TreeSetTest1 {

	public static void main(String[] args) {
			TreeSet<Integer> ts = new TreeSet<Integer>(new CustomComp());
			ts.add(10);
			ts.add(0);
			ts.add(5);
			
			System.out.println(ts);
	}

}
