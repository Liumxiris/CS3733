package edu.wpi.cs.t15.demo.model;

import java.util.LinkedList;

public class Project {
	private String title; 
	private LinkedList<Task> tasks; 
	private String teamID; 
	private String projectID; 
	private int status; 
	private boolean isArchived;
	private static int idCounter = 0; 
	
	public Project(String title) {
		this.title = title; 
//		this.teamID = teamID; 
		this.projectID = "u" + idConverter(idCounter);
		this.tasks = new LinkedList<Task>(); 
		this.isArchived = false; 
		this.status = 0; 
		
		idCounter++; 
	}
	
//	public Project(String title, String teamID) {
//		this.title = title; 
//		this.teamID = teamID; 
//		this.projectID = "u" + idConverter(idCounter);
//		this.tasks = new LinkedList<Task>(); 
//		this.isArchived = false; 
//		this.status = 0; 
//		
//		idCounter++; 
//	}
	
	public String idConverter(int id){
        String s = String.format("%03d", id);
        return s;
    }
	
	public String getTitle() {
		return title; 
	}
	
//	public int getProjID() {
//		return projectID; 
//	}

}
