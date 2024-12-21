package todo.comparator;

import java.util.Comparator;

import todo.model.Todo;

public class DateComp implements Comparator {

	@Override
	public int compare(Object o1, Object o2) {
		Todo todo1 = (Todo)o1;
		Todo todo2 =(Todo)o2;
		
		return todo1.duedate.compareTo(todo2.duedate);
	}

}
