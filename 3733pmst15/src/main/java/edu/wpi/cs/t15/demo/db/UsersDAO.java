package edu.wpi.cs.t15.demo.db;

import edu.wpi.cs.t15.demo.model.Team;
import edu.wpi.cs.t15.demo.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UsersDAO {
	Connection conn;
	final String tblName = "Projects";
	
	public UsersDAO() {
		try {
			conn = DatabaseUtil.connect();
			
		} catch (Exception e) {
			conn = null;
		} 
	}
	
	public User getUser(int id) throws Exception{
		try {
			User user = null; 
			
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE name=?;");
            ps.setInt(1,  id);
            ResultSet resultSet = ps.executeQuery();
            
            while (resultSet.next()) {
            	user = generateUser(resultSet);
            }
            resultSet.close();
            ps.close();
            
            return user;

		}catch(Exception e) {
			e.printStackTrace();
			throw new Exception("Failed to get user: " + e.getMessage());
		}
	}
	
	public boolean addUser(User user) throws Exception {
        try {
	           PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE name = ?;");
	           ps.setString(1, user.getName());
	           ResultSet resultSet = ps.executeQuery();
	            
	           // already present?
	           while (resultSet.next()) {
	        	   User c = generateUser(resultSet);
	               resultSet.close();
	               return false;
	           }

	           ps = conn.prepareStatement("INSERT INTO " + tblName + " (name, teamID) users(?,?);");
	           ps.setString(1,  user.getName());
	           ps.setString(2,  user.getTeamID());
	           ps.execute();
	           return true;

	     } catch (Exception e) {
	           throw new Exception("Failed to insert user: " + e.getMessage());
	     }
	}
	
	private User generateUser(ResultSet resultSet) throws Exception{
		String name = resultSet.getString(1);
		String id = resultSet.getString(2);
		
		return new User(name, id); 
		
	}
	
	
}


