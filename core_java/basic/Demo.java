package basic;



public class Demo implements MyInterface1{
	int ABC = 12000;

	public void m1() {
		System.out.println(" ");
	}
	
	public void m2() {
		System.out.println(" ");
	}
	
	public void xyz() {
		System.out.println("");
		}
	public static void main(String args[]) {

	Demo demo = new Demo();
	demo.m1();
	demo.m2();
	demo.xyz();
	demo.m4();
	MyInterface1.m3();

	}
}