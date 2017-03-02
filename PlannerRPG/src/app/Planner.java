package app;

<<<<<<< HEAD
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
=======
import java.io.IOException;
>>>>>>> origin/master
import java.util.ArrayList;
import java.util.Collections;

import org.joda.time.LocalDate;

import lib.ConsoleIO;
<<<<<<< HEAD
import lib.ProgramUtil;
=======
import models.Task;
>>>>>>> origin/master

public class Planner {
	private static ArrayList<Task> taskList = new ArrayList<>();
	private static ArrayList<LocalDate> dates = new ArrayList<>();
	private static String invalid = "Invalid input. Please, enter valid input.";

<<<<<<< HEAD
	public static void run() throws IOException{
=======
	public static void run() {
>>>>>>> origin/master
		menu();
		makeDates();
		savemenu();
	}
<<<<<<< HEAD
	
	public static void makeDates(){
		int month = promptForMonth();
	}
	
	public static int promptForMonth(){
		String[] months = {"1. January" ,"2. January" ,"3. January","4. January","5. January","6. January","7. January","8. January","9. January","10. January","11. January","12. January"};
		int month = ConsoleIO.promptForMenuSelection(months, false);
		return month;
=======

	public static void makeDates() {
		for (int i = 1; i < 13; i++) {
			for (int j = 1; j < 32; j++) {
				if (i == 4 && j == 31 || i == 6 && j == 31 || i == 9 && j == 31 || i == 11 && j == 31) {
					j = 32;
				} else if (i == 2 && j == 29) {
					j = 32;
				}
				if (j != 32) {
					LocalDate date = new LocalDate(2016, i, j);
					dates.add(date);
				}
			}
		}
>>>>>>> origin/master
	}

	public static void menu() {
		String[] options = { "1. Add task", "2. Remove Task", "3. Edit Task", "4. Print Tasks" };
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
			} else if (userOpt == 4) {
				printTasks();
			} else {
				System.out.println(invalid);
			}
		}
	}

	public static void addTask() {
		String[] options = {"1. Percentage", "2. HOLY SHIT"};
		int userOpt = ConsoleIO.promptForMenuSelection(options, true);
		Task t = new Task();
		nameTask(t);
		startDate(t);
		endDate(t);
		describeTask(t);
		taskPriority(t);
		taskLocation(t);
		taskRecurring(t);
		taskList.add(t);
	}

	public static void removeTask() {
		ArrayList<Task> tempList = new ArrayList<>();
		for (Task t : taskList) {
			tempList.add(t);
		}
		if (tempList.isEmpty()) {
			System.out.println("There are currently no tasks.");
		} else {
			Collections.sort(tempList);
			int iterator = 0;
			for (Task t : tempList) {
				System.out.println(++iterator + ". " + t.toString());
			}
			int userOpt = 0;
			boolean isValid = false;
			do {
				try {
					userOpt = ConsoleIO.promptForInt("Please, choose a task to remove, or enter 0 to go back: ", 0,
							tempList.size());
					isValid = true;
				} catch (IOException e) {
					System.out.println(invalid);
				}
			} while (!isValid);
			if (userOpt == 0) {
				menu();
			}
			taskList.remove(tempList.get(userOpt - 1));
			System.out.println("Task removed.");
		}
		menu();
	}

	public static void editTask() {
		ArrayList<Task> tempList = new ArrayList<>();
		for (Task t : taskList) {
			tempList.add(t);
		}
		if (tempList.isEmpty()) {
			System.out.println("There are currently no tasks.");
		} else {
			Collections.sort(tempList);
			int iterator = 0;
			for (Task t : tempList) {
				System.out.println(++iterator + ". " + t.toString());
			}
			int userOpt = 0;
			boolean isValid = false;
			do {
				try {
					userOpt = ConsoleIO.promptForInt("Please, choose a task to remove, or enter 0 to go back: ", 0,
							tempList.size());
					isValid = true;
				} catch (IOException e) {
					System.out.println(invalid);
				}
			} while (!isValid);
			if (userOpt == 0) {
				menu();
			}
			Task t = tempList.get(userOpt - 1);
			System.out.println("What would you like to edit about this task?");
			String[] options = { "1. Name", "2. Start Date", "3. End Date", "4. Completion Status",
					"5. Percent Complete", "6. Description", "7. Priority", "8. Location", "9. Recurring Status" };
			int userOption = ConsoleIO.promptForMenuSelection(options, false);
			if (userOption == 1) {
				nameTask(t);
			} else if (userOption == 2) {
				startDate(t);
			} else if (userOption == 3) {
				endDate(t);
			} else if (userOption == 4) {
				completeTask(t);
			} else if (userOption == 5) {
				taskPercent(t);
			} else if (userOption == 6) {
				describeTask(t);
			} else if (userOption == 7) {
				taskPriority(t);
			} else if (userOption == 8) {
				taskLocation(t);
			} else if (userOption == 9) {
				taskRecurring(t);
			} else {
				System.out.println("Invalid input. Please, enter valid input.");
			}
		}
		menu();
	}
<<<<<<< HEAD
	
	public static void savemenu() throws IOException{
		String[] options = {"1. save", "2. load", "3. back to home"};
		boolean isValid = true;
		while(isValid){
			int userOpt = ConsoleIO.promptForMenuSelection(options, false);
			if(userOpt == 1){
				save();
			}else if(userOpt == 2){
				load();
			}else if(userOpt == 3){
				isValid = false;
			}
		}
	}
	
	public static void addTask(){
		
=======

	public static void nameTask(Task t) {
		String name = "";
		boolean isValid = false;
		do {
			try {
				name = ConsoleIO.promptForInput("What would you like to name the task?", false);
				isValid = true;
			} catch (IOException e) {
				System.out.println(invalid);
			}
		} while (!isValid);
		t.setName(name);
>>>>>>> origin/master
	}

	public static void startDate(Task t) {

	}

	public static void endDate(Task t) {

	}

	public static void completeTask(Task t) {
		boolean complete = ConsoleIO.promptForBool("Is the task complete? (y/n)", "y", "n");
		if (complete) {
			t.setComplete(true);
			t.setPercentComplete(100);
		}
	}

	public static void taskPercent(Task t) {
		double percent = 0;
		boolean isValid = true;
		do {
			try {
				percent = ConsoleIO.promptForDouble("What percent complete is the task?", 0, 100);
				isValid = true;
			} catch (IOException e) {
				System.out.println(invalid);
			}
		} while (!isValid);
		if (percent == 100) {
			t.setComplete(true);
			t.setPercentComplete(percent);
		} else {
			t.setPercentComplete(percent);
		}
	}

	public static void describeTask(Task t) {
		String description = "";
		boolean isValid = false;
		do {
			try {
				description = ConsoleIO.promptForInput("Please describe " + t.getName(), false);
				isValid = true;
			} catch (IOException e) {
				System.out.println(invalid);
			}
		} while (!isValid);
		t.setDescription(description);
	}

	public static void taskPriority(Task t) {
		int priority = 1;
		boolean isValid = false;
		do {
			try {
				priority = ConsoleIO.promptForInt("What is the priority of the task?", 1, 5);
				isValid = true;
			} catch (IOException e) {
				System.out.println(invalid);
			}
		} while (!isValid);
		t.setPriority(priority);
	}

	public static void taskLocation(Task t) {
		String location = "";
		boolean isValid = false;
		do {
			try {
				location = ConsoleIO.promptForInput("Where is the task taking place?", false);
				isValid = true;
			} catch (IOException e) {
				System.out.println(invalid);
			}
		} while (!isValid);
		t.setLocation(location);
	}

	public static void taskRecurring(Task t) {
		boolean isRecurring = ConsoleIO.promptForBool("Is the task recurring? (y/n)", "y", "n");
		if (isRecurring) {
			System.out.println("How often is the task recurring?");
			String[] options = { "1. Daily", "2. Weekly", "3. Monthly" };
			int userOpt = ConsoleIO.promptForMenuSelection(options, false);
			if (userOpt == 1) {
				t.setRecurringDaily(true);
				t.setRecurringWeekly(false);
				t.setRecurringMonthly(false);
			} else if (userOpt == 2) {
				t.setRecurringDaily(false);
				t.setRecurringWeekly(true);
				t.setRecurringMonthly(false);
			} else if (userOpt == 3) {
				t.setRecurringDaily(false);
				t.setRecurringWeekly(false);
				t.setRecurringMonthly(true);
			} else {
				System.out.println(invalid);
			}

		}
	}

	public static void printTasks() {
		for (Task t : taskList) {
			System.out.println(t.toString());
		}
	}
	
	public static void save() throws IOException{
		String filepath = ConsoleIO.promptForInput("What is the file you'd like to save to?", false);
		ProgramUtil.writeToFile(filepath, "Dates");
		Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filepath), "utf-8"));
		for(LocalDate date : dates){
			writer.append("--\n");
			writer.append(date.toString() + "\n");
		}
	}
	
	public static void load(){
		
	}

}
