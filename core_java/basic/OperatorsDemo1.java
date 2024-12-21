package basic;

public class OperatorsDemo1 {

	/**
	 * @param args
	 */
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		int i = 10;
		int i2 = -10;
		System.out.println("i value = " + i);
		System.out.println("i2 value = " + i2);
		
		int i3 = i++; //post increment
		System.out.println("i3 value is = " + i3);
		System.out.println("i value is = " + i);
		
		int i4 = ++i; // pre increment
		System.out.println("i4 value is = " + i4);
		System.out.println("i value is = " + i);
		
		int j = --i; //pre decremnet
		System.out.println("j is " + j);
		System.out.println("i is " + i);
		

		int j1 = i--; //pre decremnet
		System.out.println("j is " + j1);
		System.out.println("i is " + i);
	}

}
