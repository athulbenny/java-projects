package basic;

public interface MyInterface1 {
	int ABC = 12000;
	
	public void m1();
	public void m2();
	
	static void m3() {
		System.out.println("static myinterface");	}

	
	
	default void m4() {
		System.out.println("defualt myinterface");	}

	
	
	private void m5() {
		System.out.println("private myinterface");	}
}
