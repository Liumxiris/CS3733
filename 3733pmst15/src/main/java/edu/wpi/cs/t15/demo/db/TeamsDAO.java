package edu.wpi.cs.t15.demo.db;

import edu.wpi.cs.t15.demo.model.Task;
import edu.wpi.cs.t15.demo.model.Team;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TeamsDAO {
	Connection conn;
	final String tblName = "Projects";
	
	public TeamsDAO() {
		try {
			conn = DatabaseUtil.connect();
			
		} catch (Exception e) {
			conn = null;
		} 
	}
	
	public Team getUser(int id) throws Exception{
		try {
			Team team = null; 
			
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE name=?;");
            ps.setInt(1,  id);
            ResultSet resultSet = ps.executeQuery();
            
            while (resultSet.next()) {
            	team = generateTeam(resultSet);
            }
            resultSet.close();
            ps.close();
            
            return team;

		}catch(Exception e) {
			e.printStackTrace();
			throw new Exception("Failed to get team: " + e.getMessage());
		}
	}
	
	public boolean addTeam(Team team) throws Exception {
        try {
	           PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE name = ?;");
	           ps.setString(1, team.getName());
	           ResultSet resultSet = ps.executeQuery();
	            
	           // already present?
	           while (resultSet.next()) {
	        	   Team c = generateTeam(resultSet);
	               resultSet.close();
	               return false;
	           }

	           ps = conn.prepareStatement("INSERT INTO " + tblName + " (name, projectID) teams(?,?);");
	           ps.setString(1,  team.getName());
	           ps.setString(2,  team.getProjectID());
	           ps.execute();
	           return true;

	     } catch (Exception e) {
	           throw new Exception("Failed to insert team: " + e.getMessage());
	     }
	}
	
	private Team generateTeam(ResultSet resultSet) throws Exception{
		String name = resultSet.getString(1);
		String id = resultSet.getString(2);
		
		return new Team(name, id); 
		
	}
	
	
}


