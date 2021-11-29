package edu.wpi.cs.t15.demo.http;

public class CreateProjectResponse {
	public int statusCode; 
	public String response; 

	
	public CreateProjectResponse(String name) {
		this.statusCode = 200; 
		this.response = name; 
	}
	
	public CreateProjectResponse(String errorMessage, int statusCode) {
		this.statusCode = statusCode; 
		this.response = errorMessage; 
	}
	
	public String toString() {
		if(statusCode / 100 == 2) {
			return "Project Created"; 
		}else {
			return "ErrorResult( " + statusCode + ", err = " + response + ")"; 
		}
	}
	
	

}
