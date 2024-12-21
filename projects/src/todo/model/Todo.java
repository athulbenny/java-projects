package todo.model;

import java.time.LocalDate;

public class Todo {
	
	public enum Status{
		NOT_STARTED,IN_PROGRESS,COMPLETED;
		
		public Status parse(String value) {
			if(value.equalsIgnoreCase(Status.COMPLETED.toString())) {
				return Status.COMPLETED;
			}else if(value.equalsIgnoreCase(Status.IN_PROGRESS.toString())) {
				return Status.IN_PROGRESS;
			}else {
				return Status.NOT_STARTED;
			}
		}
	}

	public int id;
	public String category;
	public String title;
	public String desc;
	public LocalDate duedate;
	public Status status = Status.NOT_STARTED;; 
	
	public Todo(int id,String title, String category, 
			String desc, LocalDate duedate, Status status) {
		this.id = id;
		this.title = title;
		this.category = category;
		this.desc = desc;
		this.duedate = duedate;
		this.status = status;
	}
	
	public String toString() {
		return this.id+", "+this.title+", "+this.category+", "+this.desc+", "+this.duedate+", "+this.status;
	}
	
}	
