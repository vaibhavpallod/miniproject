package com.dao;

import java.io.FileOutputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;

import com.mysql.cj.jdbc.Blob;
import com.user.Achievement;
import com.user.Internship;
import com.user.ProfilePic;
import com.user.User;

public class Dao {

	private String passwordTable = "password";
	private String studentDetailsTable = "studentdetails";
	private String achievementTable = "achievement";
	private String internshipTable = "internship";
	private String departmentTable = "department";
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
	String function = "SELECT * FROM studentdetails";

	public boolean checkIdAndPassword(String id, String password) {
		boolean isCorrect = false;
		try {
			Connection con = ConnectionProvider.getConnection();

			String q = "SELECT * FROM " + passwordTable + " where " + passwordTable + ".id = ? and " + passwordTable
					+ ".pw=?";

			PreparedStatement pstmt = con.prepareStatement(q);

			pstmt.setString(1, id);
			pstmt.setString(2, password);

			ResultSet result = pstmt.executeQuery();
			isCorrect = result.next();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isCorrect;
	}

	public void setPassword(String id, String newPassword) {
		try {
			Connection con = ConnectionProvider.getConnection();

			String q = "UPDATE " + passwordTable + " set " + passwordTable + ".pw=? where " + passwordTable + ".id = ?";

			PreparedStatement pstmt = con.prepareStatement(q);

			pstmt.setString(1, newPassword);
			pstmt.setString(2, id);

			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getName(String id) {
		String name = null;
		try {
			Connection con = ConnectionProvider.getConnection();
			String q = "SELECT name FROM " + studentDetailsTable + " where " + studentDetailsTable + ".userid = ?";

			PreparedStatement pstmt = con.prepareStatement(q);

			pstmt.setString(1, id);

			ResultSet result = pstmt.executeQuery();

			if (result.next()) {
				name = result.getString("name");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return name;
	}

	public User getUser(String id) {
		User user = new User();
		try {
			Connection con = ConnectionProvider.getConnection();
			String q = "SELECT * FROM " + studentDetailsTable + " where " + studentDetailsTable + ".userid = ?";

			PreparedStatement pstmt = con.prepareStatement(q);

			pstmt.setString(1, id);

			ResultSet result = pstmt.executeQuery();

			if (result.next()) {
				user.setID(result.getString(1));
				user.setName(result.getString(2));
				user.setYear(result.getString(3));
				user.setDepartment(getDepartment(result.getInt(4)));
				user.setBio(result.getString(6));
				user.setEmail(result.getString(7));
				user.setContact(String.valueOf(result.getInt(8)));
				user.setAchievements(getAchievements(id));
				user.setInternships(getInternships(id));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	public String getDepartment(int deptID) {
		String name = null;
		try {
			Connection con = ConnectionProvider.getConnection();
			String q = "SELECT " + departmentTable + ".deptname FROM " + departmentTable + " where " + departmentTable
					+ ".deptid = ?";

			PreparedStatement pstmt = con.prepareStatement(q);

			pstmt.setInt(1, deptID);

			ResultSet result = pstmt.executeQuery();

			if (result.next()) {
				name = result.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return name;
	}

	private String getEncodedString(ResultSet resultSet, int i) {
		String fileName = "image.png";
		String encodeString = "null";
		System.out.println("Enter");
		try (FileOutputStream fos = new FileOutputStream(fileName)) {
			Blob blob = null;
			System.out.println("get   1  " + blob);

			if (i == 0) {
				blob = (Blob) resultSet.getBlob("achcert");
				System.out.println("get xx    " + blob);

			} else if (i == 1) {
				blob = (Blob) resultSet.getBlob("intrncert");
				System.out.println("get yy    " + blob);

			} else if (i == 3) {
				blob = (Blob) resultSet.getBlob("pic");
				System.out.println("get zz    " + blob);
			}

			int len = (int) blob.length();

			byte[] buf = blob.getBytes(1, len);
			encodeString = Base64.getEncoder().encodeToString(buf);
			fos.write(buf, 0, len);

		} catch (Exception e) {
			// TODO: handle exception
		}

		return encodeString;

	}

	public ArrayList<Achievement> getAchievements(String id) {
		ArrayList<Achievement> achievements = new ArrayList<Achievement>();

		try {
			Connection con = ConnectionProvider.getConnection();
			String q = "SELECT * FROM " + achievementTable + " where " + achievementTable + ".userid = ?";

			PreparedStatement pstmt = con.prepareStatement(q);

			pstmt.setString(1, id);

			ResultSet result = pstmt.executeQuery();
			while (result.next()) {
				Achievement achievement = new Achievement();
				achievement.setID(result.getString(1));
				achievement.setAchievementID(result.getInt(2));
				achievement.setName(result.getString(3));
				achievement.setDescription(result.getString(4));
				achievement.setDate(result.getDate(5));
				achievement.setEncodedString(getEncodedString(result, 0));
				achievement.setTimestamp(result.getTimestamp(7));
				achievements.add(achievement);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return achievements;
	}

	public ArrayList<Internship> getInternships(String id) {
		ArrayList<Internship> internships = new ArrayList<Internship>();
		try {
			Connection con = ConnectionProvider.getConnection();
			String q = "SELECT * FROM " + internshipTable + " where " + internshipTable + ".userid = ?";

			PreparedStatement pstmt = con.prepareStatement(q);
			pstmt.setString(1, id);

			ResultSet result = pstmt.executeQuery();

			while (result.next()) {
				Internship internship = new Internship();
				internship.setID(result.getString(1));
				internship.setInternshipID(result.getInt(2));
				internship.setName(result.getString(3));
				internship.setDescription(result.getString(4));
				internship.setStartDate(result.getDate(5));
				internship.setEndDate(result.getDate(6));
				internship.setStatus(result.getString("status"));
				internship.setNor(result.getString("nor"));
				internship.setEncodedString(getEncodedString(result, 1));
				internship.setTimestamp(result.getTimestamp(10));
				internships.add(internship);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return internships;
	}

	public void addAchievement(Achievement achievement) {
		try {
			Connection con = ConnectionProvider.getConnection();
			// "CREATE TABLE achievement(userid varchar(20),achid int NOT NULL
			// AUTO_INCREMENT PRIMARY KEY,achname varchar(80),achdes varchar(150),achdate
			// date,achcert longblob,savedon DATETIME,FOREIGN KEY(userid) REFERENCES
			// studentdetails(userid))";

			String q = "insert into " + achievementTable
					+ "(userid,achname,achdes,achdate,achcert,savedon) values(?,?,?,?,?,?)";

			PreparedStatement pstmt = con.prepareStatement(q);

			pstmt.setString(1, achievement.getID());
			pstmt.setString(2, achievement.getName());
			pstmt.setString(3, achievement.getDescription());
			pstmt.setTimestamp(4,(achievement.getTimestamp()));
			pstmt.setBlob(5, achievement.getCertificate());
			pstmt.setDate(6, java.sql.Date.valueOf(java.time.LocalDate.now()));

//			pstmt.setTimestamp(4, achievement.getTimestamp());

			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateAchievement(Achievement achievement) {
		try {
			Connection con = ConnectionProvider.getConnection();
			// "CREATE TABLE achievement(userid varchar(20),achid int NOT NULL
			// AUTO_INCREMENT PRIMARY KEY,achname varchar(80),achdes varchar(150),achdate
			// date,achcert longblob,savedon DATETIME,FOREIGN KEY(userid) REFERENCES
			// studentdetails(userid))";

			String q = "update " + achievementTable
					+ " set achname=?,achdes=?,achdate=?,achcert=?,savedon=? where achid=?";

			PreparedStatement pstmt = con.prepareStatement(q);

			pstmt.setString(1, achievement.getName());
			pstmt.setString(2, achievement.getDescription());
			pstmt.setDate(3, new java.sql.Date((achievement.getTimestamp().getTime())));
			pstmt.setBlob(4, achievement.getCertificate());
			pstmt.setDate(5, java.sql.Date.valueOf(java.time.LocalDate.now()));
			pstmt.setInt(6, achievement.getAchievementID());

			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addInternship(Internship internship) {
		try {
			Connection con = ConnectionProvider.getConnection();
			String q = "insert into " + internshipTable
					+ "(userid,intrnname,intrndes,startdate,enddate,status,nor,intrncert,savedon) values(?,?,?,?,?,?,?,?,?)";

			PreparedStatement pstmt = con.prepareStatement(q);

			pstmt.setString(1, internship.getID());
			pstmt.setString(2, internship.getName());
			pstmt.setString(3, internship.getDescription());
			pstmt.setDate(4, new java.sql.Date(internship.getStartDate().getTime()));
			pstmt.setDate(5, new java.sql.Date(internship.getEndDate().getTime()));
			pstmt.setString(6, internship.getStatus());
			pstmt.setString(7, internship.getNor());
			pstmt.setBlob(8, internship.getCertificate());
			pstmt.setDate(9, java.sql.Date.valueOf(java.time.LocalDate.now()));

			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateInternship(Internship internship) {
		try {
			Connection con = ConnectionProvider.getConnection();

			String q = "update " + internshipTable
					+ " set intrnname=?,intrndes=?,startdate=?,enddate=?,status=?,nor=?,intrncert=?,savedon=? where intrnid=?";

			PreparedStatement pstmt = con.prepareStatement(q);

			pstmt.setString(1, internship.getName());
			pstmt.setString(2, internship.getDescription());
			pstmt.setDate(3, new java.sql.Date(internship.getStartDate().getTime()));
			pstmt.setDate(4, new java.sql.Date(internship.getEndDate().getTime()));
			pstmt.setString(5, internship.getStatus());
			pstmt.setString(6, internship.getNor());
			pstmt.setBlob(7, internship.getCertificate());
			pstmt.setDate(8, java.sql.Date.valueOf(java.time.LocalDate.now()));
			pstmt.setInt(9, internship.getInternshipID());

			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteAchievement(int achievementId) {
		try {
			Connection con = ConnectionProvider.getConnection();
			String q = "delete from " + achievementTable + " where achid = ?";

			PreparedStatement pstmt = con.prepareStatement(q);

			pstmt.setInt(1, achievementId);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteInternship(int internshipId) {
		try {
			Connection con = ConnectionProvider.getConnection();
			String q = "delete from " + internshipTable + " where intrnid = ?";

			PreparedStatement pstmt = con.prepareStatement(q);

			pstmt.setInt(1, internshipId);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean CheckDatabase(Connection con, String database) {
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

	public boolean checkTable(Connection con, String tableName) {
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

	public boolean checkProfilepic(String id) {
		Connection con = ConnectionProvider.getConnection();
		String check = "SELECT userid FROM profilepic WHERE profilepic.userid = "+id;
		boolean isavailable = false;
		Statement stmt;
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(check);

			while (rs.next()) {
				if (rs.getString("userid").equals(id))
					isavailable = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return isavailable;
	}

	public String getProfilePic(String id) {
		Connection con = ConnectionProvider.getConnection();
		ProfilePic pic = new ProfilePic();
		String ens = null;
		if (checkProfilepic(id)) {
			String query = "SELECT * from profilepic where profilepic.userid = " + id;
			try {
				Statement stmt = con.createStatement();
				ResultSet resultSet = stmt.executeQuery(query);
				resultSet.next();
				pic.setUserID(id);
				ens = getEncodedString(resultSet, 3);
				pic.setEncodedString(ens);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ens;
	}

	public User updateDetails(User user) {
		try {
			Connection con = ConnectionProvider.getConnection();
			String q = "UPDATE " + studentDetailsTable + " SET name=?,bio=?,email=?,mobile=? where "
					+ studentDetailsTable + ".userid = ?";

			PreparedStatement pstmt = con.prepareStatement(q);

			pstmt.setString(1, user.getName());
			pstmt.setString(2, user.getBio());
			pstmt.setString(3, user.getEmail());
			pstmt.setInt(4, Integer.parseInt(user.getContact()));
			pstmt.setString(5, user.getID());

			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	public ArrayList<User> getAllUsers() {
		Connection con = ConnectionProvider.getConnection();
		String query = "SELECT * FROM studentdetails";
		ArrayList<User> users = new ArrayList<User>();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				String id = rs.getString("userid");

				User user = new User();
				user.setID(id);
				user.setName(rs.getString("name"));
				user.setBio(rs.getString("bio"));
				user.setAchievements(getAchievements(id));
				user.setInternships(getInternships(id));

				users.add(user);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;
	}

	public boolean compareTimestamp(Timestamp t1, Timestamp t2) {
		Connection con = ConnectionProvider.getConnection();
//		String function = "DELIMITER $$ \n"+" CREATE FUNCTION compareTimestamp(t1 timestamp,t2 timestamp) RETURNS bool DETERMINISTIC BEGIN DECLARE isearly BOOL;SET isearly = FALSE;IF t1<t2 THEN SET isearly=true; END IF; RETURN isearly; END end DELIMITER ;";
		String query = "SELECT student.compareTimestamp(" + "'" + t1 + "','" + t2 + "'" + ")";
		boolean bool = false;
		try {
			System.out.println(1);
			CallableStatement cstmt = con.prepareCall("{? = call compareTimestamp(?,?)}");
			cstmt.setTimestamp(2, t1);
			cstmt.setTimestamp(3, t2);
			cstmt.registerOutParameter(1, Types.BOOLEAN);
			cstmt.execute();
			System.out.println(2);
			bool = cstmt.getBoolean(1);  //getBoolean(0);
		} catch (SQLException e) {

			e.printStackTrace();
		}
		System.out.println("xxxxxxxxxxxxxxxxxxxxxx   "+bool);

		return bool;
	}

}