package basic;

public class OverLoadingTest {
	
	public static void m1() {
		System.out.println("inside m1()");
	}
	
	public static void m1(int x) {
		System.out.println("inside m1(int)");
	}
	
	public static void m1(double d) {
		System.out.println("inside m1(double");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		OverLoadingTest olt = new OverLoadingTest();
		OverLoadingTest.m1();
		OverLoadingTest.m1(10);
		OverLoadingTest.m1(122.0);
		OverLoadingTest.m1();
		
	}

}
