package basic;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class TreeSetTest {
	public static void  main(String args[]) {
		
		List<String> mnames = new ArrayList<String>();
		List<String> fnames = new ArrayList<String>();
	
		SortedSet ts = new TreeSet<>();
		mnames.add("Bala");
		mnames.add("Ali");
		mnames.add("chandran");
		
		fnames.add("Ba");
		fnames.add("Ai");
		fnames.add("cran");
		
		ts.addAll(fnames);
		ts.addAll(mnames);
		ts.add(" ");
		
		System.out.println(ts);
	}
}
