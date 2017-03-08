package models;

import java.io.Serializable;
import java.util.ArrayList;

import org.joda.time.LocalDate;

import characters.Hero;

public class User extends Hero implements Serializable{
	private static final long serialVersionUID = 1L;
	private String name;
	private ArrayList<Task> taskList = new ArrayList<>();
	private ArrayList<LocalDate> dates = new ArrayList<>();
	
	public User(String name){
		setStrBase();
		setDefBase();
		setLuckBase();
		setStrMod();
		setDefMod();
		setLuckMod();
		setBaseHP();
		setCurrentHP(getBaseHP());
		setRP(getRP());
		this.name = name;
	}
	
	public ArrayList<Task> getTaskList(){
		return taskList;
	}
	
	public void setTaskList(ArrayList<Task> taskList){
		this.taskList = taskList;
	}
	
	public ArrayList<LocalDate> getDates(){
		return dates;
	}
	
	public void setDates(ArrayList<LocalDate> dates){
		this.dates = dates;
	}
	
	public void addToTaskList(Task e){
		taskList.add(e);
	}
	
	public void addToDates(LocalDate e){
		dates.add(e);
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}

	@Override
	public int attack() {
		return 0;
	}

	@Override
	public int takeDamage(int damage, int armorDR, int defense) {
		return 0;
	}
}
