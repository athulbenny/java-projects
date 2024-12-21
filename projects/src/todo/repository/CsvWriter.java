package todo.repository;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import todo.repository.*;
import todo.model.Todo;

public class CsvWriter {
	
	static String path = Constants.filepath;
	
	public void addTodoEntryToCsv(Todo todo) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(path,true))) {
			String s = todo.id+","+todo.title+","+todo.category+","+todo.desc+","+todo.duedate+","+todo.status;
			bw.write(s);
			bw.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		
		}
	}
	
	
	public void rewriteDataToCsvAfterUpdate() {
		List<Todo> todoList = Constants.todoList;
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
			for(Todo todo: todoList){
				String s = todo.id+","+todo.title+","+todo.category+","+todo.desc+","+todo.duedate+","+todo.status;
					bw.write(s);
					bw.newLine();
				}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

