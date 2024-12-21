package basic;

import java.util.Comparator;
import java.util.TreeSet;

class EmployeeT implements Comparable{
	int eid;
	String name;
	
	EmployeeT(int eid, String name){
		this.eid = eid;
		this.name = name;
	}
	
	public int compareTo(Object o) {
		int e1 = this.eid;
		EmployeeT emp = (EmployeeT)o;
		int e2 = emp.eid;
		
		if(e1<e2) {
			return -1;
		}else if(e1>e2) {
			return 1;
		}else return 0;
	}
	
}

class ComparatorImpl implements Comparator{
	public int compare(Object o1, Object o2) {
		EmployeeT e1 = (EmployeeT)o1;
		EmployeeT e2 = (EmployeeT)o2;
		
		return e1.name.compareTo(e2.name);
	}
}

public class EmployeeSortingSet {

	public static void main(String[] args) {
		EmployeeT e1 = new EmployeeT(103,"Amal");
		EmployeeT e2 = new EmployeeT(104,"Anugrah");
		EmployeeT e3 = new EmployeeT(101,"Sai");
		EmployeeT e4 = new EmployeeT(105,"Vyshna");
		EmployeeT e5 = new EmployeeT(102,"Jinil");
		
		TreeSet<EmployeeT> ts = new TreeSet(new ComparatorImpl());
		ts.add(e1);
		ts.add(e2);
		ts.add(e3);
		ts.add(e4);
		ts.add(e5);
		
		for(EmployeeT e: ts)
		System.out.println(e.name);
	}

}
