package models;

public class Kanban {
	
	static int count = 0;
	
	int id;
	String title;
	String task;

	public Kanban(String title, String task) {
		super();
		this.title = title;
		this.task = task;
		
		this.id = count++;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	

}