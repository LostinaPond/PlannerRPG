package app;

import java.awt.Dimension;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import items.Accessory;
import items.Armor;
import items.Item;
import items.Potion;
import items.Rarity;
import items.Weapon;
import lib.ConsoleIO;
import lib.ProgramUtil;
import models.Shopkeep;
import models.Task;
import models.User;

public class Planner{
	
	private static User u;
	private static String invalid = "Invalid input. Please, enter valid input.";
	private static boolean justOpened = true;

	public static void run() {
		newMenu();
	}

	public static void mainMenu() {
		String[] options = { "1. Task Menu", "2. RPG Menu", "3. Save Menu" };
		int userinput = 0;
		boolean isValid = false;
		do {
			do {
				try {
					userinput = ConsoleIO.promptForMenuSelection(options, true);
					isValid = true;
				} catch (NumberFormatException nfe) {
					System.out.println(invalid);
				}
			} while (!isValid);
			if (userinput == 1) {
				menu();
			} else if (userinput == 2) {
				RPGMenu();
			} else if (userinput == 3) {
				savemenu();
			} else {
				System.exit(0);
			}
		} while (true);
	}

	public static void menu() {
		String[] options = { "1. Add task", "2. Remove Task", "3. Edit Task", "4. Print Tasks",
				"5. Look at a specific month", "6. Print Calendar", "7. Return to Main Menu" };
		boolean isValid = false;
		int userOpt = 0;
		do {
			try {
				userOpt = ConsoleIO.promptForMenuSelection(options, false);
			} catch (NumberFormatException nfe) {
				System.out.println(invalid);
				menu();
			}
			if (userOpt == 1) {
				addTask();
				isValid = true;
			} else if (userOpt == 2) {
				removeTask();
				isValid = true;
			} else if (userOpt == 3) {
				editTask();
				isValid = true;
			} else if (userOpt == 4) {
				printTasks();
				isValid = true;
			} else if (userOpt == 7) {
				mainMenu();
			} else if (userOpt == 5) {
				makeDates();
			} else if (userOpt == 6) {
				printCalendar();
			} else {
				System.out.println(invalid);
			}
		} while (!isValid);

	}

	public static void newMenu() {
		System.out.println("Welcome to PlannerRPG!");
		String[] options = { "1. New User", "2. Load User" };
		boolean isValid = false;
		int userOpt = 0;
		do {
			try {
				userOpt = ConsoleIO.promptForMenuSelection(options, true);
			} catch (NumberFormatException nfe) {
				System.out.println(invalid);
				newMenu();
			}
			if (userOpt == 1) {
				newUser();
				isValid = true;
			} else if (userOpt == 2) {
				justOpened = true;
				loadSwing();
				isValid = true;
			} else if (userOpt == 0) {
				System.exit(0);
			} else {
				System.out.println(invalid);
			}
		} while (!isValid);

	}

	public static void newUser() {
		boolean isValid = false;
		String name = "";
		do {
			name = ConsoleIO.promptForInput("What is the user's name?", false);
			isValid = true;
		} while (!isValid);
		u = new User(name);
		mainMenu();
	}

	public static void makeDates() {
		u.getDates().clear();
		int month = promptForMonth();
		int year = promptForYear();
		for (int i = 1; i < 32; i++) {
			if (month == 2) {
				if (year % 4 == 0) {
					u.getDates().add(new LocalDate(year, month, i));
					if (i > 28) {
						i = 32;
					}
				} else {
					u.getDates().add(new LocalDate(year, month, i));
					if (i > 27) {
						i = 32;
					}
				}
			} else {
				u.getDates().add(new LocalDate(year, month, i));
			}
		}
	}

	public static int promptForMonth() {
		String[] months = { "1. January", "2. February", "3. March", "4. April", "5. May", "6. June", "7. July", "8. August",
				"9. September", "10. October", "11. November", "12. December" };
		boolean isValid = false;
		int month = 0;
		do {
			try {
				month = ConsoleIO.promptForMenuSelection(months, false);
				isValid = true;
			} catch (NumberFormatException nfe) {
				System.out.println(invalid);
			}
		} while (!isValid);
		return month;
	}

	public static int promptForDay(int month, int year) {
		boolean isValid = false;
		int day = 0;
		int max = 0;
		if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
			max = 31;
		} else if (month == 4 || month == 6 || month == 9 || month == 11) {
			max = 30;
		} else {
			if (year % 4 == 0) {
				max = 29;
			} else {
				max = 28;
			}
		}
		do {
			try {
				day = ConsoleIO.promptForInt("What day of the month would you like?", 1, max);
				isValid = true;
			} catch (NumberFormatException nfe) {
				System.out.println(invalid);
			}
		} while (!isValid);
		return day;
	}

	public static int promptForYear() {
		boolean isValid = false;
		int year = 0;
		do {
			try {
				String input = ConsoleIO.promptForInput("What year would you like?", false);
				year = Integer.parseInt(input);
				isValid = true;
			} catch (NumberFormatException nfe) {
				System.out.println(invalid);
			}
		} while (!isValid);
		return year;
	}

	public static int promptForHour() {
		boolean isValid = false;
		int hour = 0;
		do {
			try {
				hour = ConsoleIO.promptForInt("What hour would you like?", 1, 12);
				isValid = true;
			} catch (NumberFormatException nfe) {
				System.out.println(invalid);
			}
		} while (!isValid);
		return hour;
	}

	public static int promptForMinute() {
		boolean isValid = false;
		int minute = 0;
		do {
			try {
				minute = ConsoleIO.promptForInt("What minute would you like?", 1, 60);
				isValid = true;
			} catch (NumberFormatException nfe) {
				System.out.println(invalid);
			}
		} while (!isValid);
		return minute;
	}

	public static boolean isAM() {
		boolean isValid = false;
		boolean isAM = false;
		do {
			try {
				isAM = ConsoleIO.promptForBool("Is this time AM or PM?", "am", "pm");
				isValid = true;
			} catch (NumberFormatException nfe) {
				System.out.println(invalid);
			}
		} while (!isValid);
		return isAM;
	}

	public static void addTask() {
		ArrayList<Task> temp = u.getTaskList();
		String[] options = { "1. Percentage", "2. Checkbox" };
		boolean isValid = false;
		int userOpt = 0;
		do {
			try {
				userOpt = ConsoleIO.promptForMenuSelection(options, false);
			} catch (NumberFormatException nfe) {

			}
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
				temp.add(t);
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
				temp.add(t);
				isValid = true;
			} else {
				System.out.println(invalid);
			}
		} while (!isValid);
		u.setTaskList(temp);
	}

	public static void removeTask() {
		ArrayList<Task> tempList = u.getTaskList();
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
				userOpt = ConsoleIO.promptForInt("Please, choose a task to remove, or enter 0 to go back: ", 0,
						tempList.size());
				isValid = true;
			} while (!isValid);
			if (userOpt == 0) {
				menu();
			}
			tempList.remove(tempList.get(userOpt - 1));
			System.out.println("Task removed.");
		}
		u.setTaskList(tempList);
		menu();
	}

	public static void editTask() {
		ArrayList<Task> tempList = u.getTaskList();
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
				userOpt = ConsoleIO.promptForInt("Please, choose a task to edit, or enter 0 to go back: ", 0,
						tempList.size());
				isValid = true;
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
				String[] options2 = { "1. Name", "2. Start Date", "3. End Date", "4. Completion Status", "5. Description",
						"6. Priority", "7. Location", "8. Recurring Status" };
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
		u.setTaskList(tempList);
		menu();
	}

	public static void savemenu() {
		String[] options = { "1. Save", "2. Switch User", "3. Back to Home" };
		boolean isValid = true;
		while (isValid) {
			int userOpt = ConsoleIO.promptForMenuSelection(options, true);
			if (userOpt == 1) {
				saveSwing();
			} else if (userOpt == 2) {
				justOpened = false;
				loadSwing();
			} else if (userOpt == 3) {
				menu();
			} else if (userOpt == 0) {
				System.exit(0);
			}
		}
	}

	public static void nameTask(Task t) {
		String name = "";
		boolean isValid = false;
		do {
			name = ConsoleIO.promptForInput("What would you like to name the task?", false);
			isValid = true;
		} while (!isValid);
		t.setName(name);
	}

	public static DateTime datePrompt(Task t, boolean isStartDate) {
		int year = 1;
		int month = 1;
		int day = 1;
		int hour = 1;
		int minute = 1;
		DateTime d = new DateTime(year, month, day, hour, minute);
		if (isStartDate) {
			System.out.println("Does the task have a TIME?");
			String[] options = { "1. Yes", "2. No" };
			int choice = ConsoleIO.promptForMenuSelection(options, false);
			if (choice == 1) {
				year = promptForYear();
				month = promptForMonth();
				day = promptForDay(month, year);
				hour = promptForHour();
				minute = promptForMinute();
				t.setTime(true);
				boolean isAM = isAM();
				if (!isAM) {
					hour = hour + 12;
				}
				d = new DateTime(year, month, day, hour, minute);
			} else if (choice == 2) {
				year = promptForYear();
				month = promptForMonth();
				day = promptForDay(month, year);
				t.setTime(false);
				d = new DateTime(year, month, day, 0, 0);
			}
		} else {
			if (t.isTime()) {
				year = promptForYear();
				month = promptForMonth();
				day = promptForDay(month, year);
				hour = promptForHour();
				minute = promptForMinute();
				t.setTime(true);
				boolean isAM = isAM();
				if (!isAM) {
					hour = hour + 12;
				}
				d = new DateTime(year, month, day, hour, minute);
			} else {
				year = promptForYear();
				month = promptForMonth();
				day = promptForDay(month, year);
				t.setTime(false);
				d = new DateTime(year, month, day, 0, 0);
			}
		}

		return d;
	}

	public static void startDate(Task t) {
		System.out.println("START DATE");
		DateTime d = datePrompt(t, true);
		t.setStartDate(d);
	}

	public static void endDate(Task t) {
		System.out.println("END DATE");
		DateTime d = datePrompt(t, false);
		t.setEndDate(d);
	}

	public static void completeTask(Task t) {
		boolean complete = ConsoleIO.promptForBool("Is the task complete? (y/n)", "y", "n");
		if (complete) {
			t.setComplete(true);
			t.setPercentComplete(100);
			System.out.println("TASK COMPLETE!");
			// rewards
			if (t.getPriority() == 1) {
				LootGenerator.generateItem(Rarity.Common, 0, u);
				System.out.println("Earned 5RP");
				u.setRP(u.getRP() + 5);
			} else if (t.getPriority() == 2) {
				LootGenerator.generateItem(Rarity.Common, 40, u);
				System.out.println("Earned 10RP");
				u.setRP(u.getRP() + 10);
			} else if (t.getPriority() == 3) {
				LootGenerator.generateItem(Rarity.Rare, 0, u);
				System.out.println("Earned 15RP");
				u.setRP(u.getRP() + 15);
			} else if (t.getPriority() == 4) {
				LootGenerator.generateItem(Rarity.Rare, 40, u);
				System.out.println("Earned 20RP");
				u.setRP(u.getRP() + 20);
			} else if (t.getPriority() == 5) {
				LootGenerator.generateItem(Rarity.Epic, 0, u);
				System.out.println("Earned 25RP");
				u.setRP(u.getRP() + 25);
			}
		}
	}

	public static void taskPercent(Task t) {
		double percent = 0;
		boolean isValid = true;
		do {
			percent = ConsoleIO.promptForDouble("What percent complete is the task?", 0, 100);
			isValid = true;
		} while (!isValid);
		if (percent == 100) {
			t.setComplete(true);
			t.setPercentComplete(percent);
			System.out.println("TASK COMPLETE!");
			// rewards
			if (t.getPriority() == 1) {
				LootGenerator.generateItem(Rarity.Common, 0, u);
				System.out.println("Earned 5RP");
				u.setRP(u.getRP() + 5);
			} else if (t.getPriority() == 2) {
				LootGenerator.generateItem(Rarity.Common, 40, u);
				System.out.println("Earned 10RP");
				u.setRP(u.getRP() + 10);
			} else if (t.getPriority() == 3) {
				LootGenerator.generateItem(Rarity.Rare, 0, u);
				System.out.println("Earned 15RP");
				u.setRP(u.getRP() + 15);
			} else if (t.getPriority() == 4) {
				LootGenerator.generateItem(Rarity.Rare, 40, u);
				System.out.println("Earned 20RP");
				u.setRP(u.getRP() + 20);
			} else if (t.getPriority() == 5) {
				LootGenerator.generateItem(Rarity.Epic, 0, u);
				System.out.println("Earned 25RP");
				u.setRP(u.getRP() + 25);
			}
		} else {
			t.setPercentComplete(percent);
		}
	}

	public static void describeTask(Task t) {
		String description = "";
		boolean isValid = false;
		do {
			description = ConsoleIO.promptForInput("Please describe " + t.getName(), false);
			isValid = true;
		} while (!isValid);
		t.setDescription(description);
	}

	public static void taskPriority(Task t) {
		int priority = 1;
		boolean isValid = false;
		do {
			priority = ConsoleIO.promptForInt("What is the priority of the task?", 1, 5);
			isValid = true;
		} while (!isValid);
		t.setPriority(priority);
	}

	public static void taskLocation(Task t) {
		String location = "";
		boolean isValid = false;
		do {
			location = ConsoleIO.promptForInput("Where is the task taking place?", false);
			isValid = true;
		} while (!isValid);
		t.setLocation(location);
	}

	public static void taskRecurring(Task t) {
		boolean isRecurring = ConsoleIO.promptForBool("Is the task recurring? (y/n)", "y", "n");
		if (isRecurring) {
			System.out.println("How often is the task recurring?");
			String[] options = { "1. Daily", "2. Weekly", "3. Monthly", "4. Yearly" };
			int userOpt = ConsoleIO.promptForMenuSelection(options, false);
			if (userOpt == 1) {
				t.setRecurringDaily(true);
				t.setRecurringWeekly(false);
				t.setRecurringMonthly(false);
				t.setRecurringYearly(false);
				int it = 1;
				it = ConsoleIO.promptForInt("How many more times does it recur?", 1, 200);
				for (int i = 1; i <= it; i++) {
					Task e = new Task();
					e.setComplete(false);
					e.setDescription(t.getDescription());
					DateTime end = new DateTime(t.getEndDate().getYear(), t.getEndDate().getMonthOfYear(),
							t.getEndDate().getDayOfMonth() + i, t.getEndDate().getHourOfDay(),
							t.getEndDate().getMinuteOfHour());
					e.setEndDate(end);
					DateTime start = new DateTime(t.getStartDate().getYear(), t.getStartDate().getMonthOfYear(),
							t.getStartDate().getDayOfMonth() + i, t.getStartDate().getHourOfDay(),
							t.getStartDate().getMinuteOfHour());
					e.setStartDate(start);
					e.setLocation(t.getLocation());
					e.setName(t.getName());
					e.setPercent(t.isPercent());
					e.setPercentComplete(t.getPercentComplete());
					e.setPriority(t.getPriority());
					e.setTime(t.isTime());
					e.setRecurringDaily(true);
					e.setRecurringMonthly(false);
					e.setRecurringWeekly(false);
					e.setRecurringYearly(false);
					u.getTaskList().add(e);
				}
			} else if (userOpt == 2) {
				t.setRecurringDaily(false);
				t.setRecurringWeekly(true);
				t.setRecurringMonthly(false);
				t.setRecurringYearly(false);
				int it2 = 1;
				it2 = ConsoleIO.promptForInt("How many more times does it recur?", 1, 200);
				for (int i = 1; i <= it2; i++) {
					if (i % 7 == 0) {
						Task e = new Task();
						e.setComplete(false);
						e.setDescription(t.getDescription());
						DateTime end = new DateTime(t.getEndDate().getYear(), t.getEndDate().getMonthOfYear(),
								t.getEndDate().getDayOfMonth() + i, t.getEndDate().getHourOfDay(),
								t.getEndDate().getMinuteOfHour());
						e.setEndDate(end);
						DateTime start = new DateTime(t.getStartDate().getYear(), t.getStartDate().getMonthOfYear(),
								t.getStartDate().getDayOfMonth() + i, t.getStartDate().getHourOfDay(),
								t.getStartDate().getMinuteOfHour());
						e.setStartDate(start);
						e.setLocation(t.getLocation());
						e.setName(t.getName());
						e.setPercent(t.isPercent());
						e.setPercentComplete(t.getPercentComplete());
						e.setPriority(t.getPriority());
						e.setTime(t.isTime());
						e.setRecurringDaily(false);
						e.setRecurringMonthly(false);
						e.setRecurringWeekly(true);
						e.setRecurringYearly(false);
						u.getTaskList().add(e);
					}
				}
			} else if (userOpt == 3) {
				t.setRecurringDaily(false);
				t.setRecurringWeekly(false);
				t.setRecurringMonthly(true);
				t.setRecurringYearly(false);
				int it3 = 1;
				it3 = ConsoleIO.promptForInt("How many more times does it recur?", 1, 200);
				for (int i = 1; i <= it3; i++) {
					Task e = new Task();
					e.setComplete(false);
					e.setDescription(t.getDescription());
					DateTime end = new DateTime(t.getEndDate().getYear(), t.getEndDate().getMonthOfYear() + i,
							t.getEndDate().getDayOfMonth(), t.getEndDate().getHourOfDay(),
							t.getEndDate().getMinuteOfHour());
					e.setEndDate(end);
					DateTime start = new DateTime(t.getStartDate().getYear(), t.getStartDate().getMonthOfYear() + i,
							t.getStartDate().getDayOfMonth(), t.getStartDate().getHourOfDay(),
							t.getStartDate().getMinuteOfHour());
					e.setStartDate(start);
					e.setLocation(t.getLocation());
					e.setName(t.getName());
					e.setPercent(t.isPercent());
					e.setPercentComplete(t.getPercentComplete());
					e.setPriority(t.getPriority());
					e.setTime(t.isTime());
					e.setRecurringDaily(false);
					e.setRecurringMonthly(false);
					e.setRecurringWeekly(true);
					e.setRecurringYearly(false);
					u.getTaskList().add(e);
				}
			} else if (userOpt == 4) {
				t.setRecurringDaily(false);
				t.setRecurringWeekly(false);
				t.setRecurringMonthly(false);
				t.setRecurringYearly(true);
				int it4 = 1;
				it4 = ConsoleIO.promptForInt("How many more times does it recur?", 1, 200);
				for (int i = 1; i <= it4; i++) {
					Task e = new Task();
					e.setComplete(false);
					e.setDescription(t.getDescription());
					DateTime end = new DateTime(t.getEndDate().getYear() + i, t.getEndDate().getMonthOfYear(),
							t.getEndDate().getDayOfMonth(), t.getEndDate().getHourOfDay(),
							t.getEndDate().getMinuteOfHour());
					e.setEndDate(end);
					DateTime start = new DateTime(t.getStartDate().getYear() + i, t.getStartDate().getMonthOfYear(),
							t.getStartDate().getDayOfMonth(), t.getStartDate().getHourOfDay(),
							t.getStartDate().getMinuteOfHour());
					e.setStartDate(start);
					e.setLocation(t.getLocation());
					e.setName(t.getName());
					e.setPercent(t.isPercent());
					e.setPercentComplete(t.getPercentComplete());
					e.setPriority(t.getPriority());
					e.setTime(t.isTime());
					e.setRecurringDaily(false);
					e.setRecurringMonthly(false);
					e.setRecurringWeekly(false);
					e.setRecurringYearly(true);
					u.getTaskList().add(e);
				}
			} else {
				System.out.println(invalid);
			}

		}
	}

	public static void printTasks() {
		if (u.getTaskList().isEmpty()) {
			System.out.println("There aren't any tasks, fool!");
		} else {
			for (Task t : u.getTaskList()) {
				System.out.println(t.toString());
			}
		}
	}

	public static void printCalendar() {
		for (LocalDate date : u.getDates()) {
			System.out.println(date.toString());
		}
	}

	public static void saveSerialize() {
		String filepath = "";
		boolean isValid = false;
		while (!isValid) {
			filepath = ConsoleIO.promptForInput("What is the folder you'd like to save to?", false);
			isValid = true;
		}
		StringBuilder sb = new StringBuilder();
		sb.append(filepath + "/" + u.getName());
		String fileName = sb.toString();
		boolean isValid2 = false;
		while (!isValid2) {
			try {
				FileOutputStream fileOut = new FileOutputStream(fileName);
				ObjectOutputStream out = new ObjectOutputStream(fileOut);
				out.writeObject(u);
				out.close();
				fileOut.close();
				isValid2 = true;
			} catch (IOException i) {
				System.out.println(invalid);
			}
		}
	}

	public static void loadDeserialize() {
		u = null;
		String filepath = "";
		boolean isValid = false;
		while (!isValid) {
			filepath = ConsoleIO.promptForInput("What is the folder you'd like to load from?", false);
			isValid = true;
		}

		// int counter = 1;
		// File[] files = new File(filepath).listFiles();
		//
		// for (File file : files) {
		// if (file.isFile()) {
		// System.out.println(counter + ". " + file.getName());
		// }
		// counter += 1;
		// }
		// boolean isValid2 = false;
		// int user = 0;
		// do {
		// try {
		// user = ConsoleIO.promptForInt("Which user would you like to load?",
		// 1, counter);
		// isValid2 = true;
		// } catch (IOException e) {
		// System.out.println(invalid);
		// }
		// } while (!isValid2);
		// String userName = files[user].getName();
		boolean notvalid = true;
		String userName = "";
		do {
			userName = ConsoleIO.promptForInput("What is the name of the user you'd like to load?", false);
			notvalid = false;
		} while (notvalid);
		boolean loading = true;
		StringBuilder sb = new StringBuilder();
		sb.append(filepath + "/" + userName);
		String fileName = sb.toString();
		while (loading) {
			try {
				FileInputStream fileIn = new FileInputStream(fileName);
				ObjectInputStream in = new ObjectInputStream(fileIn);
				u = (User) in.readObject();
				in.close();
				fileIn.close();
				loading = false;
			} catch (IOException i) {
				System.out.println(invalid);
			} catch (ClassNotFoundException c) {
				System.out.println(invalid);
			}
		}
		mainMenu();
	}

	public static void loadSwing() {
		boolean loading = true;
		while (loading) {
			try {
				final JFrame jFrame;
				jFrame = new JFrame();
				jFrame.setVisible(true);
				jFrame.setExtendedState(JFrame.ICONIFIED);
				jFrame.setExtendedState(JFrame.NORMAL);
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setDialogTitle("Open File");
				fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				fileChooser.setPreferredSize(new Dimension(800, 600));
				fileChooser.setVisible(true);
				if (fileChooser.showOpenDialog(jFrame) == JFileChooser.APPROVE_OPTION) {

					// load from file
				}
				String file = fileChooser.getSelectedFile().getAbsolutePath();

				FileInputStream fileIn = new FileInputStream(file);
				ObjectInputStream in = new ObjectInputStream(fileIn);
				u = (User) in.readObject();
				in.close();
				fileIn.close();
				jFrame.dispose();
				loading = false;
			} catch (IOException i) {
				System.out.println(invalid);
			} catch (ClassNotFoundException c) {
				System.out.println(invalid);
			} catch (NullPointerException n) {
				if (justOpened) {
					newMenu();
				} else {
					savemenu();
				}
			}
		}
		mainMenu();
	}

	public static void saveSwing() {
		boolean isValid2 = false;
		while (!isValid2) {
			try {
				final JFrame jFrame;
				jFrame = new JFrame();
				jFrame.setVisible(true);
				jFrame.setExtendedState(JFrame.ICONIFIED);
				jFrame.setExtendedState(JFrame.NORMAL);
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setDialogTitle("Save File");
				fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				fileChooser.setPreferredSize(new Dimension(800, 600));
				fileChooser.setVisible(true);
				if (fileChooser.showSaveDialog(jFrame) == JFileChooser.APPROVE_OPTION) {
					// load from file
				}
				String file = fileChooser.getSelectedFile().getAbsolutePath();
				FileOutputStream fileOut = new FileOutputStream(file);
				ObjectOutputStream out = new ObjectOutputStream(fileOut);
				out.writeObject(u);
				out.close();
				fileOut.close();
				jFrame.dispose();
				System.out.println("Save Successful.");
				isValid2 = true;
			} catch (IOException i) {
				System.out.println(invalid);
			} catch (NullPointerException n) {
				if (justOpened) {
					newMenu();
				} else {
					savemenu();
				}
			}
		}
	}

	public static void RPGMenu() {
		String[] options = { "1. Visit Shop", "2. Battle", "3. View Stats", "4. View Inventory", "5. Equip Items",
				"6. Return to Main Menu" };
		boolean isValid = false;
		int userOpt = 0;
		do {
			try {
				userOpt = ConsoleIO.promptForMenuSelection(options, false);
			} catch (NumberFormatException nfe) {
				System.out.println(invalid);
				menu();
			}
			if (userOpt == 1) {
				Shopkeep.runShop(u);
				RPGMenu();
				isValid = true;
			} else if (userOpt == 2) {
				battleMenu();
				isValid = true;
			} else if (userOpt == 3) {
				System.out.println(u.toString());
			} else if (userOpt == 4) {
				u.showInventory();
			} else if (userOpt == 5) {
				equipMenu();
			} else if (userOpt == 6) {
				mainMenu();
			} else {
				System.out.println(invalid);
			}
		} while (!isValid);
	}

	public static void battleMenu() {
		String[] options = { "1. Start Battling", "2. Go Back" };
		int a = ConsoleIO.promptForMenuSelection(options, false);
		if (a == 1) {
			BattleMenu.menu(u);
		} else if (a == 2) {
			RPGMenu();
		}
	}

	public static void equipMenu() {
		String[] options = { "1. Equip Armor", "2. Equip Weapon", "3. Equip Accessory", "4. Drink Potion", "5. Go Back" };
		int a = ConsoleIO.promptForMenuSelection(options, false);
		if (a == 1) {
			// equip armor
			System.out.println("Select armor to equip: ");
			int i = 0;
			u.showInventory();
			boolean isValid = false;
			do {
				try {
					i = ConsoleIO.promptForInt(
							"Select an item number corresponding to the items above or press 0 to go back.", 0,
							u.getInventory().size());
					if (i == 0) {
						equipMenu();
					} else {
						Armor select = (Armor) u.getInventory().get(i - 1);
						u.setEquippedArmor(select);
					}
					isValid = true;
				} catch (ClassCastException c) {
					System.out.println("That isn't armor!");
				}
			} while (!isValid);
		} else if (a == 2) {
			// equip weapon
			System.out.println("Select a weapon to equip: ");
			int j = 0;
			u.showInventory();
			boolean valid = false;
			do {
				try {
					j = ConsoleIO.promptForInt(
							"Select an item number corresponding to the items above or press 0 to go back.", 0,
							u.getInventory().size());
					if (j == 0) {
						equipMenu();
					} else {
						Weapon select2 = (Weapon) u.getInventory().get(j - 1);
						u.setEquippedWeapon(select2);
					}
					valid = true;
				} catch (ClassCastException c) {
					System.out.println("That isn't a weapon!");
				}
			} while (!valid);
		} else if (a == 3) {
			// equip accessory
			System.out.println("Select an accessory to equip: ");
			int j = 0;
			u.showInventory();
			boolean valid = false;
			do {
				try {
					j = ConsoleIO.promptForInt(
							"Select an item number corresponding to the items above or press 0 to go back.", 0,
							u.getInventory().size());
					if (j == 0) {
						equipMenu();
					} else {
						Accessory select2 = (Accessory) u.getInventory().get(j - 1);
						u.setEquippedAccessory(select2);
					}
					valid = true;
				} catch (ClassCastException c) {
					System.out.println("That isn't an accessory!");
				}
			} while (!valid);
		} else if (a == 4) {
			potionPick();
		} else if (a == 5) {
			RPGMenu();
		}
	}

	public static void potionPick() {
		ArrayList<Item> inventory = u.getInventory();
		ArrayList<Potion> potions = new ArrayList<>();
		for (Item i : inventory) {
			try {
				potions.add((Potion) i);
			} catch (ClassCastException c) {
			}
		}
		int j = 0;
		for (Potion p : potions) {
			System.out.println(p.toString());
			System.out.println();
		}
		j = ConsoleIO.promptForInt("Select an item number corresponding to the items above or press 0 to go back.",
				0, u.getInventory().size());
		if (j == 0) {
			equipMenu();
		} else {
			Potion select = potions.get(j - 1);
			select.use(u);
			inventory.remove(select);
			System.out.println("You drank the potion and it raised your HP by " + select.getNum() + "!");
			System.out.println("Your current HP is " + u.getCurrentHP() + "/" + u.getBaseHP());
		}
	}

}
