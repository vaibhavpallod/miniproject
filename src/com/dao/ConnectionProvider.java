package com.dao;

import java.sql.*;

public class ConnectionProvider {
	private static Connection con;
	
	public static Connection getConnection() {
		try{
			String url = "jdbc:mysql://localhost:3306/student";
			String username = "root";
			String password = "0000";
			
			if(con == null || con.isClosed()) {
				Class.forName("com.mysql.cj.jdbc.Driver");
			    con = DriverManager.getConnection(url,username,password);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return con;
	}
}
