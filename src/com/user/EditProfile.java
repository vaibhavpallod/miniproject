package com.user;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.dao.Dao;
import com.mysql.cj.jdbc.Blob;
import com.dao.ConnectionProvider;


@WebServlet("/EditProfile")
public class EditProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String url = "jdbc:mysql://localhost:3306/student";
	private String sqlUsername = "root";
	private String sqlPassword = "0000";
	String database = "student";

	public EditProfile() {
		super();

	}
	
//	public boolean checkTableExists()

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		Dao dao = new Dao();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String id = "", ach_name = "", ach_des = "";
			Date ach_date;
			Blob ach_image;

			Connection con =ConnectionProvider.getConnection(); // DriverManager.getConnection(url, sqlUsername, sqlPassword);
			boolean dbExists = dao.CheckDatabase(con,database);
			HttpSession session = request.getSession();
//			User user=(User)session.getAttribute("User");
			id=(String)session.getAttribute("UserID");

			ach_name = request.getParameter("ach_name");
			ach_des = request.getParameter("ach_desc");
//			ach_image= request.get("ach_image");
			SimpleDateFormat in = new SimpleDateFormat("yyyy-MM-dd");
			String dateparameter = request.getParameter("ach_date");
			ach_date = (Date) in.parse(dateparameter);


			if (dbExists) {
				// If Database Exists
				System.out.println("Database already exists");
				String tableName = "achivement";
				boolean tExists = dao.checkTable(con,tableName);

				if (!tExists) {
					// if Table achivement does not exists creating dynamic table // DEFAULT NOW()
					String createtable = "CREATE TABLE achivement(userid varchar(20),ach_name varchar(80),ach_des varchar(150),ach_date date,ach_cert longblob,saved_on DATETIME)";
					Statement stmt = (Statement) con.createStatement();
					stmt.executeUpdate(createtable);
					System.out.println("*******************TABLE CREATED*********************");
				}
//			       DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
//				Calendar calobj = Calendar.getInstance();
//			       System.out.println(df.format(calobj.getTime()));

				String insertValue = "INSERT INTO achivement VALUES(?,?,?,?,?,?)";
				PreparedStatement pstmt = (PreparedStatement) con.prepareStatement(insertValue);
				pstmt.setString(1, id);
				pstmt.setString(2, ach_name);
				pstmt.setString(3, ach_des);
				pstmt.setDate(4, new java.sql.Date(ach_date.getTime()));
				pstmt.setBinaryStream(5,  new ByteArrayInputStream(request.getParameter("ach_image").getBytes()));
				pstmt.setDate(6, java.sql.Date.valueOf(java.time.LocalDate.now()));
				pstmt.executeUpdate();
				System.out.println("*******************SUCCESS*********************");
			}
			con.close();
			/*
			 * pstmt.setString(1, id); pstmt.setString(2, password);
			 * 
			 * ResultSet result = pstmt.executeQuery();
			 * 
			 * return result.next();
			 */
		} catch (Exception e) {
			System.out.println("*******************CAUGHT ERROR*********************");

			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		System.out.println(
				"***************************************************Clicked in post Method************************************************************");

	}

}
