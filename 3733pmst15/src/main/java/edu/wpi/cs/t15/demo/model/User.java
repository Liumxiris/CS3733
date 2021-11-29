package edu.wpi.cs.t15.demo.model;

import java.util.LinkedList;

public class User {
	private String name; 
	private String userID; 
	private String teamID;
	private LinkedList<Task> tasks; 
	private static int idCounter = 0; 
	
	public User(String name, String teamID) {
		this.name = name; 
		this.teamID = teamID; 
		this.userID = "u" + idConverter(idCounter);
		this.tasks = new LinkedList<Task>(); 
		
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

	public String getTeamID() {
		return teamID;
	}
}
