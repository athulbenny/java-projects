package todo.repository;


import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.List;

import todo.model.Todo;
import todo.model.Todo.Status;

public class CsvReader {

	static String path = Constants.filepath;
	Status status = Status.NOT_STARTED;
	
	public  List<Todo> readFromCsv() {
		Constants.todoList.clear();
		try(BufferedReader br = new BufferedReader(new FileReader(path))) {
			String line;
			while((line=br.readLine())!=null) {
				String[] values = line.split(",");
				Todo todo = new Todo(Integer.parseInt(values[0]), values[1], values[2],values[3],LocalDate.parse(values[4]),status.parse(values[5]));
				Constants.todoList.add(todo);
			}
		}catch(Exception e ) {
			e.printStackTrace();
		}
		return Constants.todoList;
	}
	
}

