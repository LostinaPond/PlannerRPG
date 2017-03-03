package app;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;

import org.joda.time.LocalDate;

import lib.ConsoleIO;
import lib.ProgramUtil;
import models.Task;

public class Planner {
	private static ArrayList<Task> taskList = new ArrayList<>();
	private static ArrayList<LocalDate> dates = new ArrayList<>();
	private static String invalid = "Invalid input. Please, enter valid input.";

	public static void run() throws IOException {
		do{
			int userinput = mainMenu();
			if(userinput == 1){
				menu();
			}else if(userinput == 2){
				makeDates();
			}else if(userinput == 3){
				savemenu();
			}else if(userinput == 4){
				printCalendar();
			}else{
				System.exit(0);
			}
		}while(true);
	}

	public static void makeDates() throws IOException {
		dates.clear();
		int month = promptForMonth();
		int year = promptForYear();
		for(int i = 1; i < 32; i++){
			if(month == 2){
				if(year%4 == 0){
					dates.add(new LocalDate(year, month, i));
					if(i > 28){
						i = 32;
					}
				}else{
					dates.add(new LocalDate(year, month, i));
					if(i > 27){
						i = 32;
					}
				}
			}else{
				dates.add(new LocalDate(year, month, i));
			}
		}
	}

	public static int promptForMonth() {
		String[] months = { ". January", ". February", ". March", ". April", ". May", ". June",
				". July", ". August", ". September", ". October", ". November", ". December" };
		int month = ConsoleIO.promptForMenuSelection(months, false);
		return month;
	}

	public static int mainMenu(){
		String[] options = {". Task Menu", ". Look at a specific Month", ". Save Menu", ". Print Calendar"};
		int retval = ConsoleIO.promptForMenuSelection(options, true);
		return retval;
	}

	public static int promptForYear() throws IOException{
		String input = ConsoleIO.promptForInput("What year would you like?", false);
		int year = Integer.parseInt(input);
		return year;
	}

	public static void menu() {
		String[] options = { ". Add task", ". Remove Task", ". Edit Task", ". Print Tasks" };
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
		String[] options = { "1. Percentage", "2. Checkbox" };
		boolean isValid = false;
		int userOpt = ConsoleIO.promptForMenuSelection(options, false);
		do {
			if (userOpt == 1) {
				Task t = new Task();
				t.setPercent(true);
				nameTask(t);
				startDate(t);
				endDate(t);
				describeTask(t);
				taskPriority(t);
				taskLocation(t);
				taskRecurring(t);
				taskList.add(t);
				isValid = true;
			} else if (userOpt == 2) {
				Task t = new Task();
				nameTask(t);
				startDate(t);
				endDate(t);
				describeTask(t);
				taskPriority(t);
				taskLocation(t);
				taskRecurring(t);
				taskList.add(t);
				isValid = true;
			} else {
				System.out.println(invalid);
			}
		} while (!isValid);
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
					userOpt = ConsoleIO.promptForInt("Please, choose a task to edit, or enter 0 to go back: ", 0,
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
			if (t.isPercent()) {
				String[] options = { "1. Name", "2. Start Date", "3. End Date", "4. Percent Complete", "5. Description",
						"6. Priority", "7. Location", "8. Recurring Status" };
				int userOption = ConsoleIO.promptForMenuSelection(options, false);
				if (userOption == 1) {
					nameTask(t);
				} else if (userOption == 2) {
					startDate(t);
				} else if (userOption == 3) {
					endDate(t);
				} else if (userOption == 4) {
					taskPercent(t);
				} else if (userOption == 5) {
					describeTask(t);
				} else if (userOption == 6) {
					taskPriority(t);
				} else if (userOption == 7) {
					taskLocation(t);
				} else if (userOption == 8) {
					taskRecurring(t);
				} else {
					System.out.println("Invalid input. Please, enter valid input.");
				}
			} else if (!t.isPercent()) {
				String[] options2 = { "1. Name", "2. Start Date", "3. End Date", "4. Completion Status",
						"5. Description", "6. Priority", "7. Location", "8. Recurring Status" };
				int userOption2 = ConsoleIO.promptForMenuSelection(options2, false);
				if (userOption2 == 1) {
					nameTask(t);
				} else if (userOption2 == 2) {
					startDate(t);
				} else if (userOption2 == 3) {
					endDate(t);
				} else if (userOption2 == 4) {
					completeTask(t);
				} else if (userOption2 == 5) {
					describeTask(t);
				} else if (userOption2 == 6) {
					taskPriority(t);
				} else if (userOption2 == 7) {
					taskLocation(t);
				} else if (userOption2 == 8) {
					taskRecurring(t);
				} else {
					System.out.println("Invalid input. Please, enter valid input.");
				}
			}
		}
		menu();
	}

	public static void savemenu() {
		String[] options = { ". save", ". load", ". back to home" };
		boolean isValid = true;
		while (isValid) {
			try {
				int userOpt = ConsoleIO.promptForMenuSelection(options, false);
				if (userOpt == 1) {
					save();
				} else if (userOpt == 2) {
					load();
				} else if (userOpt == 3) {
					isValid = false;
				}
			} catch (IOException e) {
				System.out.println(invalid);
			}
		}
	}

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
			String[] options = { ". Daily", ". Weekly", ". Monthly" };
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
	
	public static void printCalendar(){
		for(LocalDate date : dates){
			System.out.println(date.toString());
		}
	}

	public static void save() throws IOException {
		String filepath = ConsoleIO.promptForInput("What is the file you'd like to save to?", false);
		Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filepath), "utf-8"));
		ProgramUtil.writeToFile(filepath, "Dates");
		for (LocalDate date : dates) {
			writer.append("--\n");
			writer.append(date.toString() + "\n");
		}
	}

	public static void load() throws IOException {
		String filepath = ConsoleIO.promptForInput("Which file do you want to load?", false);
		ProgramUtil.readFile(filepath);
	}

}
