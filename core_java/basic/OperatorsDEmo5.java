package basic;

public class OperatorsDEmo5 {

	public static void main(String arg[]) 
	{
//		System.out.println(false & 10/0==2); // checks both the consitions [arithmetic exception]
		
		System.out.println(false && 10/0==2);
//		
		System.out.println(!false | 10/0==2); // checks both the conditions [arithmetic exception]
		
		System.out.println(!false || 10/0==2);
		
		//ternary operator
		int number = 10;
		String s;
		s = (number > 20)?  "greater" :  "lesser";
		
	}
}
