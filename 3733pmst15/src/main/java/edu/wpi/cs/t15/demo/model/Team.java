package edu.wpi.cs.t15.demo.model;

import java.util.LinkedList;

public class Team {
	private String name; 
	private String teamID; 
	private String projectID; 
	private LinkedList<User> users; 
	private static int idCounter = 0; 
	
	public Team(String name, String projectID) {
		this.name = name; 
		this.projectID = projectID; 
		this.teamID = "m" + idConverter(idCounter);
		this.users = new LinkedList<User>();
		
		idCounter++; 
	}
	
    public String idConverter(int id){
        String s = String.format("%03d", id);
        return s;
    }
	
	public String getName() {
		return name;
	}
	
	public String getTeamID() {
		return teamID;
	}
	
	public String getProjectID() {
		return projectID;
	}

}
