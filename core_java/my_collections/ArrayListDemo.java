package my_collections;

import java.util.ArrayList;
import java.util.List;

public class ArrayListDemo {
	
	public static void main(String args[]) {
		
		List myList1 = new ArrayList();
		List myList2 = new ArrayList(20);
		
		List primeNumbers = new ArrayList();
		primeNumbers.add(2);
		primeNumbers.add(3);
		primeNumbers.add(5);
		primeNumbers.add(7);
		primeNumbers.add(11);
		List addAllPrimeNumbers = new ArrayList<>(primeNumbers);
		
		List<Integer> nextPrime = new  ArrayList<>();
		nextPrime.add(13);
		nextPrime.add(17);
		nextPrime.add(19);
		
		addAllPrimeNumbers.add(nextPrime);
		
		System.out.println(addAllPrimeNumbers);
		
		
	}

}
