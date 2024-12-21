package basic;

import java.util.Comparator;
import java.util.PriorityQueue;

class EmployeeQ implements Comparable<EmployeeQ>{
	String name;double salary;
	
	EmployeeQ(String name, double salary){
		this.name = name;
		this.salary = salary;
	}
	
	public String toString() {
		return "Employee{name = "+name+", salary:  "+ salary+ " }" ;
	}
	
	
	public int compareTo(EmployeeQ employee) {
		System.out.println((int)(this.salary - employee.salary));
		return (int)(this.salary - employee.salary);
	}
	
}

 public class QueueDemo{
	 public static void main(String args[]) {
		 PriorityQueue<EmployeeQ> pqe = new PriorityQueue<EmployeeQ>();
		 PriorityQueue<EmployeeQ> pqe1 = new PriorityQueue<EmployeeQ>(new ComparatorImpl1());
		 pqe.add(new EmployeeQ("aswanth",51700));
		 pqe.add(new EmployeeQ("asad",50000));
		 pqe.add(new EmployeeQ("samanyu",60000));
		 pqe.add(new EmployeeQ("koti",50445));
		 System.out.println(pqe);
	 }
 }
 
 class ComparatorImpl1 implements Comparator<EmployeeQ>{
	 public int compare(EmployeeQ e1, EmployeeQ e2) {
		 return e1.compareTo(e2);
	 }
 }