package edu.wpi.cs.t15.demo.db;

import edu.wpi.cs.t15.demo.model.Project;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProjectsDAO {
	Connection conn;
	final String tblName = "Projects";
	
	public ProjectsDAO() {
		try {
			conn = DatabaseUtil.connect();
			
		} catch (Exception e) {
			conn = null;
		} 
	}
	
	public Project getProject(String name) throws Exception{
		try {
			Project proj = null; 
			
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE name=?;");
            ps.setString(1,  name);
            ResultSet resultSet = ps.executeQuery();
            
            while (resultSet.next()) {
                proj = generateProject(resultSet);
            }
            resultSet.close();
            ps.close();
            
            return proj;

		}catch(Exception e) {
			e.printStackTrace();
			throw new Exception("Failed to get project: " + e.getMessage());
		}
	}
    
    public boolean addProject(Project proj) throws Exception {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE name = ?;");
            ps.setString(1, proj.getTitle());
            ResultSet resultSet = ps.executeQuery();
            
            // already present?
            while (resultSet.next()) {
            	Project c = generateProject(resultSet);
                resultSet.close();
                return false;
            }

            ps = conn.prepareStatement("INSERT INTO " + tblName + " (name) project(?);");
            ps.setString(1,  proj.getTitle());
            ps.execute();
            return true;

        } catch (Exception e) {
            throw new Exception("Failed to insert project: " + e.getMessage());
        }
    }
	
	private Project generateProject(ResultSet resultSet) throws Exception{
		String name = resultSet.getString(1);
		
		return new Project(name); 
		
	}
	
	
}


