package models;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;

import app.Planner;
import enums.Months;

public class Calendar {
	
	public ArrayList<Task> tasks = new ArrayList<>();
	public ArrayList<Dates> dates = new ArrayList<>();
	
	public Calendar(){
		
	}
	
	public void printCalendar(Planner planner){		
		Collections.sort(tasks);
		for (Task task : tasks){
			System.out.println(task.toString());
		}
	}
	
	public void populateCalendar(){
		
	}
	
	public void addTask(){
		
	}
	
	public void completeTask(){
		
	}
	
	public void removeTask(){
		
	}
	
}
