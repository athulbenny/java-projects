package todo.services;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Scanner;

import todo.model.*;
import todo.model.Todo.Status;
import todo.repository.*;

public class AddAndTrack {
	
	public AddAndTrack() {
		csvreader.readFromCsv();
	}
	
	Scanner sc = new Scanner(System.in);
	CsvWriter csvwriter = new CsvWriter();
	CsvReader csvreader = new CsvReader();
	int id;
	
	public void addTodo() throws ParseException {
		if(Constants.todoList.isEmpty()) {
			id=1;
		}else {
			id=Constants.todoList.size()+1;
		}
		System.out.println("Enter title");
		String title = sc.next();
		System.out.println("Enter category");
		String category = sc.next();
		System.out.println("Enter desc");
		String desc = sc.next();
		System.out.println("Enter date(yyyy-MM-dd)");
		String date = sc.next();
		LocalDate localdate= LocalDate.parse(date);		
		
		Todo todo = new Todo(id,title,category,desc, localdate,Status.NOT_STARTED);
		Constants.todoList.add(todo);
		csvwriter.addTodoEntryToCsv(todo);
	}
	
	public void modifyTodo() {
		System.out.println("Enter the id of the todo to be modified");
		int id = sc.nextInt();
		System.out.println("Enter 1: task completed\t2:in progress\t3:no change");
		int choice = sc.nextInt();
		for(Todo todo: Constants.todoList) {
			if(todo.id == id) {
				if(choice==1) {
					todo.status = Status.COMPLETED;
				}else if(choice==2){
					todo.status = Status.IN_PROGRESS;
				}
			}
		}
		csvwriter.rewriteDataToCsvAfterUpdate();
	}
	
	public void displayTodo() {
		System.out.println("id, title, Category, due date, status");
		for(Todo todo: Constants.todoList) {
			System.out.println(todo);
		}
	}
}
