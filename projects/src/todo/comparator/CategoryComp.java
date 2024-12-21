package todo.comparator;

import java.util.Comparator;

import todo.model.*;

public class CategoryComp implements Comparator {

	@Override
	public int compare(Object o1, Object o2) {
		Todo todo1 = (Todo)o1;
		Todo todo2 =(Todo)o2;
		
		if(todo1.category.toString().compareTo(todo2.category.toString())>0) {
			return -1;
		}else return 1;
	}

}
