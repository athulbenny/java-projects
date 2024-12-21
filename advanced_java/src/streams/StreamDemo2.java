package streams;

//functional interface
@FunctionalInterface // annotation not necessary, only one abstract function is allowed
interface DoSomthing{
	public int runHere(int a, int b);
//	default int hi(){
//		return 1;
//	}
}

public class StreamDemo2 {
	public static void main(String args[]) {
		
		DoSomthing dsg = new DoSomthing() {
			@Override
			public int runHere(int a, int b) {
				return a*b;
			}
		};
		
		DoSomthing ds = (a,b)->a+b;
		System.out.println(ds.runHere(11,20));
	}
}
