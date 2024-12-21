package basic;

class Parent{
	public void m1() {
		System.out.println("this is parent class");
	}
}

class Child extends Parent{
	public void m2() {
		System.out.println("this is child class");
	}
}

public class Test {

	public static void main(String[] args) {

		Parent parent = new Parent();
		parent.m1();
//		parent.m2();
		
		Child child = new Child();
		child.m1();
		child.m2();
		
		Parent par = new Child();
		par.m1();
//		par.m2();
		
//		Child chi = new Parent();
		
	}

}
