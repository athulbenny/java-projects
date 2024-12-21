package basic;

public class ExceptionDemo {

	public static void main(String args[]) {
		
		int numbers[] = {0,1,2};
		try {
			System.out.println(numbers[5]);
		}catch(Exception e) {
			System.out.println("Exception: "+ e);
		}
	}
}
