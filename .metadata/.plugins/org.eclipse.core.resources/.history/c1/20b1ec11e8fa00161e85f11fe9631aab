package models;

import java.util.ArrayList;
import java.util.Collections;

public class Calendar {
	private Planner planner;
	
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

	public Planner getPlanner() {
		return planner;
	}

	public void setPlanner(Planner planner) {
		this.planner = planner;
	}
	
	
}
