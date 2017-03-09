package models;

import java.io.Serializable;

import org.joda.time.DateTime;

public class Task implements Comparable<Task>{
	
	private double percentComplete = 0;
	private boolean isComplete = false;
	private DateTime startDate = new DateTime();
	private DateTime endDate = new DateTime();
	private String description = "[NULL]";
	private String name = "[NULL]";
	private int priority = 1;
	private String location = "[NULL]";
	private boolean isRecurringDaily = false;
	private boolean isRecurringWeekly = false;
	private boolean isRecurringMonthly = false;
	private boolean isPercent = false;
	private boolean isTime = false;

	public Task() {

	}

	public Task(DateTime startDate, DateTime endDate, String description, String name, int priority, String location,
			boolean isRecurringDaily, boolean isRecurringWeekly, boolean isRecurringMonthly) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.description = description;
		this.name = name;
		this.priority = priority;
		this.location = location;
		this.isRecurringDaily = isRecurringDaily;
		this.isRecurringWeekly = isRecurringWeekly;
		this.isRecurringMonthly = isRecurringMonthly;
	}

	public double getPercentComplete() {
		return percentComplete;
	}

	public void setPercentComplete(double percentComplete) {
		if (percentComplete < 101) {
			this.percentComplete = percentComplete;
		}
	}

	public boolean isComplete() {
		return isComplete;
	}

	public void setComplete(boolean isComplete) {
		this.isComplete = isComplete;
	}

	public DateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(DateTime startDate) {
		this.startDate = startDate;
	}

	public DateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(DateTime endDate) {
		this.endDate = endDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		if (priority < 6) {
			this.priority = priority;
		}
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public boolean isRecurringDaily() {
		return isRecurringDaily;
	}

	public void setRecurringDaily(boolean isRecurringDaily) {
		this.isRecurringDaily = isRecurringDaily;
	}

	public boolean isRecurringWeekly() {
		return isRecurringWeekly;
	}

	public void setRecurringWeekly(boolean isRecurringWeekly) {
		this.isRecurringWeekly = isRecurringWeekly;
	}

	public boolean isRecurringMonthly() {
		return isRecurringMonthly;
	}

	public void setRecurringMonthly(boolean isRecurringMonthly) {
		this.isRecurringMonthly = isRecurringMonthly;
	}

	@Override
	public int compareTo(Task t) {
		return this.getStartDate().compareTo(t.getStartDate());
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Task: " + getName());
		int month = getStartDate().getMonthOfYear();
		String monthName = "";
		switch (month) {
		case 1:
			monthName = "January";
			break;
		case 2:
			monthName = "February";
			break;
		case 3:
			monthName = "March";
			break;
		case 4:
			monthName = "April";
			break;
		case 5:
			monthName = "May";
			break;
		case 6:
			monthName = "June";
			break;
		case 7:
			monthName = "July";
			break;
		case 8:
			monthName = "August";
			break;
		case 9:
			monthName = "September";
			break;
		case 10:
			monthName = "October";
			break;
		case 11:
			monthName = "November";
			break;
		case 12:
			monthName = "December";
			break;
		}
		if (!isTime) {
			sb.append("\n\tStarts: " + monthName + " " + getStartDate().getDayOfMonth() + ", "
					+ getStartDate().getYear());
		} else {
			if (getStartDate().getHourOfDay() > 12) {
				int startHour = getStartDate().getHourOfDay() - 12;
				sb.append("\n\tStarts: " + monthName + " " + getStartDate().getDayOfMonth() + ", "
						+ getStartDate().getYear() + " at " + startHour + ":" + getStartDate().getMinuteOfHour()
						+ "pm");
			} else {
				sb.append("\n\tStarts: " + monthName + " " + getStartDate().getDayOfMonth() + ", "
						+ getStartDate().getYear() + " at " + getStartDate().getHourOfDay() + ":"
						+ getStartDate().getMinuteOfHour() + "am");
			}
		}
		int month2 = getEndDate().getDayOfMonth();
		switch (month2) {
		case 1:
			monthName = "January";
			break;
		case 2:
			monthName = "February";
			break;
		case 3:
			monthName = "March";
			break;
		case 4:
			monthName = "April";
			break;
		case 5:
			monthName = "May";
			break;
		case 6:
			monthName = "June";
			break;
		case 7:
			monthName = "July";
			break;
		case 8:
			monthName = "August";
			break;
		case 9:
			monthName = "September";
			break;
		case 10:
			monthName = "October";
			break;
		case 11:
			monthName = "November";
			break;
		case 12:
			monthName = "December";
			break;
		}
		if (!isTime) {
			sb.append("\n\tEnds: " + monthName + " " + getEndDate().getDayOfMonth() + ", " + getEndDate().getYear());
		} else {
			if (getEndDate().getHourOfDay() > 12) {
				int endHour = getEndDate().getHourOfDay() - 12;
				sb.append("\n\tEnds: " + monthName + " " + getEndDate().getDayOfMonth() + ", " + getEndDate().getYear()
						+ " at " + endHour + ":" + getEndDate().getMinuteOfHour() + "pm");
			} else {
				sb.append("\n\tEnds: " + monthName + " " + getEndDate().getDayOfMonth() + ", " + getEndDate().getYear()
						+ " at " + getEndDate().getHourOfDay() + ":" + getEndDate().getMinuteOfHour() + "am");
			}
		}
		if (isPercent()) {
			sb.append("\n\tPercent Complete: " + getPercentComplete() + "%");
		} else if (!isPercent()) {
			if (isComplete()) {
				sb.append("\n\tStatus: Complete");
			} else {
				sb.append("\n\tStatus: Incomplete");
			}
		}
		sb.append("\n\tDescription: " + getDescription());
		sb.append("\n\tPriority: " + getPriority());
		sb.append("\n\tLocation: " + getLocation());
		if (isRecurringDaily()) {
			sb.append("\n\tRecurring Daily");
		} else if (isRecurringWeekly()) {
			sb.append("\n\tRecurring Weekly");
		} else if (isRecurringMonthly()) {
			sb.append("\n\tRecurring Monthly");
		}
		return sb.toString();
	}

	public boolean isPercent() {
		return isPercent;
	}

	public void setPercent(boolean isPercent) {
		this.isPercent = isPercent;
	}

	public boolean isTime() {
		return isTime;
	}

	public void setTime(boolean isTime) {
		this.isTime = isTime;
	}

}