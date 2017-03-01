package app;

import java.util.ArrayList;

import org.joda.time.LocalDate;

import lib.ConsoleIO;

public class Planner {
	
	private static ArrayList<LocalDate> dates = new ArrayList<>();

	public static void run(){
		menu();
		makeDates();
	}
	
	public static void makeDates(){
		for(int i = 1; i < 13; i++){
			for(int j = 1; j < 32; j++){
				if(i == 4 && j == 31 || i == 6 && j == 31 || i == 9 && j == 31 || i == 11 && j == 31){
					j = 32;
				}else if(i == 2 && j == 29){
					j = 32;
				}
				if(j != 32){
					LocalDate date = new LocalDate(2016, i, j);
					dates.add(date);
				}
			}
		}
	}
	
	public static void menu() {
		String[] options = { "1. Add task", "2. Remove Task", "3. Edit Task" };
		boolean isValid = true;
		while (isValid) {
			int userOpt = ConsoleIO.promptForMenuSelection(options, true);
			if (userOpt == 1) {
				addTask();
			} else if (userOpt == 2) {
				removeTask();
			} else if (userOpt == 3) {
				editTask();
			} else if (userOpt == 0) {
				System.exit(0);
			} else {
				System.out.println("Invalid input. Please, enter valid input.");
				isValid = false;
			}
		}
	}
	
	public static void addTask(){
		
	}
	
	public static void removeTask(){
		
	}
	
	public static void editTask(){
		
	}

}
