package edu.wpi.cs.t15.demo.http;

import edu.wpi.cs.t15.demo.model.Project;

public class CreateProjectRequest {
	
	String name; 
	Project proj;
	public boolean system;
	
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	
	public boolean getSystem() { return system; }
	public void setSystem(boolean system) { this.system = system; }
	
	public String toString() {
		return "Creating project: " + name;
	}
	
	public CreateProjectRequest() {}
	
	public CreateProjectRequest(String name) {
		this.proj = new Project(name);
	}
	
}
