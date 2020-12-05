package com.updateDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;

import com.dao.ConnectionProvider;
import com.dao.Dao;

public class UpdateDatabase {

	public static void checkdatabase() {
		String database = "student";
		Connection con = ConnectionProvider.getConnection();
		Dao tableDao = new Dao();
		// checking database
		if (!tableDao.CheckDatabase(con, database)) {
			try {
				Statement stmt = con.createStatement();
				String sql = "CREATE DATABASE student";
				stmt.executeUpdate(sql);

				if (stmt != null)
					stmt.close();
				if (con != null)
					con.close();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	public static boolean Checktables() {

		Dao tableDao = new Dao();
		boolean tExists = false;

		String studentDetailsTable = "studentdetails";
		String achievementTable = "achievement";
		String internshipTable = "internship";
		String departmentTable = "department";
		String profileTable = "profilepic";

		Connection con = ConnectionProvider.getConnection();

		try {

			Statement stmt;
			stmt = (Statement) con.createStatement();

			tExists = tableDao.checkTable(con, departmentTable);

			if (!tExists) {
				// if Table DEPARTMENT does not exists creating dynamic table // DEFAULT NOW()
				String createtable = "CREATE TABLE department(deptid int PRIMARY KEY,deptname varchar(40))";
				stmt.executeUpdate(createtable);
				System.out.println("*******************department TABLE CREATED*********************");
			}

			tExists = tableDao.checkTable(con, studentDetailsTable);

			if (!tExists) {
				// if Table STUDENT_DETAILS does not exists creating dynamic table
				String createtable = "CREATE TABLE studentdetails(userid varchar(20) PRIMARY KEY    ,name varchar(50),rollno varchar(30),deptid int,class varchar(30),bio varchar(200),email varchar(50),mobile int,FOREIGN KEY(deptid) REFERENCES department(deptid));";
				stmt.executeUpdate(createtable);
				System.out.println("*******************studentdetails TABLE CREATED*********************");
			}

			tExists = tableDao.checkTable(con, achievementTable);

			if (!tExists) {
				// if Table achievement does not exists creating dynamic table
				String createtable = "CREATE TABLE achievement(userid varchar(20),achid int NOT NULL AUTO_INCREMENT PRIMARY KEY,achname varchar(80),achdes varchar(150),achdate date,achcert longblob,savedon timestamp,FOREIGN KEY(userid) REFERENCES studentdetails(userid))";
				stmt.executeUpdate(createtable);
				System.out.println("*******************ACHIEVEMENT TABLE CREATED*********************");
			}

			tExists = tableDao.checkTable(con, internshipTable);

			if (!tExists) {
				// if Table INTERNSHIP does not exists creating dynamic table
				String createtable = "CREATE TABLE internship(userid varchar(20) ,intrnid int NOT NULL AUTO_INCREMENT PRIMARY KEY,intrnname varchar(80),intrndes varchar(150),startdate date,enddate date,status varchar(30),nor varchar(10),intrncert longblob,savedon timestamp,FOREIGN KEY(userid) REFERENCES studentdetails(userid))";
				stmt.executeUpdate(createtable);
				System.out.println("*******************internship TABLE CREATED*********************");
			}

			tExists = tableDao.checkTable(con, profileTable);

			if (!tExists) {
				// if Table PROFILEPIC does not exists creating dynamic table
				String createtable = "CREATE TABLE profilepic(userid varchar(20),pic longblob,FOREIGN KEY(userid) REFERENCES studentdetails(userid))";
				stmt.executeUpdate(createtable);
				System.out.println("*******************profilepic TABLE CREATED*********************");
			}

			con.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
			return false;
		}
		return true;

	}


	public static void checkFunction() {
		Connection con = ConnectionProvider.getConnection();
		
//		String checkFuncion = "IF EXISTS (SELECT * FROM sysobjects WHERE object_id = OBJECT_ID(N'compareTimestamp') AND xtype IN (N'FN', N'IF', N'TF')) BEGIN DROP FUNCTION compareTimestamp GO ";
//		String checkFuncion = "IF OBJECT_ID(N'student.compareTimestamp') IS NOT NULL BEGIN PRINT 'User defined function Exists' END";// DROP FUNCTION student.compareTimestamp 
//		try {
//			Statement stmt = con.createStatement();
//			stmt.execute(checkFuncion);
//			
//			System.out.println("**************** Function deleted ****************");
//		} catch (SQLException e) {
//
//			e.printStackTrace();
//		}
		
		String function = "CREATE FUNCTION compareTimestamp(t1 timestamp,t2 timestamp) RETURNS bool DETERMINISTIC BEGIN DECLARE isearly BOOL;SET isearly = FALSE;IF t1<t2 THEN SET isearly=true; END IF; RETURN isearly; END ";//DELIMITER ;
//		String query = "SELECT student.compareTimestamp(" + "'" + t1 + "','" + t2 + "'" + ")";
		try {
			Statement stmt = con.createStatement();
			stmt.execute(function);
			
			System.out.println("**************** Function Created ****************");
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

}