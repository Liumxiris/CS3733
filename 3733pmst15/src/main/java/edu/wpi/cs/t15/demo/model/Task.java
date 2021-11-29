package edu.wpi.cs.t15.demo.model;

import java.util.LinkedList;

public class Task {
	private String name; 
	private String userID; 
	private String taskID; 
	private String projectID; 
	private boolean status; 
	private boolean isBottom; 
	private LinkedList<Task> subtasks; 
	private static int idCounter = 0;
	
	public Task(String name, String projectID, String userID, boolean isBottom) {
		this.name = name; 
		this.taskID = "t" + idConverter(idCounter);
		this.projectID = projectID; 
		if(isBottom) 
			this.userID = userID; 
		else
			this.userID = null; 
		this.status = false; 
		this.subtasks = new LinkedList<Task>(); 
		
		idCounter++; 
	}
	
	
	public Task(String name, String projectID, String userID, String parentTaskID, boolean isBottom) {
		this.name = name; 
		this.taskID = "t" + idConverter(idCounter);
		this.projectID = projectID; 
		if(isBottom) 
			this.userID = userID; 
		else
			this.userID = null; 
		this.status = false; 
		this.subtasks = new LinkedList<Task>(); 
		
		idCounter++;
	}
	
    public String idConverter(int id){
        String s = String.format("%03d", id);
        return s;
    }
	
	public String getName() {
		return name;
	}
	
	public String getUserID() {
		return userID; 
	}
	
	public boolean getIsBottom() {
		return isBottom; 
	}
	
	public String getTaskID() {
		return taskID;
	}
	
	public String getProjectID() {
		return projectID;
	}
}
