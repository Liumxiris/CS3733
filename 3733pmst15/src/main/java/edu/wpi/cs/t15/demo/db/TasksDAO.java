package edu.wpi.cs.t15.demo.db;

import edu.wpi.cs.t15.demo.model.Task;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TasksDAO {
	Connection conn;
	final String tblName = "Projects";
	
	public TasksDAO() {
		try {
			conn = DatabaseUtil.connect();
			
		} catch (Exception e) {
			conn = null;
		} 
	}
	
	public Task getTask(int id) throws Exception{
		try {
			Task task = null; 
			
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE name=?;");
            ps.setInt(1,  id);
            ResultSet resultSet = ps.executeQuery();
            
            while (resultSet.next()) {
                task = generateTask(resultSet);
            }
            resultSet.close();
            ps.close();
            
            return task;

		}catch(Exception e) {
			e.printStackTrace();
			throw new Exception("Failed to get task: " + e.getMessage());
		}
	}
	
	public boolean addTask(Task task) throws Exception {
        try {
	           PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE name = ?;");
	           ps.setString(1, task.getName());
	           ResultSet resultSet = ps.executeQuery();
	            
	           // already present? 
	           while (resultSet.next()) {
	        	   Task c = generateTask(resultSet);
	               resultSet.close();
	               return false;
	           }

	           ps = conn.prepareStatement("INSERT INTO " + tblName + " (name, userID, projectID, isSubtask) tasks(?,?,?,?);");
	           ps.setString(1,  task.getName());
	           ps.setString(2,  task.getUserID());
	           ps.setString(3,  task.getProjectID());
	           ps.setBoolean(4,  task.getIsBottom());
	           ps.execute();
	           return true;

	     } catch (Exception e) {
	           throw new Exception("Failed to insert task: " + e.getMessage());
	     }
	}
	
	private Task generateTask(ResultSet resultSet) throws Exception{
		String name = resultSet.getString(1);
		String user = resultSet.getString(2);
		String id = resultSet.getString(3);
		boolean iS = resultSet.getBoolean(4);
		
		return new Task(name, user, id, iS); 
		
	}
	
	
}


