package basic;
import java.util.ArrayList;
import java.util.List;

public class AdvnacedForLoop {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		List<String> names = new ArrayList<>();
		
		names.add("yadu");
		names.add("aswanth");
		names.add("asad");
		names.add("samanyu");

		
		for(String name : names) {
			System.out.println(name.toUpperCase());
		}
	}

}
