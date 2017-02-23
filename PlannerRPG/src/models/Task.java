package models;

import java.util.Date;

public class Task implements Comparable<Task>{
	private double percentComplete = 0;
	private boolean isComplete = false;
	private Date startDate = new Date();
	private Date endDate = new Date();
	private String description =  "[NULL]";
	private String[] rewards = new String[2];
	private String name = "[NULL]";
	private int priority = 1;
	private String location = "[NULL]";
	private boolean isRecurringDaily = false;
	private boolean isRecurringWeekly = false;
	private boolean isRecurringMonthly = false;
	
	
	public Task() {
		
	}
	
	public Task(double percentComplete, boolean isComplete, Date startDate, Date endDate, String description,
			String[] rewards, String name, int priority, String location, boolean isRecurringDaily,
			boolean isRecurringWeekly, boolean isRecurringMonthly) {
		this.percentComplete = percentComplete;
		this.isComplete = isComplete;
		this.startDate = startDate;
		this.endDate = endDate;
		this.description = description;
		this.rewards = rewards;
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
		this.percentComplete = percentComplete;
	}
	public boolean isComplete() {
		return isComplete;
	}
	public void setComplete(boolean isComplete) {
		this.isComplete = isComplete;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String[] getRewards() {
		return rewards;
	}
	public void setRewards(String[] rewards) {
		this.rewards = rewards;
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
		this.priority = priority;
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
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("Task: " + getName() + "(Starts on " + getStartDate() + ")");
		return sb.toString();
	}
	
}