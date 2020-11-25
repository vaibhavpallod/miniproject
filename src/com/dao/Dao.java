package com.dao;
import java.sql.*;
import java.util.ArrayList;

import com.user.Achievement;
import com.user.Internship;
import com.user.User;

public class Dao {
	
	private String passwordTable = "password";
	private String studentDetailsTable = "studentdetails";
	private String achievementTable = "achievement";
	private String internshipTable = "internship";
	private String departmentTable = "department";
	
	public boolean checkIdAndPassword(String id,String password) {
		boolean isCorrect=false;
		try {
			Connection con = ConnectionProvider.getConnection();
			
		    String q = "SELECT * FROM "+passwordTable+" where "+passwordTable+".id = ? and "+passwordTable+".pw=?";
		    
		    PreparedStatement pstmt = con.prepareStatement(q);

            pstmt.setString(1, id);
            pstmt.setString(2, password);

		    ResultSet result = pstmt.executeQuery();
		    isCorrect = result.next();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return isCorrect;
	}
	
	public void setPassword(String id,String newPassword) {
		try {
			Connection con = ConnectionProvider.getConnection();
			
		    String q = "UPDATE "+passwordTable+" set "+passwordTable+".pw=? where "+passwordTable+".id = ?";
		    
		    PreparedStatement pstmt = con.prepareStatement(q);

		    pstmt.setString(1, newPassword);
            pstmt.setString(2, id);

		    pstmt.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
    
	public String getName(String id) {
		String name=null;
		try {
			Connection con = ConnectionProvider.getConnection();
		    String q = "SELECT name * FROM "+studentDetailsTable+" where "+studentDetailsTable+".userid = ?";
		    
		    PreparedStatement pstmt = con.prepareStatement(q);

            pstmt.setString(1, id);
		    
		    ResultSet result = pstmt.executeQuery();
		    
		    if(result.next()) {
		    	name=result.getString(1);
		    }
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return name;
	}
	
	public User getUser(String id) {
		User user=new User();
		try {
			Connection con = ConnectionProvider.getConnection();
		    String q = "SELECT * FROM "+studentDetailsTable+" where "+studentDetailsTable+".userid = ?";
		    
		    PreparedStatement pstmt = con.prepareStatement(q);

            pstmt.setString(1, id);
		    
		    ResultSet result = pstmt.executeQuery();
		    
		    if(result.next()) {
		    	user.setID(result.getString(1));
		    	user.setName(result.getString(2));
		    	user.setDepartment(getDepartment(result.getInt(3)));
		    	user.setYear(result.getString(4));
		    	user.setEmail(result.getString(5));
		    	user.setAchievements(getAchievements(id));
		    	user.setInternships(getInternships(id));
		    }
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	
	public String getDepartment(int deptID) {
		String name=null;
		try {
			Connection con = ConnectionProvider.getConnection();
		    String q = "SELECT "+departmentTable+".name FROM "+departmentTable+" where "+departmentTable+".userid = ?";
		    
		    PreparedStatement pstmt = con.prepareStatement(q);

            pstmt.setInt(1, deptID);
		    
		    ResultSet result = pstmt.executeQuery();
		    
		    if(result.next()) {
		    	name=result.getString(1);
		    }
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return name;
	}
	
	public ArrayList<Achievement> getAchievements(String id){
		ArrayList<Achievement>achievements = new ArrayList<Achievement>();
		
		try {
			Connection con = ConnectionProvider.getConnection();
		    String q = "SELECT * FROM "+achievementTable+" where "+achievementTable+".userid = ?";
		    
		    PreparedStatement pstmt = con.prepareStatement(q);

            pstmt.setString(1, id);
		    
		    ResultSet result = pstmt.executeQuery();
		    
		    while(result.next()) {
		    	Achievement achievement = new Achievement();
		    	achievement.setID(result.getString(1));
		    	achievement.setName(result.getString(2));
		    	achievement.setDescription(result.getString(3));
		    	achievement.setDate(result.getDate(4));
		    	achievement.setTimestamp(result.getTimestamp(5));
		    	achievements.add(achievement);
		    }
		    
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return achievements;
	}
	
	public ArrayList<Internship> getInternships(String id){
		ArrayList<Internship>internships = new ArrayList<Internship>();
		try {
			Connection con = ConnectionProvider.getConnection();
		    String q = "SELECT * FROM "+internshipTable+" where "+internshipTable+".userid = ?";
		    
		    PreparedStatement pstmt = con.prepareStatement(q);

            pstmt.setString(1, id);
		    
		    ResultSet result = pstmt.executeQuery();
		    
		    while(result.next()) {
		    	Internship internship = new Internship();
		    	internship.setID(result.getString(1));
		    	internship.setName(result.getString(2));
		    	internship.setDescription(result.getString(3));
		    	internship.setStartDate(result.getDate(4));
		    	internship.setEndDate(result.getDate(5));
		    	internship.setTimestamp(result.getTimestamp(6));
		    	internships.add(internship);
		    }
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return internships;
	}
	
	public void addAchievement(Achievement achievement,String id) {
		try {
			Connection con = ConnectionProvider.getConnection();
		    String q = "insert into "+achievementTable+" values(?,?,?,?,?)";
		    
		    PreparedStatement pstmt = con.prepareStatement(q);

            pstmt.setString(1, id);
            pstmt.setString(2,achievement.getName());
            pstmt.setString(3, achievement.getDescription());
            pstmt.setDate(4, achievement.getDate());
            pstmt.setTimestamp(4, achievement.getTimestamp());
		    
		    pstmt.executeQuery();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void addInternship(Internship internship,String id) {
		try {
			Connection con = ConnectionProvider.getConnection();
		    String q = "insert into "+internshipTable+" values(?,?,?,?,?,?)";
		    
		    PreparedStatement pstmt = con.prepareStatement(q);

            pstmt.setString(1, id);
            pstmt.setString(2,internship.getName());
            pstmt.setString(3, internship.getDescription());
            pstmt.setDate(4, internship.getStartDate());
            pstmt.setDate(5, internship.getEndDate());
            pstmt.setTimestamp(6, internship.getTimestamp());
		    
		    pstmt.executeQuery();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean CheckDatabase(Connection con,String database) {
		ArrayList<String> list = new ArrayList<String>();

		try {

			Statement st = (Statement) con.createStatement();
			DatabaseMetaData meta = (DatabaseMetaData) con.getMetaData();
			ResultSet rs = meta.getCatalogs();
			while (rs.next()) {
				String listofDatabases = rs.getString("TABLE_CAT");
				list.add(listofDatabases);
			}
			rs.close();
			if (list.contains(database)) {
				return true;
			} else {
				System.out.println("*******************DATABASE CREATED*********************");

				st.executeUpdate("CREATE DATABASE " + database);
				return false;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean checkTable(Connection con,String tableName) {
//		String tableName = "achivement";
		try (ResultSet result = con.getMetaData().getTables(null, null, tableName, null)) {
			while (result.next()) {
				String tName = result.getString("TABLE_NAME");
				if (tName != null && tName.equals(tableName)) {
					System.out.println("Table Exists.......");
					return true;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception

		}

		return false;
	}

	
	
}
