package models;

import java.util.HashMap;

public class Planner {
	private HashMap<String, Task> events = new HashMap<>();
	private Task task;
	
	public Planner() {
		
	}
	
	public Planner(HashMap<String, Task> events) {
		this.events = events;
	}
	
	public HashMap<String, Task> getEvents() {
		return events;
	}
	
	public void setEvents(HashMap<String, Task> events) {
		this.events = events;
	}
	
	public Task getTask() {
		return task;
	}
	
	public void setTask(Task task) {
		this.task = task;
	}
	
	
}
