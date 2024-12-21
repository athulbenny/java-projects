package basic;
import java.util.Scanner;

enum Days{
	monday, tuesday, wednesday, thursday, friday,saturday,sunday
}

public class SwitchDemo {

	public static void main(String[] args) {
		
		try (Scanner sc = new Scanner (System.in)) {
			System.out.println("Enter the day");
			String s = sc.next();
			
			Days day1 = Days.monday;
			
			for(Days day: Days.values()) {
				if(s.equalsIgnoreCase(day.toString())) {
					day1=day;
					break;
				}
			}
			
			switch( day1 ) {
				case wednesday:
					System.out.println("wednesday: mid day");
				case monday,tuesday,thursday,friday:
					System.out.println(day1 + " : workday");
					break;
				case saturday, sunday:
					System.out.println("off days");
					break;
				default:
					System.out.println("please recheck the entry");
			}
		}
		
	}

}
