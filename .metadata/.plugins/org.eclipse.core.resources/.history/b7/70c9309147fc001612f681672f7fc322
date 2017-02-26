package models;

import java.util.ArrayList;
import java.util.Collections;

import app.Planner;

public class Calendar {
	
	public void printCalendar(Planner planner){
		ArrayList<Task> tasks = new ArrayList<>();
		for (Task task : planner.getEvents().values()){
			tasks.add(task);
		}
		Collections.sort(tasks);
		for (Task task : tasks){
			System.out.println(task.toString());
		}
	}
	
}
