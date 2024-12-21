package todo.services;

import java.util.Scanner;
import java.util.TreeSet;

import todo.comparator.CategoryComp;
import todo.comparator.DateComp;
import todo.comparator.StatusComp;
import todo.model.Todo;
import todo.repository.Constants;
import todo.repository.CsvReader;

public class FilterService {
	
	public FilterService() {
		csvreader.readFromCsv();
	}
	
	Scanner sc = new Scanner(System.in);
	CsvReader csvreader = new CsvReader();
	
	public void sort() {
		System.out.println("Sort by:: 1: Category\t2: Status\t3: Date");
		int sortid = sc.nextInt();
		boolean flag = true;
		if(sortid==1) {
			categoryFilter(flag);
		}
		if(sortid == 2) {
			statusFilter(flag);
		}
		if(sortid==3) {
			dateFilter();
		}
	}
	
	public void filter() {
		System.out.println("Filter by:: 1: Category\t2: Status");
		int filterid = sc.nextInt();
		boolean flag =false;
		if(filterid==1) {
			categoryFilter(flag);
		}
		if(filterid == 2) {
			statusFilter(flag);
		}
	}
	
	public void categoryFilter(boolean flag) {
		
		TreeSet<Todo> t = new TreeSet<>(new CategoryComp());
		t.addAll(Constants.todoList);
		if(flag) {
			for(Todo todo: t) {
			System.out.println(todo);
			}
		}else {
			System.out.println("Enter category");
			String catg = sc.next();
			for(Todo todo: t) {
				if(todo.category.equalsIgnoreCase(catg)) {
					System.out.println(todo);
				}
			}
		}
	}
	
	public void statusFilter(boolean flag) {
		TreeSet<Todo> t = new TreeSet<>(new StatusComp());
		t.addAll(Constants.todoList);
		if(flag) {
			for(Todo todo: t) {
				System.out.println(todo);
			}
		}else {
			int count = 0;
			System.out.println("Enter Status");
			String catg = sc.next();
			for(Todo todo: t) {
				if(todo.status.toString().equalsIgnoreCase(catg)) {
					count++;
					System.out.println(todo);
				}
			}
			System.out.println("number of " + catg + " tasks are: " + count);
		}	
	}
	
	public void dateFilter() {
		TreeSet<Todo> t = new TreeSet<>(new DateComp());
		t.addAll(Constants.todoList);
		for(Todo todo: t) {
			System.out.println(todo);
		}
	}
}
